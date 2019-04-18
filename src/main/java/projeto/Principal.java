package projeto;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.JOptionPane;


import com.iesp.projeto.model.Cliente;
import com.iesp.projeto.model.Produto;

public class Principal {
	
public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("sistema");
		EntityManager conexao = emf.createEntityManager();
		
		
		int controle = 0;
		String mensagem = "1 - Adicionar Cliente \n"
				+ "2 - Editar Cliente \n"
				+ "3 - Excluir Cliente \n"
				+ "4 - Listar Cliente \n"
				+ "5 - Adicionar Produto \n"
				+ "6 - Editar Produto \n"
				+ "7 - Excluir Produto \n"	
				+ "8 - Listar Produto \n"
				+ "9 - Sair";
		
		while (controle != 9) {
			
			controle = Integer.parseInt(JOptionPane.showInputDialog(mensagem));
			
			switch (controle) {
			case 1:
				Cliente cliente = new Cliente();
				cliente.setNome(JOptionPane.showInputDialog("Informe o nome: "));
				cliente.setCpf(JOptionPane.showInputDialog("Informe o CPF: "));
				cliente.setGenero(JOptionPane.showInputDialog("Informe o Gênero: "));
				conexao.getTransaction().begin();
				conexao.persist(cliente);
				conexao.getTransaction().commit();
				JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!");
				break;

			case 2:
				int idClienteM = Integer.parseInt(JOptionPane.showInputDialog("Digite o id: "));
				Cliente cli = conexao.find(Cliente.class, idClienteM); //esse id já existe no banco
				cli.setNome(JOptionPane.showInputDialog("Informe o nome: "));
				cli.setCpf(JOptionPane.showInputDialog("Informe o CPF: "));
				cli.setGenero(JOptionPane.showInputDialog("Informe o Genero: "));
		        conexao.getTransaction().begin();
		        conexao.merge(cli);
		        conexao.getTransaction().commit();
		        JOptionPane.showMessageDialog(null, "Cliente Editado");
				break;
			
			case 3:
		       int idCliente = Integer.parseInt(JOptionPane.showInputDialog("DIGITE O ID: "));
		       Cliente c = conexao.find(Cliente.class, idCliente);
		       conexao.getTransaction().begin();
		       conexao.remove(c);
		       conexao.getTransaction().commit();
		       JOptionPane.showMessageDialog(null, "Cliente Excluido");
		       break;
			
			case 4:
				EntityTransaction tx = conexao.getTransaction();
				tx.begin();
				Query q = conexao.createQuery("select c from Cliente c where c.id > 0");
				List<Cliente> clien = q.getResultList();
				String saida = "";
                saida = "<html>"
                        + "<table border = '2'>"
                        + "<tr><th>ID</th><th>NOME</th><th>CPF</th><th>GÊNERO</th></tr>";

				for (Cliente cl : clien) {
					saida = saida + "<tr><td>" + cl.getId() + "</td><td>" + cl.getNome() + "</td><td>" + cl.getCpf() + "</td><td>"+ cl.getGenero() + "</td></tr>";	
				}
				saida = saida + "</table></html>";
                JOptionPane.showMessageDialog(null,saida);
				tx.commit();
				conexao.close();
				emf.close();
				break;
				
			case 5:
				Produto produto = new Produto();
				produto.setDescricao(JOptionPane.showInputDialog("Informe a Descrição: "));
				produto.setCodigoBarra(JOptionPane.showInputDialog("Informe o Codigo de Barra: "));
				produto.setValor(JOptionPane.showInputDialog("Informe o Valor: "));
				conexao.getTransaction().begin();
				conexao.persist(produto);
				conexao.getTransaction().commit();
				JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso!");
				break;

			case 6:
				Produto produto2 = new Produto();
				produto2.setId(12); //esse id já existe no banco
				produto2.setDescricao(JOptionPane.showInputDialog("Informe a Descrição: "));
				produto2.setCodigoBarra(JOptionPane.showInputDialog("Informe o Codigo de Barra: "));
				produto2.setValor(JOptionPane.showInputDialog("Informe o Valor: "));
		        conexao.getTransaction().begin();
		        conexao.merge(produto2);
		        conexao.getTransaction().commit();
		        JOptionPane.showMessageDialog(null, "Produto Alterado");
				break;
			
			case 7:
		       Produto produtoR = conexao.find(Produto.class, 1);// ja existe no banco
		       conexao.getTransaction().begin();
		       conexao.remove(produtoR);
		       conexao.getTransaction().commit();
		       JOptionPane.showMessageDialog(null, "Produto Excluido");
		       break;
			
			case 8:
				EntityTransaction txt = conexao.getTransaction();
				txt.begin();
				Query qr = conexao.createQuery("select p from Produto p where p.id > 0");
				List<Produto> produtos = qr.getResultList();
				String saidaP = "";
				saidaP = "<html>"
                        + "<table border = '2'>"
                        + "<tr><th>ID</th><th>DESCRIÇÃO</th><th>CÓDIGO DE BARRA</th><th>VALOR</th></tr>";

				for (Produto produ : produtos) {
					saidaP = saidaP + "<tr><td>" + produ.getId() + "</td><td>" + produ.getDescricao() + "</td><td>" + produ.getCodigoBarra() + "</td><td>"+ produ.getValor() + "</td></tr>";	
				}
				saidaP = saidaP + "</table></html>";
                JOptionPane.showMessageDialog(null,saidaP);
				txt.commit();
				conexao.close();
				emf.close();
			
			case 9:
				break;
			default:
				break;
			}
			
		}		
	}

}

