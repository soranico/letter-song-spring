package com.kanozz.configclass.parse.imported.selector;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
@ComponentScan("com.kanozz.configclass.parse.imported.selector")
@Import(KanoImportSelector.class)
class KanoConfiguration {
}
