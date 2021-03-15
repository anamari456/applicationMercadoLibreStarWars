/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.mercadolibre.job.application.controller;

import co.com.mercadolibre.job.application.model.Response;
import co.com.mercadolibre.job.application.model.Satellites;
import co.com.mercadolibre.job.application.service.LocationHandler;
import co.com.mercadolibre.job.application.service.MessageHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author LENOVO
 */
@Controller
@RequestMapping(value="/application")
public class PostTopSecretController {
    
    @Autowired
    private MessageHandler messageHandler;
    
    @Autowired 
    private LocationHandler locationHandler;

    @PostMapping("/topsecret")
    public ResponseEntity<Response> create(@RequestBody Satellites satellites) {
        Response response = new Response();
        response.setMessage(messageHandler.getMessage(satellites.getMessages()));
        response.setPosition(locationHandler.getLocation(satellites.getDistances()));
        return ResponseEntity.ok(response);
    }
}
