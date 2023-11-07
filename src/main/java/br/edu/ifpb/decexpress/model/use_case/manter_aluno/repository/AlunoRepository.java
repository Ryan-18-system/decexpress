package br.edu.ifpb.decexpress.model.use_case.manter_aluno.repository;

import br.edu.ifpb.decexpress.model.entity.Aluno;
import br.edu.ifpb.decexpress.model.entity.Turma;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    @Query("from Aluno where matricula = :matriculaAluno")
    Optional<Aluno> findAlunoByMatricula(@Param("matriculaAluno")Long matriculaAluno);
    @Query("from Aluno where email = :emailAluno")
    Optional<Aluno> findAlunoByEmail(@Param("emailAluno")String emailAluno);

    @Query(value = " SELECT * FROM dec_express.tb_aluno aa WHERE aa.st_registro  = 1", nativeQuery = true)
    List<Aluno> findByStRegistro1();
    @Query(value = " SELECT * FROM dec_express.tb_aluno aa WHERE aa.cod_turma  = :codTurma", nativeQuery = true)
    List<Aluno> findAlunoByCodTurma(@Param("codTurma") Long codTurma);
}
