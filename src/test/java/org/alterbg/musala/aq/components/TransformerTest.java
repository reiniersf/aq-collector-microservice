package org.alterbg.musala.aq.components;

import static org.assertj.core.api.Assertions.assertThat;

import org.alterbg.musala.aq.AQCollectorApplication;
import org.alterbg.musala.aq.components.collector.AQDataTransformer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {AQCollectorApplication.class})
public class TransformerTest {

  @Autowired
  private AQDataTransformer transformer;

  @Test
  @DisplayName("Transformer creation")
  void shouldCreateTransformer() {
    assertThat(transformer).isNotNull();
  }
}
