package br.com.benefrancis.empregados.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.benefrancis.empregados.entity.Empregado;

@Repository
public interface EmpregadoRepository extends MongoRepository<Empregado, String> {

	Optional<List<Empregado>> findByPessoaPrimeiroNome(String nome);

	public long count();

	Optional<Empregado> findByEmail(String trim);

}
