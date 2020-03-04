package org.alterbg.musala.aq;

import org.alterbg.musala.aq.components.publisher.AQDataPublisher;
import org.alterbg.musala.aq.components.status.AQDataApi;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@ComponentScan(excludeFilters = @Filter(type = FilterType.ANNOTATION, classes = Scheduled.class))
public class PublisherTestConfig {

}
