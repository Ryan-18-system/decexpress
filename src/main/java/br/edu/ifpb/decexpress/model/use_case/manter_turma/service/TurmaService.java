package br.edu.ifpb.decexpress.model.use_case.manter_turma.service;


import br.edu.ifpb.decexpress.model.entity.Turma;
import br.edu.ifpb.decexpress.model.use_case.manter_turma.dto.TurmaForm;
import br.edu.ifpb.decexpress.model.use_case.manter_turma.dto.TurmaView;
import br.edu.ifpb.decexpress.utils.exception.ServiceApplicationException;

import java.util.Collection;
import java.util.List;

public interface TurmaService {

    void inserir(TurmaForm dto) throws ServiceApplicationException;

    List<TurmaView> listar() throws ServiceApplicationException;

    void alterar(Long codTurma, TurmaForm dto) throws ServiceApplicationException;

    void deletar(Long codTurma) throws ServiceApplicationException;

    TurmaView pesquisarTurma(Long codTurma) throws  ServiceApplicationException;
}
