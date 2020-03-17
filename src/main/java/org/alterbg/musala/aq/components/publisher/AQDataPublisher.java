package org.alterbg.musala.aq.components.publisher;

import org.alterbg.musala.aq.bean.AQILog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class AQDataPublisher {

  private KafkaTemplate<Integer, AQILog> kafkaTemplate;
  private final Logger logger = LoggerFactory.getLogger(AQDataPublisher.class);

  @Autowired
  public AQDataPublisher(KafkaTemplate<Integer, AQILog> kafkaTemplate) {
    this.kafkaTemplate = kafkaTemplate;
  }

  @EventListener
  public void pushNewAQLog(AQILog AQILog) {
    logger.info("Transformed Log: {}", AQILog);
    kafkaTemplate.sendDefault(AQILog);
  }
}
