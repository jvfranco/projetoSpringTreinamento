package br.com.cadastro.funcionario.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.cadastro.funcionario.exceptions.FuncionarioNaoEncontradoException;
import br.com.cadastro.funcionario.model.Funcionario;
import br.com.cadastro.funcionario.service.FuncionarioService;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {

	@Autowired
	private FuncionarioService funcionarioService;

	@GetMapping
	public ResponseEntity<?> obterListaDeFuncionarios() {
		try {
			return ResponseEntity.ok(this.funcionarioService.obterListaDeFuncionarios());
		} catch (FuncionarioNaoEncontradoException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> obterFuncionarioDetalhado(@PathVariable Long id) {
		try {
			return ResponseEntity.ok(this.funcionarioService.obterFuncionarioDetalhado(id));
		} catch (FuncionarioNaoEncontradoException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
	
	@PostMapping
	public ResponseEntity<Funcionario> cadastrarFuncionario(@RequestBody Funcionario funcionario, UriComponentsBuilder uriBuilder) {
		Funcionario funcionarioSalvo = this.funcionarioService.cadastrarFuncionario(funcionario);
		URI uri = uriBuilder.path("/{id}").buildAndExpand(funcionarioSalvo.getId()).toUri();
		return ResponseEntity.created(uri).body(funcionarioSalvo);
	}

}
