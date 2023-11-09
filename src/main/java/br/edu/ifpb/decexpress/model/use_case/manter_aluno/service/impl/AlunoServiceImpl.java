package br.edu.ifpb.decexpress.model.use_case.manter_aluno.service.impl;

import br.edu.ifpb.decexpress.model.entity.Aluno;
import br.edu.ifpb.decexpress.model.entity.Turma;
import br.edu.ifpb.decexpress.model.use_case.manter_aluno.dto.AlunoForm;
import br.edu.ifpb.decexpress.model.use_case.manter_aluno.dto.AlunoView;
import br.edu.ifpb.decexpress.model.use_case.manter_aluno.repository.AlunoRepository;
import br.edu.ifpb.decexpress.model.use_case.manter_aluno.service.AlunoService;
import br.edu.ifpb.decexpress.utils.GlobalConstantes;
import br.edu.ifpb.decexpress.utils.exception.ServiceApplicationException;
import br.edu.ifpb.decexpress.utils.mapper.aluno.AlunoMapperForm;
import br.edu.ifpb.decexpress.utils.mapper.aluno.AlunoMapperView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoServiceImpl implements AlunoService {
    private final AlunoMapperView alunoMapperView;
    private final AlunoRepository alunoRepository;
    private final AlunoMapperForm alunoMapperForm;

    @Autowired

    public AlunoServiceImpl(AlunoMapperView alunoMapperView, AlunoRepository alunoRepository, AlunoMapperForm alunoMapperForm) {
        this.alunoMapperView = alunoMapperView;
        this.alunoRepository = alunoRepository;
        this.alunoMapperForm = alunoMapperForm;
    }

    @Override
    @Transactional
    @CacheEvict(cacheNames = "alunos",allEntries = true)
    public AlunoView inserir(AlunoForm dto) throws ServiceApplicationException {
        Aluno novoAluno = this.alunoMapperForm.map(dto);
        this.alunoRepository.save(novoAluno);
        return this.alunoMapperView.map(novoAluno);
    }// inserir

    @Override
    @Cacheable(cacheNames = "alunos",key = "#root.method.name")
    public List<AlunoView> listar() throws ServiceApplicationException {
        return alunoMapperView.mapCollection(this.alunoRepository.findByStRegistro1());
    }

    @Transactional
    @Override
    @CacheEvict(cacheNames = "alunos",allEntries = true)
    public AlunoView alterar(Long matriculaAluno, AlunoForm dto) throws ServiceApplicationException {
        Optional<Aluno> alunoBanco = this.alunoRepository.findAlunoByMatricula(matriculaAluno);
        if (alunoBanco.isEmpty()) {
            throw new ServiceApplicationException("erro.aluno.nao.encontrado");
        }
        this.alunoMapperForm.mapAlteracao(dto, alunoBanco.get());
        return this.alunoMapperView.map(alunoBanco.get());

    }// alterar

    @Override
    @Transactional
    @CacheEvict(cacheNames = "alunos",allEntries = true)
    public void deletar(Long matriculaAluno) throws ServiceApplicationException {
        Optional<Aluno> alunoBanco = this.alunoRepository.findAlunoByMatricula(matriculaAluno);
        if (alunoBanco.isEmpty()) {
            throw new ServiceApplicationException("erro.aluno.nao.encontrado");
        }
        alunoBanco.get().setStRegistro(GlobalConstantes.ST_INATIVO);
    }// deletar

    @Override
    public AlunoView pesquisarAluno(Long matriculaAluno) throws ServiceApplicationException {
        Optional<Aluno> alunoBanco = this.alunoRepository.findAlunoByMatricula(matriculaAluno);
        if (alunoBanco.isEmpty()) {
            throw new ServiceApplicationException("erro.aluno.nao.encontrado");
        }
        return this.alunoMapperView.map(alunoBanco.get());
    }// pesquisar

    @Override
    public Boolean pesquisarExistenciaDoEmail(String email) throws ServiceApplicationException {
        Optional<Aluno> aluoPesquisado = this.alunoRepository.findAlunoByEmail(email);
        if(aluoPesquisado.isPresent()){
            throw new ServiceApplicationException("erro.email.ja.existe");
        }
        return Boolean.FALSE;
    }// pesquisar existencia do email

    @Override
    public AlunoView pesquisarAlunoPorEmail(String email) throws ServiceApplicationException {
        Optional<Aluno> alunoBanco = this.alunoRepository.findAlunoByEmail(email);
        if (alunoBanco.isEmpty()) {
            throw new ServiceApplicationException("erro.aluno.nao.encontrado");
        }
        return this.alunoMapperView.map(alunoBanco.get());
    }// pesquisar email

}
