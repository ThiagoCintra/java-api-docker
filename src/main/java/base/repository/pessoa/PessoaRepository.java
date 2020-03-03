package base.repository.pessoa;

import org.springframework.data.jpa.repository.JpaRepository;
import base.model.pessoa.Pessoa;


public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

}
