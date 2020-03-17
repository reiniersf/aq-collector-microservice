package org.alterbg.musala.aq.bean;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

public class AQILog {

  private GLocation location;
  private final Integer generalAQIndex;
  private Particle dominantPollution;
  private final Measures particlesMeasures;

  @JsonCreator(mode = Mode.PROPERTIES)
  public AQILog(@JsonProperty("location") GLocation gLocation,
      @JsonProperty("aqi") Integer generalAQIndex,
      @JsonProperty("dominant") Particle dominantPollution,
      @JsonProperty("measures") Measures particlesMeasures) {

    this.location = gLocation;
    this.generalAQIndex = generalAQIndex;
    this.dominantPollution = dominantPollution;
    this.particlesMeasures = particlesMeasures;
  }

  public GLocation getLocation() {
    return location;
  }

  public Particle getDominantPollution() {
    return dominantPollution;
  }

  public Integer getGeneralAQIndex() {
    return generalAQIndex;
  }

  public Measures getParticlesMeasures() {
    return particlesMeasures;
  }

  @Override
  public boolean equals(Object aqLog) {
    return (aqLog instanceof AQILog)
        && Objects.equals(location, ((AQILog) aqLog).location)
        && dominantPollution == ((AQILog) aqLog).dominantPollution;
  }

  @Override
  public String toString() {
    return "AQILog{\n location=" + location + ",\n generalAQIndex=" + generalAQIndex
        + ",\n dominantPollution=" + dominantPollution + ",\n particlesMeasures="
        + particlesMeasures + '}';
  }
}
