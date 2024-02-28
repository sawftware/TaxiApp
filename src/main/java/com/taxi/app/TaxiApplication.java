package com.taxi.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TaxiApplication {

	private static final Logger logger = LoggerFactory.getLogger(TaxiApplication.class);

	public static void main(final String[] args) {
		SpringApplication.run(TaxiApplication.class, args);
		logger.info("TaxiApp: Application started");
	}
}
