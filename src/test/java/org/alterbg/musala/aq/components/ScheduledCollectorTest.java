package org.alterbg.musala.aq.components;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;

import java.time.Duration;
import org.alterbg.musala.aq.ScheduledTestConfig;
import org.alterbg.musala.aq.components.collector.AQScheduledCollector;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {ScheduledTestConfig.class})
public class ScheduledCollectorTest {

  @Autowired
  private AQScheduledCollector collector;

  @Test
  @DisplayName("Collector creation")
  void shouldCreateTHeScheduledCollector() {
    assertThat(collector).isNotNull();
  }

  @Test
  @DisplayName("Collector retrieval")
  void shouldRetrieveForecastEachSecond() {
    await()
        .atLeast(Duration.ofSeconds(3))
        .untilAsserted(() -> assertThat(collector.retrievals()).hasValueGreaterThan(5));
  }
}
