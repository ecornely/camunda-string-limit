package be.nrb.o2.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import be.nrb.o2.ProcessDelegate;

@Configuration
@ComponentScan(basePackageClasses= {ProcessDelegate.class})
public class StringLimitConfiguration {
  
}
