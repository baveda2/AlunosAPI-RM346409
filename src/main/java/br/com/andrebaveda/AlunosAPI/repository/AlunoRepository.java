package br.com.andrebaveda.AlunosAPI.repository;

import br.com.andrebaveda.AlunosAPI.entity.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Integer> {
}
