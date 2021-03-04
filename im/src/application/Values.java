package application;

import java.util.List;

public class Values {

    public List<Double> min;
    public List<Double> max;
    public List<Integer> hitRate;
    public List<Double> relativeHitRate;
    
    public Values(List<Double> min, List<Double> max, List<Integer> hitRate, List<Double> relativeHitRate) {
        this.min = min;
        this.max = max;
        this.hitRate = hitRate;
        this.relativeHitRate = relativeHitRate;
    }
}
