package org.alterbg.musala.aq.bean;

public class GLocation {

  private double latitude;
  private double longitude;

  public GLocation(double latitude, double longitude) {
    this.latitude = latitude;
    this.longitude = longitude;
  }

  public double getLatitude() {
    return latitude;
  }

  public double getLongitude() {
    return longitude;
  }
}
