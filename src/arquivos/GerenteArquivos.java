/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arquivos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gabriel S
 */
public class GerenteArquivos implements Arquivos {

    ArrayList<Candidato> candidatos = new ArrayList<>(); //Array geral com os candidatos
    ArrayList<Candidato> candidatosLidos = new ArrayList<>();

    @Override
    public boolean cadastrarCandidatos(String nome, String numero, String caminhoImagem, String extensaoImagem) {

        String nomeArquivo = "arqs"
                + System.getProperty("file.separator")
                + "Candidatos.txt";
        File arquivo = new File(nomeArquivo);
        
        if (arquivo.exists()) {
            try{
                Scanner ler = new Scanner(arquivo);
                while (ler.hasNextLine()){
                    String linha = ler.nextLine();
                    String[] partes = linha.split(",");
                    
                    String numeroLido = partes[0];
                    String nomeLido = partes[1];
                    Candidato candidatoLido = new Candidato(nomeLido, numeroLido);
                    candidatosLidos.add(candidatoLido);
                    System.out.println("Lendo arquivo pronto");
                    candidatosLidos.stream().forEach(c -> System.out.println("Nome: " + c.getNome() + " | Número: " + c.getNumero()));
                }
                ler.close();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(GerenteArquivos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        System.out.println("Quantidade de candidatos lidos eh: " + candidatosLidos.size());   
        
        Candidato candidato = new Candidato(nome, numero);
        if ((candidatos.size() <= 3) && candidatosLidos.size() <=3) {
            candidatos.add(candidato);
            System.out.println("Candidato Cadastrado com Sucesso!");
//            System.out.println("A quantidade de candidatos eh: " + candidatos.size());
            candidatos.stream().forEach(c -> System.out.println("Nome: " + c.getNome() + " | Número: " + c.getNumero()));

            File pastaDestino = new File("imgs");

            try {
                InputStream is = new FileInputStream(caminhoImagem);
                OutputStream os = new FileOutputStream(new File(pastaDestino, numero + extensaoImagem));
                byte[] buffer = new byte[1024];
                int length;
                while ((length = is.read(buffer)) > 0) {
                    os.write(buffer, 0, length);
                }
                is.close();
                os.close();
            } catch (IOException e) {
//            e.printStackTrace();
            }
            return true;

        } else {
            System.out.println("Erro ao Cadastrar Candidato!");
//            System.out.println(candidatos.size());
            try {
                registrarCandidatos();
            } catch (IOException ex) {
                Logger.getLogger(GerenteArquivos.class.getName()).log(Level.SEVERE, null, ex);
            }
            return false;
        }
        
    }

    public void registrarCandidatos() throws IOException {
        String nomeArquivo = "arqs"
                + System.getProperty("file.separator")
                + "Candidatos.txt";

        File arquivo = new File(nomeArquivo);
        HashStringGeneration hashG = new HashStringGeneration(); //Cria um objeto do Gerador de Hash de String

        for (Candidato c : candidatos) {
            String nome = c.getNome();
            String numero = c.getNumero();
            String texto = (numero + "," + nome + ",");

            try {
                String hash = hashG.generateHash(texto); //Cria uma string pra receber o valor da hash
                FileWriter escritor = new FileWriter(nomeArquivo, true); //Cria um objeto FileWriter
                escritor.write(texto + hash + "\n"); //Faz a escrita no arquivo
                escritor.close(); //Fecha a stream do escritor

                System.out.println("Candidato Registrado com Sucesso!"); //Avisa que a escrita dos dados do candidato no arquivo acabou
            } catch (NoSuchAlgorithmException e) {
                System.err.println("Erro ao gerar hash: " + e.getMessage());
            }
        }

    }

    @Override
    public void registarVoto() {

    }

    @Override
    public String mostrarCandidatos() {
        return "Adagas";
    }

    @Override
    public String listarVotos() {
        return "Voadoras";
    }

//    public static void main(String[] args) {
//        GerenteArquivos gerente = new GerenteArquivos();
//        gerente.cadastrarCandidatos("Gabriel", "1");
//        gerente.cadastrarCandidatos("Yasmim", "2");
//        gerente.cadastrarCandidatos("Alice","3");
//        gerente.cadastrarCandidatos("Sofia", "4");
//        gerente.cadastrarCandidatos("Fernanda", "5");
//    }
}
