package org.alterbg.musala.aq.bean;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.HashSet;
import java.util.Set;

public class Measures {

  private Set<Measure> measures;

  @JsonCreator(mode = Mode.PROPERTIES)
  public Measures(@JsonProperty("measuresSet") Set<Measure> measures) {
    this.measures = measures;
  }

  public static Measures newMeasures(){
    return new Measures(new HashSet<>());
  }

  public void include(Measures measuresA) {
    measures.addAll(measuresA.measures);
  }

  public void record(Measure aMeasure) {
    measures.add(aMeasure);
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
