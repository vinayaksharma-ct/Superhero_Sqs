package com.example.LocalSQS.Object;

import lombok.Data;

@Data
public class SuperheroRequestBody {
    private String name;
    private String power;
    private String universe;

    SuperheroRequestBody(String name, String power, String universe) {
        this.name = name;
        this.power = power;
        this.universe = universe;
    }

}