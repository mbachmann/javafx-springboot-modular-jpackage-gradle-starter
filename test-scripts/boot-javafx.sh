#!/usr/bin/env bash
java -Dfile.encoding=UTF-8 -Duser.country=CH -Duser.language=de -Duser.variant -cp /Users/mbach/develop/tst/javafx-springboot-modular-jpackage/build/classes/java/main:/Users/mbach/.m2/repository/javax/servlet/javax.servlet-api/4.0.1/javax.servlet-api-4.0.1.jar:/Users/mbach/.gradle/caches/modules-2/files-2.1/org.springframework.boot/spring-boot-starter-actuator/2.2.4.RELEASE/42883d44298a034924038ca299abd9a581e03b57/spring-boot-starter-actuator-2.2.4.RELEASE.jar:/Users/mbach/.m2/repository/org/springframework/boot/spring-boot-starter-data-jpa/2.2.4.RELEASE/spring-boot-starter-data-jpa-2.2.4.RELEASE.jar:/Users/mbach/.m2/repository/org/springframework/boot/spring-boot-starter-aop/2.2.4.RELEASE/spring-boot-starter-aop-2.2.4.RELEASE.jar:/Users/mbach/.m2/repository/org/springframework/boot/spring-boot-starter-jdbc/2.2.4.RELEASE/spring-boot-starter-jdbc-2.2.4.RELEASE.jar:/Users/mbach/.m2/repository/org/springframework/boot/spring-boot-starter/2.2.4.RELEASE/spring-boot-starter-2.2.4.RELEASE.jar:/Users/mbach/.m2/repository/org/openjfx/javafx-fxml/13/javafx-fxml-13-mac.jar:/Users/mbach/.m2/repository/org/openjfx/javafx-controls/13/javafx-controls-13-mac.jar:/Users/mbach/.m2/repository/org/openjfx/javafx-controls/13/javafx-controls-13.jar:/Users/mbach/.m2/repository/org/openjfx/javafx-graphics/13/javafx-graphics-13-mac.jar:/Users/mbach/.m2/repository/org/openjfx/javafx-graphics/13/javafx-graphics-13.jar:/Users/mbach/.m2/repository/org/openjfx/javafx-base/13/javafx-base-13-mac.jar:/Users/mbach/.m2/repository/org/openjfx/javafx-base/13/javafx-base-13.jar:/Users/mbach/.m2/repository/com/h2database/h2/1.4.200/h2-1.4.200.jar:/Users/mbach/.gradle/caches/modules-2/files-2.1/org.springframework.boot/spring-boot-actuator-autoconfigure/2.2.4.RELEASE/c2a37b0ca5c4d48474102278cf98fd05c10f4d76/spring-boot-actuator-autoconfigure-2.2.4.RELEASE.jar:/Users/mbach/.m2/repository/org/springframework/boot/spring-boot-autoconfigure/2.2.4.RELEASE/spring-boot-autoconfigure-2.2.4.RELEASE.jar:/Users/mbach/.gradle/caches/modules-2/files-2.1/org.springframework.boot/spring-boot-actuator/2.2.4.RELEASE/4f9cf08b5b238b8895c6663c9d0ead9a9ed1c264/spring-boot-actuator-2.2.4.RELEASE.jar:/Users/mbach/.m2/repository/org/springframework/boot/spring-boot/2.2.4.RELEASE/spring-boot-2.2.4.RELEASE.jar:/Users/mbach/.m2/repository/org/springframework/boot/spring-boot-starter-logging/2.2.4.RELEASE/spring-boot-starter-logging-2.2.4.RELEASE.jar:/Users/mbach/.m2/repository/jakarta/annotation/jakarta.annotation-api/1.3.5/jakarta.annotation-api-1.3.5.jar:/Users/mbach/.m2/repository/org/springframework/data/spring-data-jpa/2.2.4.RELEASE/spring-data-jpa-2.2.4.RELEASE.jar:/Users/mbach/.m2/repository/org/springframework/spring-context/5.2.3.RELEASE/spring-context-5.2.3.RELEASE.jar:/Users/mbach/.m2/repository/org/springframework/spring-aop/5.2.3.RELEASE/spring-aop-5.2.3.RELEASE.jar:/Users/mbach/.m2/repository/org/springframework/spring-aspects/5.2.3.RELEASE/spring-aspects-5.2.3.RELEASE.jar:/Users/mbach/.m2/repository/org/springframework/spring-orm/5.2.3.RELEASE/spring-orm-5.2.3.RELEASE.jar:/Users/mbach/.m2/repository/org/springframework/spring-jdbc/5.2.3.RELEASE/spring-jdbc-5.2.3.RELEASE.jar:/Users/mbach/.m2/repository/org/springframework/data/spring-data-commons/2.2.4.RELEASE/spring-data-commons-2.2.4.RELEASE.jar:/Users/mbach/.m2/repository/org/springframework/spring-tx/5.2.3.RELEASE/spring-tx-5.2.3.RELEASE.jar:/Users/mbach/.m2/repository/org/springframework/spring-beans/5.2.3.RELEASE/spring-beans-5.2.3.RELEASE.jar:/Users/mbach/.m2/repository/org/springframework/spring-expression/5.2.3.RELEASE/spring-expression-5.2.3.RELEASE.jar:/Users/mbach/.m2/repository/org/springframework/spring-core/5.2.3.RELEASE/spring-core-5.2.3.RELEASE.jar:/Users/mbach/.m2/repository/org/yaml/snakeyaml/1.25/snakeyaml-1.25.jar:/Users/mbach/.m2/repository/org/hibernate/hibernate-core/5.4.10.Final/hibernate-core-5.4.10.Final.jar:/Users/mbach/.m2/repository/org/glassfish/jaxb/jaxb-runtime/2.3.2/jaxb-runtime-2.3.2.jar:/Users/mbach/.m2/repository/org/jvnet/staxex/stax-ex/1.8.1/stax-ex-1.8.1.jar:/Users/mbach/.m2/repository/jakarta/xml/bind/jakarta.xml.bind-api/2.3.2/jakarta.xml.bind-api-2.3.2.jar:/Users/mbach/.m2/repository/com/sun/istack/istack-commons-runtime/3.0.8/istack-commons-runtime-3.0.8.jar:/Users/mbach/.m2/repository/jakarta/activation/jakarta.activation-api/1.2.1/jakarta.activation-api-1.2.1.jar:/Users/mbach/.m2/repository/jakarta/persistence/jakarta.persistence-api/2.2.3/jakarta.persistence-api-2.2.3.jar:/Users/mbach/.m2/repository/jakarta/transaction/jakarta.transaction-api/1.3.3/jakarta.transaction-api-1.3.3.jar:/Users/mbach/.m2/repository/io/micrometer/micrometer-core/1.3.2/micrometer-core-1.3.2.jar:/Users/mbach/.m2/repository/ch/qos/logback/logback-classic/1.2.3/logback-classic-1.2.3.jar:/Users/mbach/.m2/repository/org/apache/logging/log4j/log4j-to-slf4j/2.12.1/log4j-to-slf4j-2.12.1.jar:/Users/mbach/.m2/repository/org/slf4j/jul-to-slf4j/1.7.30/jul-to-slf4j-1.7.30.jar:/Users/mbach/.m2/repository/org/springframework/spring-jcl/5.2.3.RELEASE/spring-jcl-5.2.3.RELEASE.jar:/Users/mbach/.m2/repository/org/aspectj/aspectjweaver/1.9.5/aspectjweaver-1.9.5.jar:/Users/mbach/.m2/repository/com/zaxxer/HikariCP/3.4.2/HikariCP-3.4.2.jar:/Users/mbach/.m2/repository/org/hibernate/common/hibernate-commons-annotations/5.1.0.Final/hibernate-commons-annotations-5.1.0.Final.jar:/Users/mbach/.m2/repository/org/jboss/logging/jboss-logging/3.4.1.Final/jboss-logging-3.4.1.Final.jar:/Users/mbach/.m2/repository/org/javassist/javassist/3.24.0-GA/javassist-3.24.0-GA.jar:/Users/mbach/.m2/repository/net/bytebuddy/byte-buddy/1.10.6/byte-buddy-1.10.6.jar:/Users/mbach/.m2/repository/antlr/antlr/2.7.7/antlr-2.7.7.jar:/Users/mbach/.m2/repository/org/jboss/jandex/2.1.1.Final/jandex-2.1.1.Final.jar:/Users/mbach/.m2/repository/com/fasterxml/classmate/1.5.1/classmate-1.5.1.jar:/Users/mbach/.m2/repository/org/dom4j/dom4j/2.1.1/dom4j-2.1.1.jar:/Users/mbach/.m2/repository/org/slf4j/slf4j-api/1.7.30/slf4j-api-1.7.30.jar:/Users/mbach/.gradle/caches/modules-2/files-2.1/com.fasterxml.jackson.datatype/jackson-datatype-jsr310/2.10.2/8d95d3f2b20c7d2f598b6d5e7f4e2d688ac1bc78/jackson-datatype-jsr310-2.10.2.jar:/Users/mbach/.gradle/caches/modules-2/files-2.1/com.fasterxml.jackson.core/jackson-databind/2.10.2/528de95f198afafbcfb0c09d2e43b6e0ea663ec/jackson-databind-2.10.2.jar:/Users/mbach/.m2/repository/org/hdrhistogram/HdrHistogram/2.1.11/HdrHistogram-2.1.11.jar:/Users/mbach/.m2/repository/org/latencyutils/LatencyUtils/2.0.3/LatencyUtils-2.0.3.jar:/Users/mbach/.m2/repository/ch/qos/logback/logback-core/1.2.3/logback-core-1.2.3.jar:/Users/mbach/.m2/repository/org/apache/logging/log4j/log4j-api/2.12.1/log4j-api-2.12.1.jar:/Users/mbach/.m2/repository/org/glassfish/jaxb/txw2/2.3.2/txw2-2.3.2.jar:/Users/mbach/.m2/repository/com/sun/xml/fastinfoset/FastInfoset/1.2.16/FastInfoset-1.2.16.jar:/Users/mbach/.gradle/caches/modules-2/files-2.1/com.fasterxml.jackson.core/jackson-annotations/2.10.2/3a13b6105946541b8d4181a0506355b5fae63260/jackson-annotations-2.10.2.jar:/Users/mbach/.gradle/caches/modules-2/files-2.1/com.fasterxml.jackson.core/jackson-core/2.10.2/73d4322a6bda684f676a2b5fe918361c4e5c7cca/jackson-core-2.10.2.jar com.example.javafx.JavaFxSpringApp
