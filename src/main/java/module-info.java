module javafxspring {

    requires java.base;
    requires java.persistence;
    requires java.sql;
    requires java.xml.bind;
    requires java.management;
    requires java.transaction;
    requires java.annotation;
    requires java.instrument;
    requires jakarta.activation;

    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.graphics;

    requires spring.core;
    requires spring.beans;
    requires spring.context;
    requires spring.aop;
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.data.jpa;
    requires spring.data.commons;
    requires spring.expression;
    requires spring.tx;
    requires spring.orm;
    requires spring.boot.actuator;
    requires spring.boot.actuator.autoconfigure;
    requires org.hibernate.orm.core;

    requires jdk.unsupported;

    requires net.bytebuddy;

    requires com.fasterxml.classmate;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.databind;
    requires org.slf4j;

    opens com.example.javafx;
    opens com.example.javafx.domain;
    opens com.example.javafx.repository;
    opens com.example.javafx.config;
    opens com.example.javafx.service;
    opens com.example.javafx.service.impl;
    opens com.example.javafx.ui;

    exports com.example.javafx;
    exports com.example.javafx.domain;
    exports com.example.javafx.repository;
    exports com.example.javafx.config;
    exports com.example.javafx.service;
    exports com.example.javafx.service.impl;
    exports com.example.javafx.ui;

    requires java.logging;


}

