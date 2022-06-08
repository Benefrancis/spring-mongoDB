package br.com.benefrancis.empregados.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.benefrancis.empregados.entity.Empregado;
import br.com.benefrancis.empregados.entity.Pessoa;
import br.com.benefrancis.empregados.entity.Setor;
import br.com.benefrancis.empregados.repository.EmpregadoRepository;
import br.com.benefrancis.empregados.repository.PessoaRepository;
import br.com.benefrancis.empregados.repository.SetorRepository;

@Service
public class ServiceEmpregado {

	@Autowired
	private SetorRepository setorRepository;

	@Autowired
	private PessoaRepository pessoaRepository;

	@Autowired
	private EmpregadoRepository repo;

	public Optional<List<Empregado>> findByNome(String nome) {
		return repo.findByPessoaPrimeiroNome(nome);
	}

	public Long count() {
		return repo.count();
	}

	public List<Empregado> findAll() {
		return repo.findAll();
	}

	public Empregado save(Empregado e) {

		if (e.getSetor() == null) {
			return null;
		} else {
			e.setSetor(jaExisteSetor(e.getSetor()));
		}

		if (e.getPessoa() == null) {
			return null;
		} else {
			e.setPessoa(jaExistePessoa(e.getPessoa()));
		}

		return repo.save(e);
	}

	private Pessoa jaExistePessoa(Pessoa p) {

		if (p != null) {
			if (p.getId() != null) {

				Pessoa ret = pessoaRepository.findById(p.getId()).orElse(null);

				if (ret != null) {

					p = ret;
				}

			} else if (p.getId() == null && p.getEmail() != null) {

				Pessoa ret = pessoaRepository.findByEmail(p.getEmail()).orElse(null);
				if (ret != null) {

					p = ret;

				} else {

					if (p.getPrimeiroNome() != null && p.getSobreNome() != null) {
						ret = pessoaRepository.save(p);
						if (ret != null) {
							p = ret;
						}

					}
				}
			}
		}

		return p;
	}

	private Setor jaExisteSetor(Setor s) {

		if (s.getId() != null) {

			s = setorRepository.findById(s.getId()).orElse(null);

			if (s == null)
				jaExisteSetor(s);

		} else if (s.getId() == null && s.getNome() != null) {

			List<Setor> set = setorRepository.findByNome(s.getNome());
			if (set.size() > 0) {
				s = set.get(0);
				return s;
			}
			s = setorRepository.save(s);
		}
		return s;
	}

	public Optional<Empregado> findById(String id) {
		return repo.findById(id);
	}

	public Optional<Empregado> findByEmail(String email) {
		return repo.findByEmail(email.trim());
	}
}
