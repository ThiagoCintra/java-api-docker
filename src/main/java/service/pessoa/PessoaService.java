package service.pessoa;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import model.pessoa.Pessoa;
import repository.pessoa.PessoaRepository;

@Service
public class PessoaService {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	
	public Pessoa atualizar(Long id, @Valid Pessoa pessoa) {
		Pessoa pessoaSave = buscarPessoaPeloCodigo(id);
		BeanUtils.copyProperties(pessoa, pessoaSave, "id");
		return pessoaRepository.save(pessoaSave);
	}

	private Pessoa buscarPessoaPeloCodigo(Long id) {
		Optional<Pessoa> pessoaSalva = pessoaRepository.findById(id);
		
		if (!pessoaSalva.isPresent()) {
			throw new EmptyResultDataAccessException(1);
		}
		return pessoaSalva.get();
	}
}
