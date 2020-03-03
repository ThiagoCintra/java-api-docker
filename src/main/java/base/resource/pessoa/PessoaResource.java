package base.resource.pessoa;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import base.model.pessoa.Pessoa;
import base.repository.pessoa.PessoaRepository;
import base.service.pessoa.PessoaService;


@RestController
@RequestMapping("/pessoas")
public class PessoaResource {

	
	@Autowired(required=true)
	private PessoaRepository pessoaRepository;

	@Autowired
	private PessoaService pessoaService;

	// ao acessar a url /pessoa ira retornar todas as pessoas no bd
	@GetMapping
	public ResponseEntity<List<Pessoa>> listAll() {
		return ResponseEntity.ok(pessoaRepository.findAll());

	}

	@PostMapping
	public ResponseEntity<Pessoa> criar(@Valid @RequestBody Pessoa pessoa, HttpServletResponse response) {
		Pessoa pessoaSeve = pessoaRepository.save(pessoa);
		setHeaderLocation(response, pessoaSeve.getId());
		return ResponseEntity.status(HttpStatus.CREATED).body(pessoaSeve);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Pessoa> buscarPeloid(@PathVariable Long id) {
		Optional<Pessoa> pessoa = pessoaRepository.findById(id);
		return pessoa.isPresent() ? ResponseEntity.ok(pessoa.get()) : ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long id) {
		pessoaRepository.deleteById(id);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Pessoa> atualizar(@PathVariable Long id, @Valid @RequestBody Pessoa pessoa) {
		Pessoa pessoaSave = pessoaService.atualizar(id, pessoa);
		return ResponseEntity.ok(pessoaSave);
	}

	private void setHeaderLocation(HttpServletResponse response, Long id) {
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(id).toUri();
		response.setHeader("Location", uri.toASCIIString());
	}
}
