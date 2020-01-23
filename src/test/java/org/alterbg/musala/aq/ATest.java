package org.alterbg.musala.aq;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ATest {
  Logger testLogger = LoggerFactory.getLogger("Test");


  @Test
  void should(){
    testLogger.debug("Dummy test!");
    Assertions.assertThat(true).isEqualTo(true);
  }
}
