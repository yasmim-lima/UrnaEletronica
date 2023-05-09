package arquivos;

import java.io.FileWriter;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class Candidato {
	
	private String nomeArquivo;
	
	public Candidato(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}
	
	public void cadastrarCandidato(int numero, String nome) throws IOException {
		
		HashStringGeneration hashG = new HashStringGeneration(); //Cria um objeto do Gerador de Hash de String
		String texto = (numero + "," + nome + ",");
		
		try {
			String hash = hashG.generateHash(texto); //Cria uma string pra receber o valor da hash
			FileWriter escritor = new FileWriter(this.nomeArquivo, true); //Cria um objeto FileWriter
			escritor.write(texto + hash + "\n"); //Faz a escrita no arquivo
			escritor.close(); //Fecha a stream do escritor
			
			System.out.println("Candidato Cadastrado!"); //Avisa que a escrita dos dados do candidato no arquivo acabou
		} catch (NoSuchAlgorithmException e) {
			System.err.println("Erro ao gerar hash: " + e.getMessage());
		}			
	}
	
//	public static void main(String[] args) {
//		Candidato c1 = new Candidato("arqs//Candidatos.txt");
//		try {
//			c1.cadastrarCandidato(1, "Gabriel Sales");
//			c1.cadastrarCandidato(2, "Yasmim Lima");
//			System.out.println("Fim!");
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
//	}
	
}
