package org.alterbg.musala.aq.bean;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

public class AQLog {

  @JsonProperty
  private GLocation location;
  @JsonProperty
  private MeasureUnit unit;
  @JsonProperty
  private Particle particle;
  @JsonProperty
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
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AQLog aqLog = (AQLog) o;
    return Double.compare(aqLog.value, value) == 0 &&
        Objects.equals(location, aqLog.location) &&
        unit == aqLog.unit &&
        particle == aqLog.particle;
  }

  @Override
  public int hashCode() {
    return Objects.hash(location, unit, particle, value);
  }
}
