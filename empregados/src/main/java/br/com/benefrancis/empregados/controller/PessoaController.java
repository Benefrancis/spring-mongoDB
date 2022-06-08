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

import br.com.benefrancis.empregados.entity.Pessoa;
import br.com.benefrancis.empregados.repository.PessoaRepository;

@RestController
@RequestMapping(value = "/pessoa")
public class PessoaController {

	@Autowired
	private PessoaRepository repo;

	@GetMapping
	private List<Pessoa> findAll() {
		return repo.findAll();
	}

	@PostMapping
	private ResponseEntity<Pessoa> save(@RequestBody Pessoa e) {

		Pessoa resp = repo.save(e);

		if (resp.equals(null)) {
			return ResponseEntity.internalServerError().build();

		} else {
			return ResponseEntity.ok(repo.save(e));
		}

	}

	@GetMapping(value = "/primeiroNome/{nome}")
	private List<Pessoa> findByNome(@PathVariable String nome) {
		return repo.findByPrimeiroNome(nome);
	}

	@GetMapping(value = "/sobreNome/{sobreNome}")
	private List<Pessoa> findBySobreNome(@PathVariable String sobreNome) {
		return repo.findBySobreNome(sobreNome);
	}

	@GetMapping(value = "/primeiroNome/{nome}/sobreNome/{sobreNome}")
	private List<Pessoa> findByPrimeiroNomeAndSobreNome(@PathVariable String nome, @PathVariable String sobreNome) {
		return repo.findByPrimeiroNomeAndSobreNome(nome, sobreNome);
	}

	@GetMapping(value = "/{id}")
	private ResponseEntity<Pessoa> findById(@PathVariable String id) {

		Pessoa resp = repo.findById(id).orElse(null);

		if (resp == null) {
			return ResponseEntity.notFound().build();

		} else {
			return ResponseEntity.ok(resp);
		}

	}

}
