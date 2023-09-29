package br.edu.ifpb.decexpress.model.use_case.manter_aluno.service.impl;

import br.edu.ifpb.decexpress.model.use_case.manter_aluno.dto.AlunoForm;
import br.edu.ifpb.decexpress.model.use_case.manter_aluno.dto.AlunoView;
import br.edu.ifpb.decexpress.model.use_case.manter_aluno.repository.AlunoRepository;
import br.edu.ifpb.decexpress.model.use_case.manter_aluno.service.AlunoService;
import br.edu.ifpb.decexpress.utils.exception.ServiceApplicationException;
import br.edu.ifpb.decexpress.utils.mapper.aluno.AlunoMapperView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlunoServiceImpl implements AlunoService {
    private final AlunoMapperView alunoMapperView;
    private final AlunoRepository alunoRepository;

    @Autowired

    public AlunoServiceImpl(AlunoMapperView alunoMapperView, AlunoRepository alunoRepository) {
        this.alunoMapperView = alunoMapperView;
        this.alunoRepository = alunoRepository;
    }

    @Override
    public AlunoView inserir(AlunoForm dto) throws ServiceApplicationException {
        return null;
    }

    @Override
    public List<AlunoView> listar() throws ServiceApplicationException {
        return alunoMapperView.mapCollection(this.alunoRepository.findAll());
    }

    @Override
    public AlunoView alterar(Long codAluno, AlunoForm dto) throws ServiceApplicationException {
        return null;
    }

    @Override
    public void deletar(Long codAluno) throws ServiceApplicationException {

    }

    @Override
    public AlunoView pesquisarAluno(Long codAluno) throws ServiceApplicationException {
        return null;
    }
}
