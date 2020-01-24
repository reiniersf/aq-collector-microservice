package org.alterbg.musala.aq.components;

import org.alterbg.musala.aq.bean.AQLog;
import org.alterbg.musala.aq.bean.GLocation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class AQDataPublisher {

  private KafkaTemplate<Integer, AQLog> kafkaTemplate;

  @Autowired
  public AQDataPublisher(KafkaTemplate<Integer, AQLog> kafkaTemplate) {
    this.kafkaTemplate = kafkaTemplate;
  }

  public void pushNewAQLog(AQLog aqLog) {
    kafkaTemplate.send("AQ", aqLog);
  }
}
