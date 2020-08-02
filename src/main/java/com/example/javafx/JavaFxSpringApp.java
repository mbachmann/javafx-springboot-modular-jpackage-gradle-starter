package com.example.javafx;

import com.example.javafx.ui.JavaFxApp;
import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JavaFxSpringApp {

	public static void main(String[] args) {

		System.out.println("** Java App Starting ** ");
		System.setProperty("prism.lcdtext", "false");
		Application.launch(JavaFxApp.class, args);
	}
}
