package org.alterbg.musala.aq.components;

import static java.util.Objects.requireNonNull;
import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import org.alterbg.musala.aq.bean.AQILog;
import org.alterbg.musala.aq.components.collector.AQDataTransformer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TransformerTest {

  private AQDataTransformer transformer = new AQDataTransformer();

  @Test
  @DisplayName("Should Transform a AQ Forecast")
  void shouldTransformForecastGeoLocationToGLocationObject() throws IOException {
    JsonNode forecast = new ObjectMapper().readTree(
        requireNonNull(this.getClass().getClassLoader().getResource("AqForecast.json")));

    AQILog aqiLog = transformer.toDataLog(forecast);

    assertThat(aqiLog).isNotNull();
  }
}
