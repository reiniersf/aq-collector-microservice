package org.alterbg.musala.aq.bean;

public class AQLog {

  private GLocation location;
  private MeasureUnit unit;
  private Particle particle;
  private double value;

  public AQLog(GLocation location, MeasureUnit unit, Particle particle, double value) {
    this.location = location;
    this.unit = unit;
    this.particle = particle;
    this.value = value;
  }

  public AQLog() {

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
}
