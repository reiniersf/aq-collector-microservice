package org.alterbg.musala.aq.bean;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Measure {

  private final Particle particle;
  private final MeasureUnit unit;
  private final double index;

  public Measure(Particle particle, MeasureUnit unit, double index) {

    this.particle = particle;
    this.unit = unit;
    this.index = index;
  }

  @JsonCreator
  public static Measure newMeasure(@JsonProperty("particle") Particle particle, @JsonProperty("unit") MeasureUnit unit,
      @JsonProperty("index") double index) {
    return new Measure(particle, unit, index);
  }

  public Particle getParticle() {
    return particle;
  }

  public double getIndex() {
    return index;
  }

  public MeasureUnit getUnit() {
    return unit;
  }

  @Override
  public String toString() {
    return "Measure{" +
        "particle=" + particle +
        ", index=" + index +
        '}';
  }
}
