/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.mercadolibre.job.application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author LENOVO
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class MessageNotCreatedException extends RuntimeException{

    public MessageNotCreatedException(String message) {
        super(message);
    }
    
}
