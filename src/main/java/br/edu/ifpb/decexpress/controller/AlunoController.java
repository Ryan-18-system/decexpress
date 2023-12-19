package br.edu.ifpb.decexpress.controller;

import br.edu.ifpb.decexpress.infra.securityDec.SecurityDec;
import br.edu.ifpb.decexpress.model.use_case.manter_aluno.dto.AlunoForm;

import br.edu.ifpb.decexpress.model.use_case.manter_aluno.dto.AlunoView;
import br.edu.ifpb.decexpress.model.use_case.manter_aluno.service.AlunoService;
import br.edu.ifpb.decexpress.utils.exception.ServiceApplicationException;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;
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
    public ResponseEntity<AlunoView> cadastrarAluno(@RequestBody AlunoForm alunoForm) {
        try {
            return new ResponseEntity<>(this.alunoService.inserir(alunoForm), HttpStatus.OK);
        } catch (ServiceApplicationException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping
    @Operation(summary = "Listar Alunos")
    public ResponseEntity<List<AlunoView>> listarAluno() {
        try {
            return new ResponseEntity<>(this.alunoService.listar(), HttpStatus.OK);
        } catch (ServiceApplicationException e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping(value = "/{matriculaAluno}")
    @Operation(summary = "Alterar Aluno por Matrícula")
    @SecurityDec()
    public ResponseEntity<AlunoView> alterarAluno(@PathVariable("matriculaAluno") Long matriculaAluno,
                                                  @RequestBody AlunoForm alunoForm) {
        try {
            return new ResponseEntity<>(this.alunoService.alterar(matriculaAluno, alunoForm), HttpStatus.OK);
        } catch (ServiceApplicationException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping(value = "matricula/{matriculaAluno}")
    @Operation(summary = "Pesquisar Aluno Por matrícula")
    @SecurityDec()
    public ResponseEntity<AlunoView> pesquisarAlunoPorMatricula(@PathVariable("matriculaAluno") Long matriculaAluno) {
        try {
            return new ResponseEntity<>(this.alunoService.pesquisarAluno(matriculaAluno), HttpStatus.OK);
        } catch (ServiceApplicationException e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping(value = "/{matriculaAluno}")
    @Operation(summary = "Deletar aluno por matrícula")
    @SecurityDec(accessAllowed = false)
    public ResponseEntity deletarAlunoPorMatricula(@PathVariable("matriculaAluno") Long matriculaAluno) {
        try {
            this.alunoService.deletar(matriculaAluno);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ServiceApplicationException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping(value = "/verificar-email/{emailAluno}")
    @Operation(summary = "Pesquisar Email")
    public ResponseEntity<Boolean> pesquisarExistenciaDoEmail(@PathVariable("emailAluno") String email) {
        try {
            Boolean retorno = this.alunoService
                    .pesquisarExistenciaDoEmail(email.trim().toUpperCase(Locale.ROOT));
            return new ResponseEntity<>(retorno, HttpStatus.OK);
        } catch (ServiceApplicationException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping(value = "/email/{email}")
    @Operation(summary = "Pesquisar Aluno Por Email")
    public ResponseEntity<AlunoView> pesquisarAlunoPorEmail(@PathVariable("email") String email) {
        try {
            return new ResponseEntity<>(this.alunoService.pesquisarAlunoPorEmail(email.trim().toUpperCase()), HttpStatus.OK);
        } catch (ServiceApplicationException e) {
            throw new RuntimeException(e);
        }
    }

}
