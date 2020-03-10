package org.alterbg.musala.aq.bean;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Set;

public class Measures {

  private Set<Measure> measures;

  @JsonCreator(mode = Mode.PROPERTIES)
  public Measures(@JsonProperty("measuresSet") Set<Measure> measures) {
    this.measures = measures;
  }

  public static Measures newMeasures(Set<Measure> particleMeasures){
    return new Measures(particleMeasures);
  }

  public Set<Measure> getMeasures() {
    return measures;
  }

  @Override
  public String toString() {
    return "Measures{" +
        "measures=" + measures +
        '}';
  }
}
