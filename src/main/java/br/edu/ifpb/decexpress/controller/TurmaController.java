package br.edu.ifpb.decexpress.controller;

import br.edu.ifpb.decexpress.model.entity.Turma;
import br.edu.ifpb.decexpress.model.use_case.manter_turma.dto.TurmaView;
import br.edu.ifpb.decexpress.model.use_case.manter_turma.service.TurmaService;
import br.edu.ifpb.decexpress.utils.exception.ServiceApplicationException;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/turma")
public class TurmaController {

    private final TurmaService turmaService;

    @Autowired
    public TurmaController(TurmaService turmaService) {
        this.turmaService = turmaService;
    }

    @GetMapping()
    @Operation(summary = "Listar Turmas")
    public List<TurmaView> listarAllTurmas(){
        try {
            return turmaService.listar();
        } catch (ServiceApplicationException e) {
            throw new RuntimeException(e);
        }
    }
}
