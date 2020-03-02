package repository.pessoa;

import org.springframework.data.jpa.repository.JpaRepository;
import model.pessoa.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

}
