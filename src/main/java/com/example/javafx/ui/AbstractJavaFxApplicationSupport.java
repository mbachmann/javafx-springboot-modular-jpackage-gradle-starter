
package com.example.javafx.ui;


import com.example.javafx.JavaFxSpringApp;
import javafx.application.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;

@SuppressWarnings("restriction")
public abstract class AbstractJavaFxApplicationSupport extends Application {


	private static final Logger logger = LoggerFactory.getLogger(JavaFxSpringApp.class);
	protected static String[] savedArgs;
	protected ConfigurableApplicationContext applicationContext;

	@Override
	public void init() {

	}

	@Override
	public void stop() throws Exception {

		super.stop();
		applicationContext.close();
	}

	protected static void launchApp(Class<? extends AbstractJavaFxApplicationSupport> appClass, String[] args) {

		AbstractJavaFxApplicationSupport.savedArgs = args;
		Application.launch(appClass, args);
	}
}
