package org.alterbg.musala.aq.bean;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

public class AQLog {

  private GLocation location;
  private MeasureUnit unit;
  private Particle particle;
  private double value;

  @JsonCreator(mode = Mode.PROPERTIES)
  public AQLog(@JsonProperty("location") GLocation location, @JsonProperty("unit") MeasureUnit unit,
      @JsonProperty("particle") Particle particle, @JsonProperty("value") double value) {
    this.location = location;
    this.unit = unit;
    this.particle = particle;
    this.value = value;
  }

  public GLocation getLocation() {
    return location;
  }

  public MeasureUnit getUnit() {
    return unit;
  }

  public Particle getParticle() {
    return particle;
  }

  public double getValue() {
    return value;
  }

  @Override
  public boolean equals(Object aqLog) {
    return (aqLog instanceof AQLog)
        && Double.compare(((AQLog)aqLog).value, value) == 0
        && Objects.equals(location, ((AQLog)aqLog).location)
        && unit == ((AQLog)aqLog).unit
        && particle == ((AQLog)aqLog).particle;
  }

}
