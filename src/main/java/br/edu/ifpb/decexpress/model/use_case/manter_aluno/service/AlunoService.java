package br.edu.ifpb.decexpress.model.use_case.manter_aluno.service;

import br.edu.ifpb.decexpress.model.entity.Aluno;
import br.edu.ifpb.decexpress.model.use_case.manter_aluno.dto.AlunoForm;
import br.edu.ifpb.decexpress.model.use_case.manter_aluno.dto.AlunoView;
import br.edu.ifpb.decexpress.utils.exception.ServiceApplicationException;
import org.springframework.stereotype.Service;

import java.util.List;


public interface AlunoService {
    AlunoView inserir(AlunoForm dto) throws ServiceApplicationException;
    List<AlunoView> listar() throws ServiceApplicationException;

    AlunoView alterar(Long matriculaAluno, AlunoForm dto) throws ServiceApplicationException;

    void deletar(Long matriculaAluno) throws ServiceApplicationException;

    AlunoView pesquisarAluno(Long matriculaAluno) throws  ServiceApplicationException;


}
