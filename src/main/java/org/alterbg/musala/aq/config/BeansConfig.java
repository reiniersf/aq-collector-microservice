package org.alterbg.musala.aq.config;

import java.util.HashMap;
import java.util.Map;
import okhttp3.OkHttpClient.Builder;
import org.alterbg.musala.aq.bean.AQLog;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class BeansConfig {

  @Bean
  public ClientHttpRequestFactory httpRequestFactory() {
    Builder okHttpClientBuilder = new Builder();
    return new OkHttp3ClientHttpRequestFactory(okHttpClientBuilder.build());
  }

  @Bean
  public RestTemplate restTemplate(ClientHttpRequestFactory requestFactory) {
    return new RestTemplate(requestFactory);
  }

}
