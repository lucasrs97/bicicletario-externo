package com.example.echo.controller;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(MockitoExtension.class)
public class HelloWorldControllerTests {

    @InjectMocks
    HelloWorldController helloWorldController;

    @Test
    void getHelloWorld() {
        String retorno = helloWorldController.getHelloWorld("hello world");

        Assertions.assertEquals("hello world", retorno);
    }

}
