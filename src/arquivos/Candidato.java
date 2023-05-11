package arquivos;

import java.io.FileWriter;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class Candidato {

    private String nomeArquivo;
    private String numero;
    private String nome;

    public Candidato(String numero, String nome) {
        this.numero = numero;
        this.nome = nome;
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void cadastrarCandidato(String nomeArquivo) throws IOException {

        HashStringGeneration hashG = new HashStringGeneration(); //Cria um objeto do Gerador de Hash de String
        String texto = (this.numero + "," + this.nome + ",");

        try {
            String hash = hashG.generateHash(texto); //Cria uma string pra receber o valor da hash
            FileWriter escritor = new FileWriter(nomeArquivo, true); //Cria um objeto FileWriter
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
