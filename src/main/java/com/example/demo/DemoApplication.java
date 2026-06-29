package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;

import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.HexFormat;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner testHashing() {
		return args -> {

			String password = "utn_2026";

			System.out.println("===== MD5 =====");
			md5Test(password);

			System.out.println("\n===== BCrypt =====");
			bcryptTest(password);

			System.out.println("\n===== Argon2 =====");
			argon2Test(password);

			System.out.println("\n===== SALT + PEPPER =====");
			saltPepperTest(password);
		};
	}

	private void md5Test(String password) throws Exception {
		MessageDigest md = MessageDigest.getInstance("MD5");

		byte[] hash1 = md.digest(password.getBytes());
		byte[] hash2 = md.digest(password.getBytes());

		System.out.println("Hash 1: " + HexFormat.of().formatHex(hash1));
		System.out.println("Hash 2: " + HexFormat.of().formatHex(hash2));
	}

	private void bcryptTest(String password) {
		int[] rounds = {4, 10, 15};

		for (int r : rounds) {
			long start = System.currentTimeMillis();

			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(r);
			String hash = encoder.encode(password);

			long end = System.currentTimeMillis();

			System.out.println("Rounds: " + r);
			System.out.println("Hash: " + hash);
			System.out.println("Tiempo: " + (end - start) + " ms\n");
		}
	}

	private void argon2Test(String password) {
		Argon2PasswordEncoder encoder = new Argon2PasswordEncoder(
				16,
				32,
				1,
				65536,
				3
		);

		String hash = encoder.encode(password);

		System.out.println("Hash: " + hash);
	}

	private void saltPepperTest(String password) throws Exception {

		String pepper = "SECRETO_APP";

		byte[] saltBytes = new byte[16];
		SecureRandom random = new SecureRandom();
		random.nextBytes(saltBytes);

		String salt = Base64.getEncoder().encodeToString(saltBytes);

		String finalPassword = password + salt + pepper;

		MessageDigest md = MessageDigest.getInstance("SHA-256");
		byte[] hash = md.digest(finalPassword.getBytes());

		String hashFinal = HexFormat.of().formatHex(hash);

		System.out.println("password_hash: " + hashFinal);
		System.out.println("salt: " + salt);
	}
}

