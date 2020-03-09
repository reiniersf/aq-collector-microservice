package org.alterbg.musala.aq.components.collector;

import static java.util.Arrays.stream;
import static org.alterbg.musala.aq.bean.GLocation.GLocation;
import static org.alterbg.musala.aq.bean.Measure.newMeasure;
import static org.alterbg.musala.aq.bean.MeasureUnit.aqi;
import static org.alterbg.musala.aq.bean.Particle.no2;
import static org.alterbg.musala.aq.bean.Particle.o3;
import static org.alterbg.musala.aq.bean.Particle.pm10;
import static org.alterbg.musala.aq.bean.Particle.valueOf;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collector;
import org.alterbg.musala.aq.bean.AQILog;
import org.alterbg.musala.aq.bean.GLocation;
import org.alterbg.musala.aq.bean.Measure;
import org.alterbg.musala.aq.bean.MeasureUnit;
import org.alterbg.musala.aq.bean.Measures;
import org.alterbg.musala.aq.bean.Particle;
import org.springframework.stereotype.Component;

@Component
public class AQDataTransformer {

  public AQILog toAQLog(JsonNode aqForecast) {

    Integer generalAQIndex = extractFrom(aqForecast, "/data/aqi", JsonNode::asInt);
    GLocation gLocation = extractFrom(aqForecast, "/data/city/geo", asGLocation);
    Particle dominantPollution = extractFrom(aqForecast, "/data/dominentpol", asParticle);
    Measures particlesMeasures = extractFrom(aqForecast, "/data/iaqi",
        asParticleMeasuresFor(no2, o3, pm10));

    return new AQILog(gLocation, generalAQIndex, dominantPollution, particlesMeasures);
  }
  private Function<JsonNode, Measures> asParticleMeasuresFor(Particle... particles) {
    return particleIndexes -> stream(particles)
        .map(particle -> anMeasure(particle, aqi, particleIndexes.at("/" + particle + "/v").asDouble()))
        .collect(asMeasures());
  }

  private Measure anMeasure(Particle particle, MeasureUnit unit,
      double index) {
    return newMeasure(particle, unit, index);
  }

  private Function<JsonNode, GLocation> asGLocation = coordinatesNode -> {
    ArrayNode coordinatesArrayNode = (ArrayNode) coordinatesNode;
    return GLocation(new double[]{
        coordinatesArrayNode.get(0).asDouble(),
        coordinatesArrayNode.get(1).asDouble()
    });
  };

  private Function<JsonNode, Particle> asParticle = particleNode -> valueOf(particleNode.asText());

  private <T> T extractFrom(JsonNode source, String requestedPath,
      Function<JsonNode, T> transformer) {
    return transformer.apply(source.at(requestedPath));
  }

  private static Collector<Measure, Measures, Measures> asMeasures() {
    return Collector.of(Measures::newMeasures, Measures::record,
        (measures, measures2) -> measures2);
  }
}
