package br.edu.ifpb.decexpress.model.use_case.manter_aluno.repository;

import br.edu.ifpb.decexpress.model.entity.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {
}
