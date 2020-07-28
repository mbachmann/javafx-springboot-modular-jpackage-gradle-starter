package com.example.javafx.service.impl;

import com.example.javafx.service.TestService;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {

    public String testMethod() {

        return "Hello";
    }


}
