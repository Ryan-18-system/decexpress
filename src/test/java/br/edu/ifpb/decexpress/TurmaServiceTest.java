package br.edu.ifpb.decexpress;

import br.edu.ifpb.decexpress.model.entity.Turma;
import br.edu.ifpb.decexpress.model.use_case.manter_turma.dto.TurmaForm;
import br.edu.ifpb.decexpress.model.use_case.manter_turma.dto.TurmaView;
import br.edu.ifpb.decexpress.model.use_case.manter_turma.repository.TurmaRepository;
import br.edu.ifpb.decexpress.model.use_case.manter_turma.service.TurmaServiceImpl;
import br.edu.ifpb.decexpress.utils.NivelTurma;
import br.edu.ifpb.decexpress.utils.exception.ServiceApplicationException;
import br.edu.ifpb.decexpress.utils.mapper.turma.TurmaMapperView;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.Mockito.*;

public class TurmaServiceTest {

    @InjectMocks
    private TurmaServiceImpl turmaService;

    @Mock
    private TurmaMapperView turmaMapperView;
    @Mock
    private TurmaRepository turmaRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test(expected = ServiceApplicationException.class)
    public void testInserirTurma() {
        TurmaForm turmaForm = new TurmaForm(7, 2023, NivelTurma.ENSINO_FUNDAMENTAL_II, 'Z');

        when(turmaRepository.findBySerieTurmaNivel(7, 'Z', NivelTurma.ENSINO_FUNDAMENTAL_II)).thenReturn(Optional.of(new Turma()));

        turmaService.inserir(turmaForm);
    }

    @Test(expected = ServiceApplicationException.class)
    public void testInserirTurmaDuplicada() {
        TurmaForm turmaForm = new TurmaForm(1, 2023, NivelTurma.ENSINO_MEDIO, 'A');

        when(turmaRepository.findBySerieTurmaNivel(1, 'A', NivelTurma.ENSINO_MEDIO)).thenReturn(Optional.of(new Turma()));

        turmaService.inserir(turmaForm);
    }

    @Test(expected = ServiceApplicationException.class)
    public void testDeletarTurmaInexistente() {
        Long codTurma = 1L;
        when(turmaRepository.findById(codTurma)).thenReturn(Optional.empty());

        turmaService.deletar(codTurma);
    }

    @Test
    public void testPesquisarTurma() {
        Long codTurma = 1L;
        Turma turmaBanco = new Turma();
        when(turmaRepository.findBtIdRegistro1(codTurma)).thenReturn(Optional.of(turmaBanco));
        when(turmaMapperView.map(turmaBanco)).thenReturn(new TurmaView(codTurma, 1, 2023, NivelTurma.ENSINO_MEDIO, 'A', null));

        TurmaView result = turmaService.pesquisarTurma(codTurma);

        verify(turmaRepository).findBtIdRegistro1(codTurma);
        verify(turmaMapperView).map(turmaBanco);
    }

    @Test(expected = ServiceApplicationException.class)
    public void testPesquisarTurmaInexistente() {
        Long codTurma = 1L;
        when(turmaRepository.findBtIdRegistro1(codTurma)).thenReturn(Optional.empty());

        turmaService.pesquisarTurma(codTurma);
    }

    @Test(expected = ServiceApplicationException.class)
    public void testAlterarTurmaInexistente() {
        Long codTurma = 1L;
        TurmaForm turmaForm = new TurmaForm(1, 2023, NivelTurma.ENSINO_MEDIO, 'Z');

        when(turmaRepository.findById(codTurma)).thenReturn(Optional.empty());

        turmaService.alterar(codTurma, turmaForm);
    }
}
