package arquivos;

import java.util.ArrayList;

public interface Arquivos {
	public boolean cadastrarCandidatos(String numero, String nome, String caminhoImagem, String extensãoImagem);
	public void cadastrarVoto(String num);
	public ArrayList<Candidato> mostrarCandidatos();
	public String listarVotos();
}
