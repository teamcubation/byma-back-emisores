package com.byma.emisor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

@SpringBootApplication
public class EmisorApplication {

	public static void main(String[] args) {
		try (Stream<String> stream = Files.lines(Paths.get(".env"))) {
			stream.forEach(line -> {
				String[] keyValue = line.split("=", 2);
				if (keyValue.length == 2) {
					System.setProperty(keyValue[0], keyValue[1]);
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
		}

		SpringApplication.run(EmisorApplication.class, args);
	}

}
