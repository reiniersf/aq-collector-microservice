package org.alterbg.musala.aq;

import org.alterbg.musala.aq.components.collector.AQScheduledCollector;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(excludeFilters = @Filter(type = FilterType.ASSIGNABLE_TYPE, classes = AQScheduledCollector.class))
public class PublisherTestConfig {

}
