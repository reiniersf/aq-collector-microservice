package org.alterbg.musala.aq.config;

import okhttp3.OkHttpClient.Builder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
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
}
