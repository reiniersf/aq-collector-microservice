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
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest(classes = {ScheduledTestConfig.class})
public class ScheduledCollectorTest {

  private final AQScheduledCollector collector;

  @Autowired
  public ScheduledCollectorTest(
      AQScheduledCollector collector) {
    this.collector = collector;
  }

  @Test
  @DisplayName("Collector creation")
  void shouldCreateTHeScheduledCollector() {
    assertThat(collector).isNotNull();
  }

  @Test
  @DisplayName("Collector retrieval")
  void shouldRetrieveForecastEachSecond() {
    await()
        .atLeast(Duration.ofSeconds(6))
        .untilAsserted(() -> assertThat(collector.retrievals()).hasValueGreaterThan(5));
  }
}
