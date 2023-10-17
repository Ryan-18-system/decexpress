package br.edu.ifpb.decexpress;

import br.edu.ifpb.decexpress.model.entity.Aluno;
import br.edu.ifpb.decexpress.model.use_case.manter_aluno.dto.AlunoForm;
import br.edu.ifpb.decexpress.model.use_case.manter_aluno.dto.AlunoView;
import br.edu.ifpb.decexpress.model.use_case.manter_aluno.repository.AlunoRepository;
import br.edu.ifpb.decexpress.model.use_case.manter_aluno.service.AlunoService;
import br.edu.ifpb.decexpress.model.use_case.manter_aluno.service.impl.AlunoServiceImpl;
import br.edu.ifpb.decexpress.utils.GlobalConstantes;
import br.edu.ifpb.decexpress.utils.exception.ServiceApplicationException;
import br.edu.ifpb.decexpress.utils.mapper.aluno.AlunoMapperView;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AlunoServiceTest {

    @InjectMocks
    private AlunoServiceImpl alunoService;

    @Mock
    private AlunoMapperView alunoMapperView;
    @Mock
    private AlunoRepository alunoRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test(expected = ServiceApplicationException.class)
    public void testAlterarAlunoInexistente() {
        Long matriculaAluno = 1L;
        AlunoForm alunoForm = new AlunoForm("Ryan", LocalDate.of(2000, 11, 10), "1234567", "123", "999999999", "ryan@test", null, null);

        when(alunoRepository.findAlunoByMatricula(matriculaAluno)).thenReturn(Optional.empty());

        alunoService.alterar(matriculaAluno, alunoForm);
    }

    @Test
    public void testDeletar() {
        Long matriculaAluno = 1L;
        Aluno aluno = new Aluno();

        when(alunoRepository.findAlunoByMatricula(matriculaAluno)).thenReturn(Optional.of(aluno));

        alunoService.deletar(matriculaAluno);

        verify(alunoRepository).findAlunoByMatricula(matriculaAluno);
        assertEquals(GlobalConstantes.ST_INATIVO, aluno.getStRegistro());
    }

    @Test(expected = ServiceApplicationException.class)
    public void testDeletarAlunoInexistente() {
        Long matriculaAluno = 1L;

        when(alunoRepository.findAlunoByMatricula(matriculaAluno)).thenReturn(Optional.empty());

        alunoService.deletar(matriculaAluno);
    }

    @Test
    public void testPesquisarAluno() {
        Long matriculaAluno = 1L;
        Aluno aluno = new Aluno();
        AlunoView alunoView = new AlunoView();

        when(alunoRepository.findAlunoByMatricula(matriculaAluno)).thenReturn(Optional.of(aluno));
        when(alunoMapperView.map(aluno)).thenReturn(alunoView);

        AlunoView result = alunoService.pesquisarAluno(matriculaAluno);

        verify(alunoRepository).findAlunoByMatricula(matriculaAluno);
        assertEquals(alunoView, result);
    }

    @Test(expected = ServiceApplicationException.class)
    public void testPesquisarAlunoInexistente() {
        Long matriculaAluno = 1L;

        when(alunoRepository.findAlunoByMatricula(matriculaAluno)).thenReturn(Optional.empty());

        alunoService.pesquisarAluno(matriculaAluno);
    }
}
