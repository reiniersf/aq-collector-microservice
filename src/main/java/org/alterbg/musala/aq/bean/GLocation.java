package org.alterbg.musala.aq.bean;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GLocation {

  private double latitude;
  private double longitude;

  @JsonCreator(mode = Mode.PROPERTIES)
  public GLocation(@JsonProperty("latitude") double latitude,
      @JsonProperty("longitude") double longitude) {
    this.latitude = latitude;
    this.longitude = longitude;
  }

  public static GLocation GLocation(double [] geo){
    return new GLocation(geo[0], geo[1]);
  }

  public double getLatitude() {
    return latitude;
  }

  public double getLongitude() {
    return longitude;
  }

  @Override
  public boolean equals(Object gLocation) {
    return (gLocation instanceof GLocation)
        && Double.compare(((GLocation) gLocation).latitude, latitude) == 0
        && Double.compare(((GLocation) gLocation).longitude, longitude) == 0;
  }
}
