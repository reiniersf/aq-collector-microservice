package org.alterbg.musala.aq;

import org.alterbg.musala.aq.components.collector.AQScheduledCollector;
import org.alterbg.musala.aq.config.BeansConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
@ComponentScan(basePackageClasses = AQScheduledCollector.class)
@Import(BeansConfig.class)
@Profile("test")
public class ScheduledTestConfig {

}
