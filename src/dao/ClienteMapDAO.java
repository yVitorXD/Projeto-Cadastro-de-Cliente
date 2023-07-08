package dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import domain.Cliente;

public class ClienteMapDAO implements IClienteDAO {

	// Vamos salvar colocando em um map, chave(cpf(long) valor(cliente);
	// vai ser chamada de "map"
	private Map<Long, Cliente> map;
	
	//dentro deste construtor iremos instanciar o map
	public ClienteMapDAO() {
		this.map = new HashMap<>();
	}
	
	
	// Método cadastrar
	// (this = todas as informaçoes da classe
	@Override
	public Boolean cadastrar(Cliente cliente) {
		
		// "se" no map contains key (cpf), verifico se o mapa tem cpf
		//	se contiver, retorno falso
		// se tiver a chave no map, retorno false, nao vo colocar mais nada la, ja que o cliente ja esta la
		if (this.map.containsKey(cliente.getCpf())) {
			return false;
		}
		// se n tiver, ele executa o codigo abaixo
		//adicionar no mapa a chave cpf desse cliente e retorna true
		this.map.put(cliente.getCpf(), cliente);
		
		return true;
	}

	// Método excluir, pela chave cpf do cliente cadastrado
	@Override
	public void excluir(Long cpf) {
		Cliente clienteCadastrado = this.map.get(cpf);
		// se cliente for diferente de nulo
		// tenho que remover ele do map
		if (clienteCadastrado != null) {
			// removendo do map pelo cpf
			// Chave = clienteCadastrado.getCpf()
			// Valor = clienteCadastrado
			this.map.remove(clienteCadastrado.getCpf(), clienteCadastrado);
		}
		
	}

	@Override
	public void alterar(Cliente cliente) {
		Cliente clienteCadastrado = this.map.get(cliente.getCpf());
		// se cliente for diferente de null, vou substituir as informaçoes deste cliente, (clienteCadastrado)
		if (cliente != null) {
			//Substitui o cliente do map(clienteCadastrado.setNome, pelo cliente quem vem na tela(cliente.getNome()
			clienteCadastrado.setNome(cliente.getNome());
			clienteCadastrado.setTel(cliente.getTel());
			clienteCadastrado.setNumero(cliente.getNumero());
			clienteCadastrado.setEnd(cliente.getEnd());
			clienteCadastrado.setCidade(cliente.getCidade());
			clienteCadastrado.setEstado(cliente.getEstado());

		}
		
	}

	
	
	@Override
	public Cliente consultar(Long cpf) {
		
		return this.map.get(cpf);
		//vai retornar a chave(cpf), se tiver no map retorna cliente, senao retorna nulo
	}

	@Override
	public Collection<Cliente> buscarTodos() {
		return this.map.values();
		//para buscar todos, busco todos os valor do mapa com this.map.values();
		// values retorna uma coleçao do tipo cliente
	}


}
