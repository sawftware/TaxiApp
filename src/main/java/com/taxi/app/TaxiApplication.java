package com.taxi.app;

import com.taxi.app.entity.security.Role;
import com.taxi.app.entity.security.User;
import com.taxi.app.repository.security.RoleRepository;
import com.taxi.app.repository.security.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class TaxiApplication {

	private static final Logger logger = LoggerFactory.getLogger(TaxiApplication.class);

	public static void main(final String[] args) {
		SpringApplication.run(TaxiApplication.class, args);
		logger.info("TaxiApp: Application started");
	}

	@Component
	public static class ApplicationRunner implements CommandLineRunner {

		@Autowired
		private UserRepository userRepository;

		@Autowired
		private RoleRepository roleRepository;

		@Autowired
		private BCryptPasswordEncoder bCryptPasswordEncoder;

		@Override
		public void run(final String... args) {
			logger.debug("ApplicationRunner (CommandLineRunner): Executing run()");

			final Set<Role> roles = new HashSet<>();
			roles.add(roleRepository.findOneByName("ROLE_ADMIN"));

			final User newAdmin = User.builder()
					.username("admin")
					.password(bCryptPasswordEncoder.encode("admin"))
					.roles(new HashSet<>(roles)).build();

			userRepository.save(newAdmin);
		}
	}
}
