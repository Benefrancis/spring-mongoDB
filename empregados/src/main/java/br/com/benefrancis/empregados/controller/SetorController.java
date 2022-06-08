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

import br.com.benefrancis.empregados.entity.Setor;
import br.com.benefrancis.empregados.repository.SetorRepository;

@RestController
@RequestMapping(value = "/setor")
public class SetorController {

	@Autowired
	private SetorRepository repo;

	@GetMapping
	private List<Setor> findAll() {
		return repo.findAll();
	}

	@PostMapping
	private ResponseEntity<Setor> save(@RequestBody Setor e) {

		Setor resp = repo.save(e);

		if (resp.equals(null)) {
			return ResponseEntity.internalServerError().build();

		} else {
			return ResponseEntity.ok(repo.save(e));
		}

	}

	@GetMapping(value = "/nome/{nome}")
	private List<Setor> findByNome(@PathVariable String nome) {
		return repo.findByNome(nome);
	}

	@GetMapping(value = "/{id}")
	private ResponseEntity<Setor> findById(@PathVariable String id) {

		Setor resp = repo.findById(id).orElse(null);

		if (resp == null) {
			return ResponseEntity.notFound().build();

		} else {
			return ResponseEntity.ok(resp);
		}

	}

}
