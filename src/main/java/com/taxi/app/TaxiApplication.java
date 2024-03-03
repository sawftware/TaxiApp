package com.taxi.app;

import java.util.Set;
import org.slf4j.Logger;
import java.util.HashSet;
import org.slf4j.LoggerFactory;
import com.taxi.app.entity.security.Role;
import com.taxi.app.entity.security.User;
import org.springframework.stereotype.Component;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import com.taxi.app.repository.security.RoleRepository;
import com.taxi.app.repository.security.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * TaxiApplication
 *
 * @author alankavanagh
 *
 * Spring Boot Application entry point
 */
@SpringBootApplication
public class TaxiApplication {

	private static final Logger logger = LoggerFactory.getLogger(TaxiApplication.class);

	public static void main(final String[] args) {
		SpringApplication.run(TaxiApplication.class, args);
	}

	/**
	 * ApplicationRunner
	 *
	 * @author alankavanagh
	 *
	 * Creates the default admin/admin account on application start
	 */
	@Component
	public static class ApplicationRunner implements CommandLineRunner {

		@Autowired
		private UserRepository userRepository;

		@Autowired
		private RoleRepository roleRepository;

		@Autowired
		private BCryptPasswordEncoder bCryptPasswordEncoder;

		private static final String ADMIN_USERNAME = "admin";
		private static final String ADMIN_PASSWORD = "admin";

		@Override
		public void run(final String... args) {
			logger.debug("ApplicationRunner (CommandLineRunner): Executing run()");

			final Set<Role> roles = new HashSet<>();
			roles.add(roleRepository.findByName("ROLE_ADMIN"));

			final User newAdmin = User.builder()
					.username(ADMIN_USERNAME)
					.password(bCryptPasswordEncoder.encode(ADMIN_PASSWORD))
					.roles(new HashSet<>(roles)).build();

			userRepository.save(newAdmin);
		}
	}
}
