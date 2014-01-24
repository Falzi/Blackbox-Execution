package org.blackbox.execution.routing.c7.model;

import java.util.ArrayList;
import java.util.List;

public class SplitProcessor {

    public List<Output> split(org.blackbox.execution.routing.c6.model.Output row7) {
        
        List<Output> lines = new ArrayList<Output>();
        double[][] array = row7.points;
        Output output;
        for (double[] line : array) {
            output = new Output();
            output.line=line;
            lines.add(output);
        }
        return lines;
    }
    
}
