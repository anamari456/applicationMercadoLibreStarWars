/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.mercadolibre.job.application.service;

import co.com.mercadolibre.job.application.model.Position;
import com.lemmingapex.trilateration.NonLinearLeastSquaresSolver;
import com.lemmingapex.trilateration.TrilaterationFunction;
import org.apache.commons.math3.fitting.leastsquares.LeastSquaresOptimizer;
import org.apache.commons.math3.fitting.leastsquares.LevenbergMarquardtOptimizer;
import org.springframework.stereotype.Service;

/**
 *
 * @author LENOVO
 */
@Service
public class LocationHandler {
    
    public static final Position KENOBI_POSITION = new Position(-500.0f, -200.0f);
    public static final Position SKYWALKER_POSITION = new Position(100.0f, -100.0f);
    public static final Position SATO_POSITION = new Position(500.0f, 100.0f);

    public Position getLocation(Float... floatDistances) {
        Position emisorPsition = new Position();
        double[][] positions = new double[][]{{KENOBI_POSITION.getX(),KENOBI_POSITION.getY()}, 
                                              {SKYWALKER_POSITION.getX(), SKYWALKER_POSITION.getY()}, 
                                              {SATO_POSITION.getX(), SATO_POSITION.getY()}};
        double[] distances = new double[floatDistances.length];

        for (int i = 0; i < floatDistances.length; i++) {
            distances[i] = (double) floatDistances[i];
        }
        
        NonLinearLeastSquaresSolver solver;
        solver = new NonLinearLeastSquaresSolver(new TrilaterationFunction(positions, distances), new LevenbergMarquardtOptimizer());
        LeastSquaresOptimizer.Optimum optimum = solver.solve();

        // the answer
        double[] calculatedPosition = optimum.getPoint().toArray();
        emisorPsition.setX((float)calculatedPosition[0]);
        emisorPsition.setY((float)calculatedPosition[1]);
        return emisorPsition;
    }
}
