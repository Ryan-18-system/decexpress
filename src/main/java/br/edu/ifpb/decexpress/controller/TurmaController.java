package br.edu.ifpb.decexpress.controller;

import br.edu.ifpb.decexpress.infra.securityDec.SecurityDec;
import br.edu.ifpb.decexpress.model.use_case.manter_turma.dto.TurmaForm;
import br.edu.ifpb.decexpress.model.use_case.manter_turma.dto.TurmaView;
import br.edu.ifpb.decexpress.model.use_case.manter_turma.service.TurmaService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @SecurityDec(accessAllowed = false)
    public ResponseEntity<List<TurmaView>> listarAllTurmas() {
        List<TurmaView> listaReturn = turmaService.listar();
        if (listaReturn == null || listaReturn.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(listaReturn, HttpStatus.OK);

    }

    @GetMapping("/{codTurma}")
    @Operation(summary = "Pesquisar turma por código")
    @SecurityDec
    public ResponseEntity<TurmaView> pesquisarTurmaPorId(@PathVariable("codTurma") Long codTurma) {
        return new ResponseEntity<>(this.turmaService.pesquisarTurma(codTurma), HttpStatus.OK);
    }


    @DeleteMapping(value = "/{codTurma}")
    @Operation(summary = "Deletar uma Turma")
    @SecurityDec
    public ResponseEntity deletarTurmaPorCodigo(@PathVariable Long codTurma) {
        this.turmaService.deletar(codTurma);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @PutMapping(value = {"/{codTurma}"})
    @Operation(summary = "Alterar uma Turma")
    @SecurityDec
    public ResponseEntity<String> alterarTurma(@PathVariable Long codTurma, @RequestBody TurmaForm turmaForm) {
        this.turmaService.alterar(codTurma, turmaForm);
        return new ResponseEntity<>("Turma alterada com Sucesso", HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Cadastrar turma")
    @SecurityDec
    public ResponseEntity<String> cadastrarTurma(@RequestBody TurmaForm turmaForm) {
        this.turmaService.inserir(turmaForm);
        return new ResponseEntity<>("Turma Cadastrada com Sucesso", HttpStatus.OK);
    }

}
