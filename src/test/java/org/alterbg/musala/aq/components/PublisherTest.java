package org.alterbg.musala.aq.components;

import static java.util.Collections.singleton;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;
import org.alterbg.musala.aq.AQCollectorApplication;
import org.alterbg.musala.aq.bean.AQLog;
import org.alterbg.musala.aq.bean.GLocation;
import org.alterbg.musala.aq.bean.MeasureUnit;
import org.alterbg.musala.aq.bean.Particle;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.KafkaTestUtils;

@SpringBootTest(classes = {AQCollectorApplication.class})
@EmbeddedKafka(topics = {PublisherTest.TEST_TOPIC}, ports = 9092)
public class PublisherTest {

  private static final Logger TEST_LOGGER = LoggerFactory.getLogger("Test");
  public static final String TEST_TOPIC = "AQ";

  @Autowired
  private EmbeddedKafkaBroker embeddedKafkaBroker;

  @Autowired
  private AQDataPublisher publisher;

  private Consumer<Integer, AQLog> consumer;

  @BeforeEach
  void setUpConsumer() {
    consumer = configureConsumer();

  }

  @Test
  @DisplayName("Publisher Creation")
  void shouldCreatePublisher() {
    assertThat(publisher).isNotNull();
  }

  @Test
  @DisplayName("Publisher sending the message")
  void shouldPublishAMessage() {
    //GIVEN
    AQLog aqLog = new AQLog(new GLocation(-23.8351, 151.254), MeasureUnit.µgm3, Particle.co, 13);
    //WHEN
    publisher.pushNewAQLog(aqLog);
    //THEN
    assertThatLogReceivedHasValue(aqLog);
  }

  private void assertThatLogReceivedHasValue(AQLog expectedLog) {
    ConsumerRecord<Integer, AQLog> singleRecord = KafkaTestUtils
        .getSingleRecord(consumer, TEST_TOPIC);
    assertThat(singleRecord).isNotNull();
    assertThat(singleRecord.value()).isEqualTo(expectedLog);
  }

  private Consumer<Integer, AQLog> configureConsumer() {
    Map<String, Object> consumerProps = KafkaTestUtils
        .consumerProps("testGroup", "true", embeddedKafkaBroker);
    consumerProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
    consumerProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
    consumerProps.put(JsonDeserializer.TRUSTED_PACKAGES, "org.alterbg.musala.aq.bean");
    Consumer<Integer, AQLog> consumer = new DefaultKafkaConsumerFactory<Integer, AQLog>(
        consumerProps)
        .createConsumer();
    consumer.subscribe(singleton(TEST_TOPIC));
    return consumer;
  }
}
