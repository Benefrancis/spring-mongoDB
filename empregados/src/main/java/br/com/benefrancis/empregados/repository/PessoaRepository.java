package br.com.benefrancis.empregados.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.benefrancis.empregados.entity.Pessoa;

public interface PessoaRepository extends MongoRepository<Pessoa, String> {

	public long count();

	List<Pessoa> findByPrimeiroNome(String nome);

	List<Pessoa> findByPrimeiroNomeAndSobreNome(String nome, String sobrenome);

	public List<Pessoa> findBySobreNome(String sobreNome);

	public Optional<Pessoa> findByEmail(String email);

}
