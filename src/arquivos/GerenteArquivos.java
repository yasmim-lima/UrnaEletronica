package arquivos;

import java.io.IOException;

public class GerenteArquivos implements Arquivos{
	
	@Override
	public void cadastrarCandidatos(int numero, String nome) {
		
		//Cria o nome do caminho do arquivo utilizando o separador correto para cada sistema operacional
		String arquivo = "arqs" +
                System.getProperty("file.separator") +
                "Candidatos.txt";
		Candidato c1 = new Candidato(arquivo); //Cria um objeto candidato
		
		try {
			c1.cadastrarCandidato(numero, nome);
			System.out.println("Fim!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void registarVoto() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String mostrarCandidatos() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String listarVotos() {
		// TODO Auto-generated method stub
		return null;
	}
	
//	public static void main(String[] args) {
//		GerenteArquivos gerente = new GerenteArquivos();
//		gerente.cadastrarCandidatos(1, "Gabriel Sales");
//		gerente.cadastrarCandidatos(2, "Yasmim Lima");
//		gerente.cadastrarCandidatos(3, "Sofia Lima");
//	}
}