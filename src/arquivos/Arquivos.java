package arquivos;

public interface Arquivos {
	public boolean cadastrarCandidatos(String numero, String nome);
	public void registarVoto();
	public String mostrarCandidatos();
	public String listarVotos();
}
