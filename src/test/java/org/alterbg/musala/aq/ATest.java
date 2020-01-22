package org.alterbg.musala.aq;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ATest {

  @Test
  void should(){
    Logger logger = LoggerFactory.getLogger(Main.class);
    logger.debug("Dummy test!");
    Assertions.assertThat(true).isEqualTo(true);
  }
}
