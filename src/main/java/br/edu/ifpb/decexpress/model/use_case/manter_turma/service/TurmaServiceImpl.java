package br.edu.ifpb.decexpress.model.use_case.manter_turma.service;

import br.edu.ifpb.decexpress.model.entity.Turma;
import br.edu.ifpb.decexpress.model.use_case.manter_turma.dto.TurmaForm;
import br.edu.ifpb.decexpress.model.use_case.manter_turma.dto.TurmaView;
import br.edu.ifpb.decexpress.model.use_case.manter_turma.repository.TurmaRepository;
import br.edu.ifpb.decexpress.utils.GlobalConstantes;
import br.edu.ifpb.decexpress.utils.exception.ServiceApplicationException;
import br.edu.ifpb.decexpress.utils.mapper.turma.TurmaMapperForm;
import br.edu.ifpb.decexpress.utils.mapper.turma.TurmaMapperView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TurmaServiceImpl implements TurmaService {

    private final TurmaMapperView turmaMapperView;
    private final TurmaRepository turmaRepository;
    private final TurmaMapperForm turmaMapperForm;


    @Autowired
    public TurmaServiceImpl(TurmaMapperView turmaMapperView, TurmaRepository turmaRepository, TurmaMapperForm turmaMapperForm) {
        this.turmaMapperView = turmaMapperView;
        this.turmaRepository = turmaRepository;
        this.turmaMapperForm = turmaMapperForm;
    }

    @Override
    @Transactional
    public void inserir(TurmaForm dto) throws ServiceApplicationException {
        Optional<Turma> turmaJaExistente = turmaRepository.findBySerieTurmaNivel(dto.serie()
                , dto.turma(),
                dto.nivel());
        if (turmaJaExistente.isPresent()) {
            throw new ServiceApplicationException("erro.cadastro.duplicado");
        }// if
        Turma novaTurma = this.turmaMapperForm.map(dto);
        this.turmaRepository.save(novaTurma);
    }

    @Override
    public List<TurmaView> listar() throws ServiceApplicationException {
        return this.turmaMapperView.mapCollection(turmaRepository.findByStRegistro1());
    }


    @Override
    @Transactional
    public void alterar(Long codTurma, TurmaForm dto) throws ServiceApplicationException {
        Optional<Turma> turmaBanco = turmaRepository.findById(codTurma);
        if (turmaBanco.isEmpty()) {
            throw new ServiceApplicationException("erro.pesquisar.turma");
        }// if
        if ((long) codTurma != turmaBanco.get().getCodTurma()) {
            throw new ServiceApplicationException("erro.turma.ja.existente");
        }// if
        Optional<Turma> turmaJaExistente = turmaRepository.findBySerieTurmaNivel(dto.serie()
                , dto.turma(),
                dto.nivel());
        if (turmaJaExistente.isPresent()) {
            if (Long.compare(turmaJaExistente.get().getCodTurma(), codTurma) != 0) {
                throw new ServiceApplicationException("erro.info.turma.divergente");
            }// if
        }// if
        turmaBanco.get().setAno(dto.ano());

    }// alterar

    @Override
    @Transactional
    public void deletar(Long codTurma) throws ServiceApplicationException {
        Optional<Turma> turmaBanco = turmaRepository.findById(codTurma);
        if (turmaBanco.isEmpty()) {
            throw new ServiceApplicationException("erro.pesquisar.turma");
        }// if
        turmaBanco.get().setStRegistro(GlobalConstantes.ST_INATIVO);
    }// deletar

    @Override
    public TurmaView pesquisarTurma(Long codTurma) throws ServiceApplicationException {
        Optional<Turma> turmaBanco = turmaRepository.findBtIdRegistro1(codTurma);
        if (turmaBanco.isEmpty()) {
            throw new ServiceApplicationException("erro.pesquisar.turma");
        }// if
        TurmaView retorno = this.turmaMapperView.map(turmaBanco.get());
        return retorno;
    }// pesquisarTurma
}
