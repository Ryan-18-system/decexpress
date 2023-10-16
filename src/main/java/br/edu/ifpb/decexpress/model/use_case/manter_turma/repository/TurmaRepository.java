package br.edu.ifpb.decexpress.model.use_case.manter_turma.repository;

import br.edu.ifpb.decexpress.model.entity.Turma;
import br.edu.ifpb.decexpress.utils.NivelTurma;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TurmaRepository extends JpaRepository<Turma, Long> {

    @Query(value = " SELECT * FROM dec_express.tb_turma tt WHERE tt.st_registro = 1", nativeQuery = true)
    List<Turma> findByStRegistro1();

    @Query("SELECT turma from Turma where stRegistro=1 and codTurma = :codTurma")
    Optional<Turma> findBtIdRegistro1(@Param("codTurma") Long codTurma);

    @Query("SELECT t FROM Turma t WHERE t.serie = :serie AND t.turma = upper(:turma) AND t.nivel = :nivel AND t.stRegistro = 1")
    Optional<Turma> findBySerieTurmaNivel(
            @Param("serie") Integer serie,
            @Param("turma") char turma,
            @Param("nivel") NivelTurma nivel
    );
}
