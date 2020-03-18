package org.alterbg.musala.aq.components.status;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/status")
public class AQDataApi {

  @GetMapping
  public String status(){
    return "Hello from AQIApplication";
  }
}
