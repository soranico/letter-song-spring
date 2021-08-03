package com.kanozz.configclass.parse.property;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@ComponentScan("com.kanozz.configclass.parse.property")
@PropertySource(value = {"classpath:application-property-source.properties","classpath:application-property-source-2.properties"})
class KanoConfigurationProperty {





}
