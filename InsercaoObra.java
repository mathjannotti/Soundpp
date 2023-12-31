import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class InsercaoObra {

	public static void main(String[] args) throws SQLException {
		Scanner scTitulo = new Scanner(System.in);
		System.out.println("Digite o titulo da obra:");
		String titulo = scTitulo.nextLine();

		while(titulo.contains(";")) {
			System.out.println("Titulo inválido");
			System.out.println("Digite o titulo da obra:");
			titulo = scTitulo.nextLine();
		}

		Scanner scLetra = new Scanner(System.in);
		System.out.println("Digite a letra da obra:");
		String letra = scLetra.nextLine();

		while(letra.contains(";")) {
			System.out.println("Letra inválido");
			System.out.println("Digite a letra da obra:");
			letra = scLetra.nextLine();
		}

		Scanner scDataLancamento = new Scanner(System.in);
		System.out.println("Digite a data de lancamento da obra (YYYY-MM-DD):");
		String dataLancamento = scDataLancamento.nextLine();

		while(dataLancamento.contains(";")) {
			System.out.println("Data inválida");
			System.out.println("Digite a data de lancamento da obra (YYYY-MM-DD):");
			dataLancamento = scDataLancamento.nextLine();
		}

		Scanner scCensura = new Scanner(System.in);
		System.out.println("Digite se a obra possui censura(1-sim; 0-nao):");
		Integer censura = scCensura.nextInt();

		Scanner scDuracao = new Scanner(System.in);
		System.out.println("Digite a duracao da obra(HH:MM:SS):");
		String duracao = scDuracao.nextLine();

		while(duracao.contains(";")) {
			System.out.println("Duracao inválida");
			System.out.println("Digite a duracao da obra(HH:MM:SS):");
			duracao = scDuracao.nextLine();
		}

		Scanner scCategoria = new Scanner(System.in);
		System.out.println("Digite a categoria da obra:");
		String categoria = scCategoria.nextLine();

		while(categoria.contains(";")) {
			System.out.println("Categoria inválida");
			System.out.println("Digite a categoria da obra:");
			categoria = scCategoria.nextLine();
		}

		Scanner numAutores = new Scanner(System.in);
		System.out.println("Digite o numero de autores: ");
		int numA = numAutores.nextInt();

		Scanner numProdutores = new Scanner(System.in);
		System.out.println("Digite o numero de produtores: ");
		int numP = numProdutores.nextInt();

		Scanner scIdAutor = new Scanner(System.in);

		CriaConexao criaConexao = new CriaConexao();
        Connection connection = criaConexao.recuperarConexao();
        Statement stm = connection.createStatement();
        stm.execute(("INSERT INTO obra (titulo, letra, data_lancamento, censura, duracao, categoria) VALUES ("+"'"+titulo+"'"+","+"'"+letra+"'"+","+"'"+dataLancamento+"'"+","+"'"+censura+"'"+","+"'"+duracao+"'"+","+"'"+categoria+"'"+");"), Statement.RETURN_GENERATED_KEYS);
        ResultSet rst = stm.getGeneratedKeys();
		while (rst.next()) {
            Integer id = rst.getInt(1);
            for(int i=0; i< numA; i++){
                System.out.println("Digite o id do autor: ");
                int autor_id = scIdAutor.nextInt();
                obra_autor x = new obra_autor();
                x.ObraAutor(autor_id, id);
            }

			for(int i=0; i< numP; i++){
                System.out.println("Digite o id do produtor: ");
                int produtor_id = scIdAutor.nextInt();
                obra_produtor y = new obra_produtor();
                y.ObraProdutor(produtor_id, id);
            }

            System.out.println(id);
        }


        connection.close();
        scTitulo.close();
        scLetra.close();
        scDataLancamento.close();
        scCensura.close();
        scDuracao.close();
        scCategoria.close();
		scIdAutor.close();
		numAutores.close();
		numProdutores.close();
	}
}