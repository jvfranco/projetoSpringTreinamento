package br.com.cadastro.funcionario.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cadastro.funcionario.exceptions.FuncionarioNaoEncontradoException;
import br.com.cadastro.funcionario.model.Funcionario;
import br.com.cadastro.funcionario.repository.FuncionarioRepository;

@Service
public class FuncionarioService {

	@Autowired
	private FuncionarioRepository funcionarioRepository;

	public List<Funcionario> obterListaDeFuncionarios() throws FuncionarioNaoEncontradoException {
		List<Funcionario> funcionarios = this.funcionarioRepository.findAll();
		if (funcionarios.isEmpty()) {
			throw new FuncionarioNaoEncontradoException("Não existem funcionários cadastrados.");
		}
		return funcionarios;
	}

	public Funcionario obterFuncionarioDetalhado(Long id) throws FuncionarioNaoEncontradoException {
		Optional<Funcionario> funcionario = this.funcionarioRepository.findById(id);
		if (!funcionario.isPresent()) {
			throw new FuncionarioNaoEncontradoException("Funcionário de ID: " + id + " não encontrado.");
		}
		return funcionario.get();
	}

	public Funcionario cadastrarFuncionario(Funcionario funcionario) {
		Funcionario funcionarioSalvo = this.funcionarioRepository.save(funcionario);
		return funcionarioSalvo;
	}

}
