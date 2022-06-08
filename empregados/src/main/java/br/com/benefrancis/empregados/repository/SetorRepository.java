package br.com.benefrancis.empregados.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.benefrancis.empregados.entity.Setor;

@Repository
public interface SetorRepository extends MongoRepository<Setor, String> {

	List<Setor> findByNome(String nome);

	public long count();

}
