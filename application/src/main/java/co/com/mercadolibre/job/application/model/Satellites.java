/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.mercadolibre.job.application.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author LENOVO
 */
@Getter @Setter @NoArgsConstructor
public class Satellites {
    
    private List<Satellite> satellites = new ArrayList<>();
    
    public String[][] getMessages(){
        return  satellites.stream().map(s -> s.getMessage()).collect(Collectors.toList()).toArray(String[][]::new);
    }
    
    public Float[] getDistances(){
        return  satellites.stream().map(s -> s.getDistance()).collect(Collectors.toList()).toArray(Float[]::new);
    }
}
