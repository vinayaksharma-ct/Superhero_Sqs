package com.example.LocalSQS.Controller;

import com.example.LocalSQS.Service_SQS.SQS_Receiver;
import com.example.LocalSQS.Service_SQS.SQS_Sender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SQSController {

    private final SQS_Sender sqsSenderService;
    private final SQS_Receiver sqsReceiverService;

    @Autowired
    public SQSController(SQS_Sender sqsSenderService, SQS_Receiver sqsReceiverService) {
        this.sqsSenderService = sqsSenderService;
        this.sqsReceiverService = sqsReceiverService;
    }

    /**
     * Endpoint to send a message to the SQS queue.
     *
     * @return A message indicating the result of the operation.
     */
    @GetMapping("/sendMessage")
    public String sendMessageToQueue() {
        sqsSenderService.sendMessage("Superman");
        return "Message successfully sent to the queue!";
    }

    /**
     * Endpoint to receive messages from the SQS queue.
     *
     * @return A message confirming that messages have been processed.
     */
    @GetMapping("/receiveMessages")
    public String processQueueMessages() {
        sqsReceiverService.receiveMessages();
        return "Messages have been received and processed!";
    }
}
