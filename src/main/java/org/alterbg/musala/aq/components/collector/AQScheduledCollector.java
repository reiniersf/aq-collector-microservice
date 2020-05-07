package org.alterbg.musala.aq.components.collector;

import com.fasterxml.jackson.databind.JsonNode;
import java.util.concurrent.atomic.AtomicLong;
import org.alterbg.musala.aq.api.DataLog;
import org.alterbg.musala.aq.api.DataTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class AQScheduledCollector {

  private final RestTemplate client;
  private final DataTransformer<DataLog> aqDataTransformer;
  private final ApplicationEventPublisher publisher;
  private final AtomicLong retrievals = new AtomicLong(0);

  @Autowired
  public AQScheduledCollector(
      RestTemplate client,
      DataTransformer<DataLog> aqDataTransformer,
      ApplicationEventPublisher publisher) {

    this.client = client;
    this.aqDataTransformer = aqDataTransformer;
    this.publisher = publisher;
  }

  @Scheduled(fixedDelay = 1000)
  public void collect() {
    ResponseEntity<JsonNode> entity = client.getForEntity(
        "http://api.waqi.info/feed/sofia/?token=9dd2d482c69d4abad3c6a5daafd1dedec98fbb9e",
        JsonNode.class);

    retrievals.incrementAndGet();
    publisher.publishEvent(aqDataTransformer.toDataLog(entity.getBody()));
  }

  public AtomicLong retrievals() {
    return this.retrievals;
  }
}
