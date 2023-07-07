package dao;

import java.util.Collection;
import java.util.Collections;

import domain.Cliente;

public interface IClienteDAO {

	public Boolean cadastrar(Cliente cliente);
	
	public void excluir(Long cpf);
	
	public void alterar(Cliente cliente);
	
	public Cliente consultar(Long cpf);
	
	public Collection<Cliente> buscarTodos();

	boolean verificarCadastroExistente(Cliente cliente);
	
}
