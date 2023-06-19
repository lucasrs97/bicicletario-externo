package com.api.bicicletario.controller;

import com.api.bicicletario.model.Funcionario;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {

    private List<Funcionario> funcionarios = new ArrayList<>();

    // Listar todos os funcionários
    @GetMapping
    public List<Funcionario> listarFuncionarios() {
        return funcionarios;
    }

    // Incluir funcionário
    @PostMapping
    public ResponseEntity<String> incluirFuncionario(@RequestBody Funcionario funcionario) {
        // Validação dos dados
        if (!validarFuncionario(funcionario)) {
            return ResponseEntity.badRequest().body("Dados inválidos");
        }

        // Registro do funcionário
        funcionarios.add(funcionario);

        return ResponseEntity.status(HttpStatus.CREATED).body("Funcionário criado com sucesso");
    }

    // Editar funcionário
    @PutMapping("/{matricula}")
    public ResponseEntity<String> editarFuncionario(@PathVariable String matricula, @RequestBody Funcionario funcionario) {
        // Busca pelo funcionário
        Funcionario funcionarioExistente = buscarFuncionarioPorMatricula(matricula);

        // Verifica se o funcionário existe
        if (funcionarioExistente == null) {
            return ResponseEntity.notFound().build();
        }

        // Validação dos dados
        if (!validarFuncionario(funcionario)) {
            return ResponseEntity.badRequest().body("Dados inválidos");
        }

        // Atualização do funcionário
        funcionarios.remove(funcionarioExistente);
        funcionarios.add(funcionario);

        return ResponseEntity.ok("Funcionário atualizado com sucesso");
    }

    // Excluir funcionário
    @DeleteMapping("/{matricula}")
    public ResponseEntity<String> excluirFuncionario(@PathVariable String matricula) {
        // Busca pelo funcionário
        Funcionario funcionarioExistente = buscarFuncionarioPorMatricula(matricula);

        // Verifica se o funcionário existe
        if (funcionarioExistente == null) {
            return ResponseEntity.notFound().build();
        }

        // Exclusão do funcionário
        funcionarios.remove(funcionarioExistente);

        return ResponseEntity.ok("Funcionário excluído com sucesso");
    }

    // Função de validação do funcionário
    private boolean validarFuncionario(Funcionario funcionario) {
        // Verifica se todos os campos obrigatórios estão preenchidos
        if (funcionario.getMatricula() == null ||
                funcionario.getSenha() == null ||
                funcionario.getConfirmacaoSenha() == null ||
                funcionario.getEmail() == null ||
                funcionario.getNome() == null ||
                funcionario.getFuncao() == null ||
                funcionario.getCpf() == null) {
            return false;
        }

        // Verifica se a senha e a confirmação de senha são iguais
        if (!funcionario.getSenha().equals(funcionario.getConfirmacaoSenha())) {
            return false;
        }

        return true;
    }

    // Função para buscar funcionário por matrícula
    private Funcionario buscarFuncionarioPorMatricula(String matricula) {
        for (Funcionario funcionario : funcionarios) {
            if (funcionario.getMatricula().equals(matricula)) {
                return funcionario;
            }
        }
        return null;
    }
}
