/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.mercadolibre.job.application.model;

import java.util.Arrays;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author LENOVO
 */
@Getter @Setter @NoArgsConstructor
public class Satellite {

    private String name;
    private float distance;
    private String[] message;

    @Override
    public String toString() {
        return "Satellite{" + "name=" + name + ", distance=" + distance + ", message=" + Arrays.asList(message) + '}';
    }

}
