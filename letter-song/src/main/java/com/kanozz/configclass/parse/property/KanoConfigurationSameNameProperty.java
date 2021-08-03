package com.kanozz.configclass.parse.property;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@ComponentScan("com.kanozz.configclass.parse.property")
@PropertySources(value = {
		@PropertySource(name = "kano",value = "classpath:application-property-source.properties"),
		@PropertySource(name = "kano",value = "classpath:application-property-source-2.properties")}
)
class KanoConfigurationSameNameProperty {





}
