package br.edu.ifpb.decexpress.controller;

import br.edu.ifpb.decexpress.model.use_case.manter_aluno.dto.AlunoForm;

import br.edu.ifpb.decexpress.model.use_case.manter_aluno.dto.AlunoView;
import br.edu.ifpb.decexpress.model.use_case.manter_aluno.service.AlunoService;
import br.edu.ifpb.decexpress.utils.exception.ServiceApplicationException;
import br.edu.ifpb.decexpress.utils.message.MessageBundle;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/aluno")
public class AlunoController {
    private static final Logger logger = Logger.getLogger(AlunoController.class.getName());

    private final AlunoService alunoService;

    @Autowired
    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }


    @PostMapping()
    @Operation(summary = "Cadastrar um aluno")
    public void cadastrarAluno(@RequestBody AlunoForm alunoForm) {
    }
    @GetMapping
    @Operation(summary = "Listar Alunos")
    public List<AlunoView> listarAluno(){
        try{
            return  this.alunoService.listar();
        }catch (ServiceApplicationException e){
            throw  new RuntimeException(e);
        }
    }
}
