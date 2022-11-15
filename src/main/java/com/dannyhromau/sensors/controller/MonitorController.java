package com.dannyhromau.sensors.controller;

import com.dannyhromau.sensors.model.Sensor;
import com.dannyhromau.sensors.service.SensorService;
import com.dannyhromau.sensors.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
public class MonitorController {
    @Autowired
    private UserService userService;
    @Autowired
    private SensorService sensorService;

    @GetMapping("/api/sensors")
    public List<Sensor> getSensors() {
        return sensorService.getSensors();
    }

    @PostMapping("/api/sensor")
    public boolean addSensor(Sensor sensor) {

        return sensorService.saveNewSensor(sensor);
    }

    @DeleteMapping("/api/sensor{id}")
    public boolean removeSensor(@PathVariable int id) {
        return sensorService.removeSensor(id);
    }

    @PostMapping("/api/sensor{id}")
    public boolean updateSensor(@PathVariable int id, @RequestBody Sensor sensor) {
        return sensorService.updateSensor(sensor);
    }

    @GetMapping("/api/sensor{name}")
    public String getDescription(@PathVariable String name) {

        return sensorService.getDescription(name);
    }
    @GetMapping("/api/sensors/type")
    public List<String> getSensorsTypes(){
        List<String> typesList = new ArrayList<>();
        Sensor.Type[] typesArray = Sensor.Type.values();
        for (Sensor.Type type : typesArray){
            typesList.add(type.toString());
        }
        return typesList;
    }
    @GetMapping("/api/sensors/unit")
    public List<String> getSensorsUnits(){
        List<String> unitsList = new ArrayList<>();
        Sensor.Unit[] unitsArray = Sensor.Unit.values();
        for (Sensor.Unit unit : unitsArray){
            unitsList.add(unit.toString());
        }
        return unitsList;
    }
}
