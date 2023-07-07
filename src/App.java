import javax.swing.JOptionPane;

import dao.ClienteMapDAO;
import dao.IClienteDAO;
import domain.Cliente;


public class App {

    private static IClienteDAO iClienteDAO;

    static {
        iClienteDAO = new ClienteMapDAO();
    }

    public static void main(String[] args) {

        // Solicitação da opção ao usuário
        String opcao = JOptionPane.showInputDialog(null,
                "Digite 1 para cadastro, 2 para consultar, 3 para exclusão, 4 para alteração ou 5 para sair",
                "Cadastro",
                JOptionPane.INFORMATION_MESSAGE);

        // Loop para verificar se a opção digitada é válida
        while (!isOpcaoValida(opcao)) {
            // Se a opção for uma string vazia, chama o método "sair()"
            if ("".equals(opcao)) {
                sair();
            }

            // Solicitar novamente a opção ao usuário
            opcao = JOptionPane.showInputDialog(null,
                    "Opção inválida! Digite 1 para cadastro, 2 para consultar, 3 para exclusão, 4 para alteração ou 5 para sair",
                    "Cadastro",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        
        // Loop infinito se a opção for válida
        while (isOpcaoValida(opcao)) {
            // Verificar se a opção é "5" para chamar o método "sair()"
            if (isOpcaoSair(opcao)) {
                sair();
            } else if (isCadastro(opcao)) {
            	String dados = JOptionPane.showInputDialog(null,
                        "Digite os dados do cliente separados por vírgula, conforme o exemplo: nome, cpf, tel, end, numero, cidade, estado ...",
                        "Cadastro",
                        JOptionPane.INFORMATION_MESSAGE);
            	cadastrar(dados);
            }
            // Você pode adicionar aqui a lógica para cada opção válida (1, 2, 3, 4)
            // Certifique-se de atualizar o valor da variável "opcao" dentro do loop
        }
    }

    private static void cadastrar(String dados) {
        // Separo tudo que está vindo por vírgula e coloco cada informação em uma posição do meu array
        String[] dadosSeparados = dados.split(",");
        
        // Verificar se todos os campos estão preenchidos
        if (dadosSeparados.length == 7 && !dados.contains(",")) {
            // Todos os campos estão preenchidos, então crio o objeto Cliente com os dados
            String nome = dadosSeparados[0];
            Long cpf = Long.parseLong(dadosSeparados[1]);
            Long tel = Long.parseLong(dadosSeparados[2]);
            String end = dadosSeparados[3];
            Integer numero = Integer.parseInt(dadosSeparados[4]);
            String cidade = dadosSeparados[5];
            String estado = dadosSeparados[6];
            
            Cliente cliente = new Cliente(nome, cpf, tel, end, numero, cidade, estado);
            
            Boolean isCadastrado = iClienteDAO.cadastrar(cliente);
            
            if (isCadastrado) {
            	JOptionPane.showInputDialog(null, "Cliente cadastrato com sucesso","Sucesso",
            			JOptionPane.INFORMATION_MESSAGE);
            }
            
            // Restante do código...
        } else {
            // Dados incompletos, exibir mensagem de erro
            JOptionPane.showMessageDialog(null, "Cliente ja se encontra cadastrado", "Sucesso",
                    JOptionPane.ERROR_MESSAGE);
            
           
        }
	}

	private static boolean isCadastro(String opcao) {
		if ("1".equals(opcao)) {
			return true;
		}
		return false;
	}

	// Método para encerrar o programa
    private static void sair() {
        JOptionPane.showMessageDialog(null, "Até logo!", "Sair", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }

    // Método para verificar se a opção é válida
    private static boolean isOpcaoValida(String opcao) {
        // Verifica se a opção é igual a "1", "2", "3", "4" ou "5"
        if ("1".equals(opcao) || "2".equals(opcao) || "3".equals(opcao)
                || "4".equals(opcao) || "5".equals(opcao)) {
            return true; // Retorna verdadeiro se a opção for válida
        }
        return false; // Retorna falso se a opção for inválida
    }

    // Método para verificar se a opção é "5" (sair)
    private static boolean isOpcaoSair(String opcao) {
        if ("5".equals(opcao)) {
            return true; // Retorna verdadeiro se a opção for "5" (sair)
        }
        return false; // Retorna falso se a opção não for "5"
    }
}