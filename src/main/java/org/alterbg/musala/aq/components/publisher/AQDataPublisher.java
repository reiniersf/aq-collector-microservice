package org.alterbg.musala.aq.components.publisher;

import org.alterbg.musala.aq.bean.AQILog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class AQDataPublisher {

  private KafkaTemplate<Integer, AQILog> kafkaTemplate;

  @Autowired
  public AQDataPublisher(KafkaTemplate<Integer, AQILog> kafkaTemplate) {
    this.kafkaTemplate = kafkaTemplate;
  }

  @EventListener
  public void pushNewAQLog(AQILog AQILog) {
    kafkaTemplate.sendDefault(AQILog);
  }
}
