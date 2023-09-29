package br.edu.ifpb.decexpress.model.use_case.manter_turma.repository;

import br.edu.ifpb.decexpress.model.entity.Turma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TurmaRepository extends JpaRepository<Turma,Long> {
}
