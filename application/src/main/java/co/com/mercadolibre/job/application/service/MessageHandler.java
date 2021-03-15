/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.mercadolibre.job.application.service;

import co.com.mercadolibre.job.application.exception.MessageNotCreatedException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author LENOVO
 */
@Service
public class MessageHandler {

    public String getMessage(String[]... messages) {
        String[] responseMessage;
        try {
            responseMessage = new String[messages[0].length];
        } catch (Exception ex) {
            throw new MessageNotCreatedException("Any message or satellite present.");
        }
        if(messages.length != 3){
            throw new MessageNotCreatedException("Insuficient data or duplicated.");
        }
        String[] baseMessage = messages[0];
        //For each other satellite in messages
        for (int i = 1; i < messages.length; i++) {
            //try doing at least compareSatellite.length shiftings
            int maxShiftingsNumber = messages[i].length;
            for (int j = 0; j <= maxShiftingsNumber; j++) {
                if (j == maxShiftingsNumber) {
                    throw new MessageNotCreatedException("Incompatible messages. There is no possible solution for this combination.");
                }
                if (!isMessageEquals(baseMessage, messages[i])) {//If not equals
                    List<String> listMessasge = Arrays.asList(messages[i]);
                    Collections.rotate(listMessasge, 1);//Shift message
                    messages[i] = listMessasge.toArray(String[]::new);//re assign
                } else {
                    break;//If they are equals, finish processing
                }
            }
        }
        //Create responseMessage
        responseMessage = createMessageResponse(messages);
        return String.join(" ", Arrays.asList(responseMessage));
    }

    private String[] createMessageResponse(String[][] messages) {
        String[] responseMessage = new String[messages[0].length];
        for (String[] message : messages) {
            for (int i = 0; i < message.length; i++) {//each message word
                responseMessage[i] = !message[i].isEmpty() ? message[i] : responseMessage[i];
                if (!Arrays.stream(responseMessage).anyMatch(m -> m == null)) {
                    return responseMessage;
                }
            }
        }
        return responseMessage;
    }

    private boolean isMessageEquals(String[] baseMessage, String[] compareMessage) {
        // If the object is compared with itself then return true   
        if (baseMessage == compareMessage) {
            return true;
        }
        // Compare the data members and return accordingly
        boolean areEquals = true;
        //Compare length
        if (baseMessage.length != compareMessage.length) {
            throw new MessageNotCreatedException("Messages must have same length.");
        }
        //logic
        for (int i = 0; i < compareMessage.length; i++) {
            areEquals = (compareMessage[i].equals(baseMessage[i]))
                    || (compareMessage[i].isEmpty() && !baseMessage[i].isEmpty())
                    || (!compareMessage[i].isEmpty() && baseMessage[i].isEmpty());
            if (!areEquals) {
                break;
            }

        }
        return areEquals;
    }
}
