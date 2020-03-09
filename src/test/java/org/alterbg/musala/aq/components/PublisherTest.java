package org.alterbg.musala.aq.components;

import static java.util.Collections.singleton;
import static org.alterbg.musala.aq.bean.Measure.newMeasure;
import static org.alterbg.musala.aq.bean.MeasureUnit.aqi;
import static org.alterbg.musala.aq.bean.MeasureUnit.ppm;
import static org.alterbg.musala.aq.bean.MeasureUnit.µgm3;
import static org.alterbg.musala.aq.bean.Particle.co;
import static org.alterbg.musala.aq.bean.Particle.no2;
import static org.alterbg.musala.aq.bean.Particle.pm10;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;
import java.util.Set;
import org.alterbg.musala.aq.PublisherTestConfig;
import org.alterbg.musala.aq.bean.AQILog;
import org.alterbg.musala.aq.bean.GLocation;
import org.alterbg.musala.aq.bean.MeasureUnit;
import org.alterbg.musala.aq.bean.Measures;
import org.alterbg.musala.aq.components.publisher.AQDataPublisher;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.KafkaTestUtils;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest(classes = {PublisherTestConfig.class})
@EmbeddedKafka(topics = {PublisherTest.TEST_TOPIC}, ports = 9093)
public class PublisherTest {

  public static final String TEST_TOPIC = "AQ";

  @Autowired
  private EmbeddedKafkaBroker embeddedKafkaBroker;

  @Autowired
  private AQDataPublisher publisher;

  private Consumer<Integer, AQILog> consumer;

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
    AQILog AQILog = new AQILog(new GLocation(-23.8351, 151.254), 20,
        pm10, new Measures(Set.of(
            newMeasure(co, aqi, 6.7),
            newMeasure(pm10, ppm, 6.7),
        newMeasure(no2, µgm3,7.2))));
    //WHEN
    publisher.pushNewAQLog(AQILog);
    //THEN
    assertThatLogsReceivedContainsValue(AQILog);
  }

  private void assertThatLogsReceivedContainsValue(AQILog expectedLog) {
    ConsumerRecords<Integer, AQILog> records = KafkaTestUtils
        .getRecords(consumer);
    assertThat(records.records(TEST_TOPIC))
        .anyMatch(aQLogConsumerRecord -> aQLogConsumerRecord.value().equals(expectedLog));
  }

  private Consumer<Integer, AQILog> configureConsumer() {
    Map<String, Object> consumerProps = KafkaTestUtils
        .consumerProps("testGroup", "true", embeddedKafkaBroker);
    consumerProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
    consumerProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
    consumerProps.put(JsonDeserializer.TRUSTED_PACKAGES, "org.alterbg.musala.aq.bean");
    Consumer<Integer, AQILog> consumer = new DefaultKafkaConsumerFactory<Integer, AQILog>(
        consumerProps)
        .createConsumer();
    consumer.subscribe(singleton(TEST_TOPIC));
    return consumer;
  }
}
