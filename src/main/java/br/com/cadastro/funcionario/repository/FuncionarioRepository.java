package br.com.cadastro.funcionario.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cadastro.funcionario.model.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

}
