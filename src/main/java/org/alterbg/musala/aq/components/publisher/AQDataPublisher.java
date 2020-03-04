package org.alterbg.musala.aq.components.publisher;

import org.alterbg.musala.aq.bean.AQLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class AQDataPublisher {

  private KafkaTemplate<Integer, AQLog> kafkaTemplate;

  @Autowired
  public AQDataPublisher(KafkaTemplate<Integer, AQLog> kafkaTemplate) {
    this.kafkaTemplate = kafkaTemplate;
  }

  @EventListener
  public void pushNewAQLog(AQLog aqLog) {
    kafkaTemplate.sendDefault(aqLog);
  }
}
