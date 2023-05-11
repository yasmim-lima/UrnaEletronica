package arquivos;

import java.io.File;
import java.io.IOException;

public class GerenteArquivos implements Arquivos {

    @Override
    public boolean cadastrarCandidatos(String numero, String nome) {
        //Cria o nome do caminho do arquivo utilizando o separador correto para cada sistema operacional
        String arquivo = "arqs"
                + System.getProperty("file.separator")
                + "Candidatos.txt";
        Candidato c1 = new Candidato(numero, nome); //Cria um objeto candidato

        try {
            c1.cadastrarCandidato(arquivo);
            System.out.println("Fim!");
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean resetarCadastro() {

        File pastaImgs = new File("imgs");
        File[] files = pastaImgs.listFiles();
        int numImgs = files.length;
        System.out.println(numImgs);

        int cont = 0;
        if (numImgs != 0) {
            for (int i = 0; i < numImgs; i++) {
                String arquivo2 = "imgs"
                        + System.getProperty("file.separator")
                        + String.valueOf(i + 1)
                        + ".jpg";
                File arquivoApagar2 = new File(arquivo2);
                if (arquivoApagar2.delete()) {
                    cont++;
                } else {
                    System.out.println("Falha em apagar imagens!");
                }
            }
        }

        String arquivo1 = "arqs"
                + System.getProperty("file.separator")
                + "Candidatos.txt";
        File arquivoApagar1 = new File(arquivo1);
        if (arquivoApagar1.exists() && cont == numImgs){
            arquivoApagar1.delete();
            return true;
        }else if (!arquivoApagar1.exists() && cont == numImgs){return true;} 
        else {return false;}
        
//        if (arquivoApagar1.delete() && cont == numImgs) {
//            return true;
//        } else {
//            return false;
//        }
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
