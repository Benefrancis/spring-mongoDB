package br.com.benefrancis.empregados;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequestMapping(value = "/")
public class EmpregadosApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmpregadosApplication.class, args);

	}

	@GetMapping
	private String getRoot() {
		return "works";
	}

}
