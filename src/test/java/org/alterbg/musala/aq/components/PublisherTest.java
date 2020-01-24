package org.alterbg.musala.aq.components;

import static org.assertj.core.api.Assertions.assertThat;

import org.alterbg.musala.aq.AQCollectorApplication;
import org.alterbg.musala.aq.bean.AQLog;
import org.alterbg.musala.aq.bean.GLocation;
import org.alterbg.musala.aq.bean.MeasureUnit;
import org.alterbg.musala.aq.bean.Particle;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;

@SpringBootTest(classes = {AQCollectorApplication.class})
@EmbeddedKafka(topics = {"AQ"}, ports = 9092)
public class PublisherTest {

  private static final Logger TEST_LOGGER = LoggerFactory.getLogger("Test");

  @Autowired
  private EmbeddedKafkaBroker embeddedKafkaBroker;

  @Autowired
  private AQDataPublisher publisher;

  @Autowired
  private KafkaTemplate<Integer, AQLog> template;

  @Test
  @DisplayName("Publisher Creation")
  void shouldCreatePublisher() {
    assertThat(publisher).isNotNull();
  }

  @Test
  @DisplayName("Publisher sending the message")
  void shouldPublishAMessage() {
    publisher.pushNewAQLog(new AQLog(new GLocation(-23.8351, 151.254), MeasureUnit.µgm3, Particle.co, 13));
  }

}
