package br.edu.ifpb.decexpress.model.use_case.manter_turma.service;

import br.edu.ifpb.decexpress.model.entity.Turma;
import br.edu.ifpb.decexpress.model.use_case.manter_turma.dto.TurmaForm;
import br.edu.ifpb.decexpress.model.use_case.manter_turma.dto.TurmaView;
import br.edu.ifpb.decexpress.model.use_case.manter_turma.repository.TurmaRepository;
import br.edu.ifpb.decexpress.utils.exception.ServiceApplicationException;
import br.edu.ifpb.decexpress.utils.mapper.turma.TurmaMapperView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurmaServiceImpl implements TurmaService {
    private final TurmaMapperView turmaMapperView;
    private final TurmaRepository turmaRepository;

    @Autowired
    public TurmaServiceImpl(TurmaMapperView turmaMapperView, TurmaRepository turmaRepository) {
        this.turmaMapperView = turmaMapperView;
        this.turmaRepository = turmaRepository;
    }

    @Override
    public void inserir(TurmaForm dto) throws ServiceApplicationException {

    }

    @Override
    public List<TurmaView> listar() throws ServiceApplicationException {
        return this.turmaMapperView.mapCollection(turmaRepository.findAll());
    }


    @Override
    public TurmaView alterar(Long codTurma, TurmaForm dto) throws ServiceApplicationException {
        return null;
    }

    @Override
    public void deletar(Long codTurma) throws ServiceApplicationException {

    }

    @Override
    public TurmaView pesquisarTurma(Long codTurma) throws ServiceApplicationException {
        return null;
    }
}
