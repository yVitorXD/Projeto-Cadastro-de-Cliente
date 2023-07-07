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

        // Solicita��o da op��o ao usu�rio
        String opcao = JOptionPane.showInputDialog(null,
                "Digite 1 para cadastro, 2 para consultar, 3 para exclus�o, 4 para altera��o ou 5 para sair",
                "Cadastro",
                JOptionPane.INFORMATION_MESSAGE);

        // Loop para verificar se a op��o digitada � v�lida
        while (!isOpcaoValida(opcao)) {
            // Se a op��o for uma string vazia, chama o m�todo "sair()"
            if ("".equals(opcao)) {
                sair();
            }

            // Solicitar novamente a op��o ao usu�rio
            opcao = JOptionPane.showInputDialog(null,
                    "Op��o inv�lida! Digite 1 para cadastro, 2 para consultar, 3 para exclus�o, 4 para altera��o ou 5 para sair",
                    "Cadastro",
                    JOptionPane.INFORMATION_MESSAGE);
        }
        
        // Loop infinito se a op��o for v�lida
        while (isOpcaoValida(opcao)) {
            // Verificar se a op��o � "5" para chamar o m�todo "sair()"
            if (isOpcaoSair(opcao)) {
                sair();
            } else if (isCadastro(opcao)) {
            	String dados = JOptionPane.showInputDialog(null,
                        "Digite os dados do cliente separados por v�rgula, conforme o exemplo: nome, cpf, tel, end, numero, cidade, estado ...",
                        "Cadastro",
                        JOptionPane.INFORMATION_MESSAGE);
            	cadastrar(dados);
            }
            // Voc� pode adicionar aqui a l�gica para cada op��o v�lida (1, 2, 3, 4)
            // Certifique-se de atualizar o valor da vari�vel "opcao" dentro do loop
        }
    }

    private static void cadastrar(String dados) {
        // Separo tudo que est� vindo por v�rgula e coloco cada informa��o em uma posi��o do meu array
        String[] dadosSeparados = dados.split(",");
        
        // Verificar se todos os campos est�o preenchidos
        if (dadosSeparados.length == 7 && !dados.contains(",")) {
            // Todos os campos est�o preenchidos, ent�o crio o objeto Cliente com os dados
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
            
            // Restante do c�digo...
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

	// M�todo para encerrar o programa
    private static void sair() {
        JOptionPane.showMessageDialog(null, "At� logo!", "Sair", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }

    // M�todo para verificar se a op��o � v�lida
    private static boolean isOpcaoValida(String opcao) {
        // Verifica se a op��o � igual a "1", "2", "3", "4" ou "5"
        if ("1".equals(opcao) || "2".equals(opcao) || "3".equals(opcao)
                || "4".equals(opcao) || "5".equals(opcao)) {
            return true; // Retorna verdadeiro se a op��o for v�lida
        }
        return false; // Retorna falso se a op��o for inv�lida
    }

    // M�todo para verificar se a op��o � "5" (sair)
    private static boolean isOpcaoSair(String opcao) {
        if ("5".equals(opcao)) {
            return true; // Retorna verdadeiro se a op��o for "5" (sair)
        }
        return false; // Retorna falso se a op��o n�o for "5"
    }
}