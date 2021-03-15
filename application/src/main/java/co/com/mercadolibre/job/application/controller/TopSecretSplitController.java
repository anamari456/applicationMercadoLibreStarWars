/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.mercadolibre.job.application.controller;

import co.com.mercadolibre.job.application.model.Response;
import co.com.mercadolibre.job.application.model.Satellite;
import co.com.mercadolibre.job.application.model.Satellites;
import co.com.mercadolibre.job.application.service.LocationHandler;
import co.com.mercadolibre.job.application.service.MessageHandler;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author LENOVO
 */
@Controller
@RequestMapping(value="/application")
public class TopSecretSplitController {
    
    private static Satellites satellites = new Satellites();
    
    @Autowired
    private MessageHandler messageHandler;
    
    @Autowired 
    private LocationHandler locationHandler;

    @PostMapping("/topsecret_split")
    @ResponseBody
    public ResponseEntity create(@RequestParam(required=true, name="satellite_name") String satelliteName, @RequestBody Satellite satellite) {
        satellite.setName(satelliteName);
        List<Satellite> satellitesList = satellites.getSatellites();
        satellitesList.add(satellite);
        satellites.setSatellites(satellitesList);
        System.out.println("Cantidad Satelites "+satellites.getSatellites().size());
        return new ResponseEntity(HttpStatus.CREATED);
    }
    
    @GetMapping("/topsecret_split")
    public ResponseEntity<Response> create() {
        Response response = new Response();
        response.setMessage(messageHandler.getMessage(satellites.getMessages()));
        response.setPosition(locationHandler.getLocation(satellites.getDistances()));
        satellites = new Satellites();
        return ResponseEntity.ok(response);
    }
}
