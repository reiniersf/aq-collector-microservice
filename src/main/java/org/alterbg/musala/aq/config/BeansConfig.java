package org.alterbg.musala.aq.config;

import java.util.HashMap;
import java.util.Map;
import okhttp3.OkHttpClient.Builder;
import org.alterbg.musala.aq.bean.AQLog;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.web.client.RestTemplate;

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

  @Bean
  private KafkaTemplate<Integer, AQLog> createTemplate(Map<String, Object> senderProps) {
    ProducerFactory<Integer, AQLog> pf =
        new DefaultKafkaProducerFactory<>(senderProps());
    return new KafkaTemplate<>(pf);
  }

  @Bean
  private Map<String, Object> senderProps() {
    Map<String, Object> props = new HashMap<>();
    props.put(ProducerConfig.RETRIES_CONFIG, 0);
    props.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
    props.put(ProducerConfig.LINGER_MS_CONFIG, 1);
    props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);
    return props;
  }

  @Bean
  @Qualifier("testLogger")
  public Logger testLogger() {
    return LoggerFactory.getLogger("Test");
  }
}
