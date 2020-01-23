package org.alterbg.musala.aq.components;

import static org.assertj.core.api.Assertions.assertThat;

import org.alterbg.musala.aq.AQCollectorApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {AQCollectorApplication.class})
public class TransformerTest {

  @Autowired
  private AQDataTransformer transformer;

  @Test
  void shouldCreateTransformer() {
    assertThat(transformer).isNotNull();
  }
}
