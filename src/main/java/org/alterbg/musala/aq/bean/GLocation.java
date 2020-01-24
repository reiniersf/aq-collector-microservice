package org.alterbg.musala.aq.bean;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Objects;

public class GLocation {

  private double latitude;
  private double longitude;

  @JsonCreator(mode = Mode.PROPERTIES)
  public GLocation(@JsonProperty("latitude") double latitude,
      @JsonProperty("longitude") double longitude) {
    this.latitude = latitude;
    this.longitude = longitude;
  }

  public double getLatitude() {
    return latitude;
  }

  public double getLongitude() {
    return longitude;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    GLocation gLocation = (GLocation) o;
    return Double.compare(gLocation.latitude, latitude) == 0 &&
        Double.compare(gLocation.longitude, longitude) == 0;
  }

  @Override
  public int hashCode() {
    return Objects.hash(latitude, longitude);
  }
}
