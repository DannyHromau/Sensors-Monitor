package com.dannyhromau.sensors.service;

import com.dannyhromau.sensors.model.Sensor;
import com.dannyhromau.sensors.repositories.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SensorService {
    @Autowired
    private SensorRepository sensorRepository;



    public boolean saveNewSensor(Sensor sensor){
        Sensor existsSensor = sensorRepository.findByName(sensor.getName());
        boolean isSaved = true;
        if (existsSensor != null) {
            isSaved = false;
        }
        return isSaved;
    }

    public List<Sensor> getSensors(){
        return sensorRepository.findAll();
    }
    public List<Sensor> getSensorsByKeyWord(String keyWord){
        List<Sensor> existsSensorsList = sensorRepository.findAll();
        List<Sensor> acceptableSensors = new ArrayList<>();
        for (Sensor sensor : existsSensorsList){
            if (sensor.toString().contains(keyWord)){
                acceptableSensors.add(sensor);
            }
        }
        return acceptableSensors;
    }

    public boolean removeSensor(int id){
        boolean isDeleted = true;
        if (sensorRepository.findById(id) != null){
        sensorRepository.deleteSensorById(id);}
        else {
            isDeleted = false;
        }
        return isDeleted;
    }

    public boolean updateSensor(Sensor updatedSensor){
        Sensor updatableSensor = sensorRepository.findById(updatedSensor.getId());
        boolean isUpdated = true;
        if (updatableSensor != null){
        updatableSensor.setModel(updatedSensor.getModel());
        updatableSensor.setName(updatedSensor.getName());
        updatableSensor.setRange(updatedSensor.getRange());
        updatableSensor.setType(updatedSensor.getType());
        updatableSensor.setUnit(updatedSensor.getUnit());
        sensorRepository.save(updatableSensor);}
        else {
            isUpdated = false;
        }
        return isUpdated;
    }
    public String getDescription(String name){
        String description = "";
        if (sensorRepository.findByName(name) != null){
            description = sensorRepository.findByName(name).getDescription();
        }
        return description;
    }
}
