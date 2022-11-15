package com.dannyhromau.sensors.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "sensors")
@NoArgsConstructor
public class Sensor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    private String model;
    private String range;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "enum", name = "type")
    private Type type;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "enum", name = "unit")
    private Unit unit;
    private String location;
    private String description;

   public enum Type {
        PRESSURE,
        VOLTAGE,
        TEMPERATURE,
        HUMIDITY
    }

   public enum Unit {
        BAR,
        VOLTAGE,
        CELSIUS,
        PERCENT
    }

    @Override
    public String toString() {
        return "Sensor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", model='" + model + '\'' +
                ", range='" + range + '\'' +
                ", type=" + type +
                ", unit=" + unit +
                ", location='" + location + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

}
