package org.alterbg.musala.aq.components;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.alterbg.musala.aq.AQCollectorApplication;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(classes = {AQCollectorApplication.class})
@AutoConfigureMockMvc
public class ApiTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  @DisplayName("Controller default endpoint should return OK status")
  void shouldTestOk() throws Exception {
    mockMvc.perform(get("/status"))
        .andExpect(status().isOk());
  }

}
