package org.alterbg.musala.aq.components.collector;

import com.fasterxml.jackson.databind.JsonNode;
import org.alterbg.musala.aq.bean.AQLog;
import org.alterbg.musala.aq.bean.GLocation;
import org.alterbg.musala.aq.bean.MeasureUnit;
import org.alterbg.musala.aq.bean.Particle;
import org.springframework.stereotype.Component;

@Component
public class AQDataTransformer {

  public AQLog toAQLog(JsonNode aqForecast) {
    return new AQLog(new GLocation(-23.8351, 151.254), MeasureUnit.µgm3, Particle.co, 10);
  }
}
