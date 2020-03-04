package org.alterbg.musala.aq.config;

import org.alterbg.musala.aq.components.collector.AQScheduledCollector;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
@ComponentScan(basePackageClasses = AQScheduledCollector.class)
@Import(BeansConfig.class)
public class ScheduledTestConfig {

}
