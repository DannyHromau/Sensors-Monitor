package com.dannyhromau.sensors.repositories;

import com.dannyhromau.sensors.model.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SensorRepository extends CrudRepository<Sensor, Integer> {
    Sensor findByName(String sensorName);

    List<Sensor> findAll();

    Sensor findById(int id);

    int deleteSensorById(int id);
}
