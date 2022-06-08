package br.com.benefrancis.empregados.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.benefrancis.empregados.entity.Empregado;
import br.com.benefrancis.empregados.service.ServiceEmpregado;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/empregado")
public class EmpregadosController {

	@Autowired
	private ServiceEmpregado service;

	@GetMapping
	private List<Empregado> findAll() {
		return service.findAll();
	}

	@PostMapping
	private ResponseEntity<Empregado> save(@RequestBody @Valid Empregado e) {

		if (!existe(e)) {
			Empregado resp = service.save(e);

			if (resp == null) {
				return ResponseEntity.badRequest().build();

			} else {
				return ResponseEntity.ok(resp);
			}
		} else {
			return ResponseEntity.ok(service.findByEmail(e.getEmail()).orElse(null));
		}

	}

	private boolean existe(Empregado e) {
		Empregado resp = service.findByEmail(e.getEmail()).orElse(null);

		if (resp != null) {
			return true;
		}
		return false;
	}

	@GetMapping(value = "/nome/{nome}")
	private List<Empregado> findByNome(@PathVariable String nome) {
		return service.findByNome(nome).orElse(null);
	}

	@GetMapping(value = "/{id}")
	private ResponseEntity<Empregado> findById(@PathVariable String id) {

		Empregado resp = service.findById(id).orElse(null);

		if (resp == null) {
			return ResponseEntity.notFound().build();

		} else {
			return ResponseEntity.ok(resp);
		}

	}
}
