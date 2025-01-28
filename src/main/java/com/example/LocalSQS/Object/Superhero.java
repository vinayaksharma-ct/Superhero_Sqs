package com.example.LocalSQS.Object;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document(collection = "superheroes")  // MongoDB collection name
@Data
public class Superhero {

    @Id
    private String id;
    private String name;
    private String power;
    private String universe;

    public Superhero() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getPower() {
        return this.power;
    }

    public String getUniverse() {
        return this.universe;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public void setUniverse(String universe) {
        this.universe = universe;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // If the objects are the same reference, return true
        if (o == null || getClass() != o.getClass()) return false; // Check for null or different class types
        Superhero other = (Superhero) o; // Cast to Superhero for comparison
        return Objects.equals(id, other.id) &&
                Objects.equals(name, other.name) &&
                Objects.equals(power, other.power) &&
                Objects.equals(universe, other.universe); // Compare all fields
    }


    protected boolean canEqual(final Object other) {
        return other instanceof Superhero;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, power, universe);
    }

    public String toString() {
        return "Superhero(id=" + this.getId() + ", name=" + this.getName() + ", power=" + this.getPower() + ", universe=" + this.getUniverse() + ")";
    }
}