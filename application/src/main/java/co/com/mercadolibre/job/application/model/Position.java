/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.mercadolibre.job.application.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author LENOVO
 */
@Getter @Setter @NoArgsConstructor
public class Position {
    
    private float x;
    private float y;

    public Position(float x, float y) {
        this.x = x;
        this.y = y;
    }
    
}
