package org.alterbg.musala.aq.components;

import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Profile("default")
public class AQScheduledCollector {

  private final RestTemplate client;
  private final AQDataTransformer aqDataTransformer;
  private final ApplicationEventPublisher publisher;
  private final Logger logger = LoggerFactory.getLogger(AQScheduledCollector.class);

  @Autowired
  public AQScheduledCollector(
      RestTemplate client,
      AQDataTransformer aqDataTransformer,
      ApplicationEventPublisher publisher) {

    this.client = client;
    this.aqDataTransformer = aqDataTransformer;
    this.publisher = publisher;
  }

  @Scheduled(fixedDelay = 1000)
  public void work() {
    ResponseEntity<JsonNode> entity = client.getForEntity(
        "http://api.waqi.info/feed/sofia/?token=9dd2d482c69d4abad3c6a5daafd1dedec98fbb9e",
        JsonNode.class);

    publisher.publishEvent(aqDataTransformer.toAQLog(entity.getBody()));
    System.out.println(entity.getBody());
  }
}
