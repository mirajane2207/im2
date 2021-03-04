package application;

import java.util.List;
import java.util.Objects;

public class Value {
   
    public Integer value;
    public Integer hitRate;
    public Double relativeHitRate;
    public String interval;
    
    public Value(Integer hitRate, Double relativeHitRate, String interval, Integer value) {
        this.hitRate = hitRate;
        this.relativeHitRate = relativeHitRate;
        this.interval = interval;
        this.value = value;
    }
    
    public boolean equals(Object obj) {
        
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        
        Value other = (Value) obj;
        return Objects.equals(hitRate, other.hitRate) && Objects.equals(relativeHitRate, other.relativeHitRate)
                && Objects.equals(interval, other.interval)&& Objects.equals(value, other.value);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(hitRate, relativeHitRate, interval, value);
    }
    
    public Integer getHitRate() {
        return this.hitRate;
    }
    
    public Double getRelativeHitRate() {
        return this.relativeHitRate;
    }
    
    public String getInterval() {
        return this.interval;
    }
    
    public Integer getValue() {
        return this.value;
    }
    
    public String  toString() {
        return " Value: " + value +
                "\n hr: " + hitRate + 
                "\n rhr: " + relativeHitRate +
                "\n Interval " + interval ;
    }
    
}
