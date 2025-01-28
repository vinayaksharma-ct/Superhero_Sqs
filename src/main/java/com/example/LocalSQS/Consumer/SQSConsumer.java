package com.example.LocalSQS.Consumer;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.example.LocalSQS.Object.Superhero;
import com.example.LocalSQS.Repository.SuperheroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SQSConsumer {

    private static final String QUEUE_URL = "http://localhost:4566/000000000000/localstack"; // LocalStack Queue URL

    private final SuperheroRepository superheroRepository;

    @Autowired
    private AmazonSQS amazonSQS;

    public SQSConsumer(SuperheroRepository superheroRepository) {
        this.superheroRepository = superheroRepository;
    }

    /**
     * Periodically fetches messages from the SQS queue and processes them.
     */
    @Scheduled(fixedRate = 5000) // Executes every 5 seconds
    public void receiveAndProcessMessages() {
        ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest()
                .withQueueUrl(QUEUE_URL)
                .withMaxNumberOfMessages(10) // Maximum number of messages to fetch
                .withWaitTimeSeconds(10);    // Long polling with 10-second timeout

        // Fetch messages from the queue
        List<Message> messages = amazonSQS.receiveMessage(receiveMessageRequest).getMessages();

        if (messages.isEmpty()) {
            System.out.println("No new messages available.");
            return;
        }

        // Iterate over the received messages
        for (Message message : messages) {
            String superheroName = message.getBody();
            System.out.println("Processing message: " + superheroName);

            Optional<Superhero> superheroOptional = superheroRepository.findByName(superheroName);

            if (superheroOptional.isPresent()) {
                Superhero superhero = superheroOptional.get();
                superhero.setName(superheroName);
                superhero.setPower("Invisible");
                superhero.setUniverse("Marvel");

                // Save updated superhero to the repository
                superheroRepository.save(superhero);
                System.out.println("Superhero updated: " + superheroName);
            } else {
                System.err.println("Superhero not found: " + superheroName);
            }

            // Delete the processed message from the queue
            amazonSQS.deleteMessage(QUEUE_URL, message.getReceiptHandle());
            System.out.println("Message deleted from the queue.");
        }
    }
}
