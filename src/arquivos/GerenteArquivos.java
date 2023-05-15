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
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Map;
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
    ArrayList<String> votos = new ArrayList<> ();
    ArrayList<Candidato> votosComputados = new ArrayList<>();
   

    @Override
    public boolean cadastrarCandidatos(String nome, String numero, String caminhoImagem, String extensaoImagem) {
        //Gera o nome do arquivo dos candidatos
        String nomeArquivo = "arqs"
                + System.getProperty("file.separator")
                + "Candidatos.txt";
        File arquivo = new File(nomeArquivo); //Cria um objeto arquivo
        if (arquivo.exists()) { //Verifica se já existe o arquivo de candidatos
            try {
                Scanner ler = new Scanner(arquivo); //Cria objeto scanner
                while (ler.hasNextLine()) { //Cria um loop para ler todas as linhas do arquivo Candidatos.txt
                    String linha = ler.nextLine(); //String que recebe a linha lida
                    String[] partes = linha.split(","); //Array de String que recebe as partes informações das linhas separadas por ,

                    String numeroLido = partes[0]; //Atribui o nome lido a variavel nomeLido
                    String nomeLido = partes[1]; //Atribui o numero lido a variavel numeroLido
                    Candidato candidatoLido = new Candidato(nomeLido, numeroLido); //recria o candidato para adicionar na Array de candidatosLidos
                    candidatosLidos.add(candidatoLido); //Adiciona o candidato a Array candidatosLidos
//                    System.out.println("Lendo arquivo pronto"); //Avisa que leu o arquivo
//                    candidatosLidos.stream().forEach(c -> System.out.println("Nome: " + c.getNome() + " | Número: " + c.getNumero())); //Imprime os candidadtos que estão presentes na Array candidatosLidos
                }
                ler.close();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(GerenteArquivos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

//        System.out.println("Quantidade de candidatos lidos eh: " + candidatosLidos.size()); Mostra o numero de candidatos que tem no arquivo canditos se já existir 
        Candidato candidato = new Candidato(nome, numero);
        if ((candidatos.size() <= 3) && candidatosLidos.size() <= 3) { //Verifica se já tem 4 candidato cadastrados pra não adicionar mais
            candidatos.add(candidato);
            System.out.println("Candidato Cadastrado com Sucesso!");
//            System.out.println("A quantidade de candidatos eh: " + candidatos.size()); //Mostra o tamanho da Array candidato
            candidatos.stream().forEach(c -> System.out.println("Nome: " + c.getNome() + " | Número: " + c.getNumero())); //Mostra os candidatos que estão no Array candidatos

            //Bloco que copia a imagem da pasta do computador da pessoa para a pasta de imagens do projeto
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
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(GerenteArquivos.class.getName()).log(Level.SEVERE, null, ex);
            }
            return false;
        }

    }

    public void registrarCandidatos() throws IOException, NoSuchAlgorithmException {
        //Essa função faz o registro de fato dos candidatos na Array candidatos parao arquivo Candidatos.txt
        String nomeArquivoCandidato = "arqs"
                + System.getProperty("file.separator")
                + "Candidatos.txt";

        File arquivo = new File(nomeArquivoCandidato);
        HashStringGeneration hashG = new HashStringGeneration(); //Cria um objeto do Gerador de Hash de String

        for (Candidato c : candidatos) {
            String nome = c.getNome();
            String numero = c.getNumero();
            String texto = (numero + "," + nome + ",");

            try {
                String hash = hashG.generateHash(texto); //Cria uma string pra receber o valor da hash
                FileWriter escritor = new FileWriter(nomeArquivoCandidato, true); //Cria um objeto FileWriter
                escritor.write(texto + hash); //Faz a escrita no arquivo
                escritor.write(System.getProperty("line.separator"));
                escritor.close(); //Fecha a stream do escritor

                System.out.println("Candidato Registrado com Sucesso!"); //Avisa que a escrita dos dados do candidato no arquivo acabou
            } catch (NoSuchAlgorithmException e) {
                System.err.println("Erro ao gerar hash: " + e.getMessage());
            }
        }
        //Bloco que criar o arquivo de Arquivos e adiciona a hash para o arquivo Candidatos.txt
        String nomeArquivoArquivos = "arqs"
                + System.getProperty("file.separator")
                + "Arquivos.txt";
        File fileArquivos = new File(nomeArquivoArquivos); //cria o objeto tipo File para o arquivo Arquivos.txt
        if (!fileArquivos.exists()) { //Verifica se o arquivo Arquivos.txt existe ou não

            HashArquivoGeneration hashGA = new HashArquivoGeneration(); //Cria um objeto do Gerador de Hash para Arquivo

            try {
                String hashArqCandidatos = hashGA.generateHash(arquivo); //String que recebe a hash gerada para o arquivo de Arquivos
                FileWriter escritor = new FileWriter(nomeArquivoArquivos, true); //Cria um objeto FileWriter
                escritor.write("Candidatos" + "," + hashArqCandidatos); //Faz a escrita no arquivo
                escritor.write(System.getProperty("line.separator"));
                escritor.close();//Fecha a stream do escritor
            } catch (NoSuchAlgorithmException e) {
                System.err.println("Erro ao gerar hash: " + e.getMessage());
            }
        }
    }

    @Override
    public void cadastrarVoto(String num) {
        String nomeCandidato = "";
        String nomeArquivo = "arqs"
                + System.getProperty("file.separator")
                + "Candidatos.txt";
        File arquivo = new File(nomeArquivo); //Cria um objeto arquivo
        try {
            Scanner ler = new Scanner(arquivo); //Cria objeto scanner
            while (ler.hasNextLine()) { //Cria um loop para ler todas as linhas do arquivo Candidatos.txt
                String linha = ler.nextLine(); //String que recebe a linha lida
                String[] partes = linha.split(","); //Array de String que recebe as partes informações das linhas separadas por ,

                String numeroLido = partes[0]; //Atribui o nome lido a variavel nomeLido
                String nomeLido = partes[1]; //Atribui o numero lido a variavel numeroLido
                Candidato candidatoLido = new Candidato(nomeLido, numeroLido); //recria o candidato para adicionar na Array de candidatosLidos
                candidatosLidos.add(candidatoLido); //Adiciona o candidato a Array candidatosLidos
            }
            ler.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GerenteArquivos.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (Candidato c : candidatosLidos) {
            int numCandidato = Integer.parseInt(c.getNumero());
            int numInt = Integer.parseInt(num);
            if (numInt == numCandidato) {
                nomeCandidato = c.getNome();
            }
        }
        String textoVoto = num + "," + nomeCandidato + ",";
        votos.add(textoVoto);
        System.out.println(textoVoto);
        votos.stream().forEach(s -> System.out.println());
    }

    public void registrarVoto() {
        //Bloco que faz o registro dos votos de fato
        String nomeArqVotos = "arqs"
                + System.getProperty("file.separator")
                + "Votos.txt";
        File arqVotos = new File(nomeArqVotos);
        for (String voto : votos) {
            HashStringGeneration hashG = new HashStringGeneration();
            try {
                String hash = hashG.generateHash(voto); //Gera a hash da linha
                FileWriter Writer = new FileWriter(nomeArqVotos, true);
                Writer.write(voto + hash);
                Writer.write(System.getProperty("line.separator"));
                Writer.close();
            } catch (Exception e) {
            }
            votos.stream().forEach(s -> System.out.println());
        }
        //Bloco que criar o arquivo de Arquivos e adiciona a hash para o arquivo Candidatos.txt
        String nomeArquivoArquivos = "arqs"
                + System.getProperty("file.separator")
                + "Arquivos.txt";
        File fileArquivos = new File(nomeArquivoArquivos); //cria o objeto tipo File para o arquivo Arquivos.txt
        if (fileArquivos.exists()) { //Verifica se o arquivo Arquivos.txt existe ou não
            HashArquivoGeneration hashGA = new HashArquivoGeneration(); //Cria um objeto do Gerador de Hash para Arquivo
            try {
                String hashArqVotos = hashGA.generateHash(arqVotos); //String que recebe a hash gerada para o arquivo de Arquivos
                FileWriter escritor = new FileWriter(nomeArquivoArquivos, true); //Cria um objeto FileWriter
                escritor.write("Votos" + "," + hashArqVotos); //Faz a escrita no arquivo
                escritor.write(System.getProperty("line.separator"));
                escritor.close();//Fecha a stream do escritor
            } catch (NoSuchAlgorithmException e) {
                System.err.println("Erro ao gerar hash: " + e.getMessage());
            } catch (IOException ex) {
                Logger.getLogger(GerenteArquivos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public ArrayList<Candidato> mostrarCandidatos() {
        return null;
    }

    @Override
    public String listarVotos() {

        String nomeArquivo = "arqs"
                + System.getProperty("file.separator")
                + "votos.txt";
        File arquivo = new File(nomeArquivo); //Cria um objeto arquivo
        if (arquivo.exists()) { //Verifica se já existe o arquivo de candidatos
            try {
                Scanner ler = new Scanner(arquivo); //Cria objeto scanner
                while (ler.hasNextLine()) { //Cria um loop para ler todas as linhas do arquivo Candidatos.txt
                    String linha = ler.nextLine(); //String que recebe a linha lida
                    String[] partes = linha.split(","); //Array de String que recebe as partes informações das linhas separadas por ,

                    String numeroLido = partes[0]; //Atribui o numero lido a variavel numeroLido
                    String nomeLido = partes[1]; //Atribui o nome lido a variavel nomeLido
                    Candidato votoComputado = new Candidato(nomeLido, numeroLido); //recria o candidato para adicionar na Array de votosComputados
                    votosComputados.add(votoComputado); //Adiciona o candidato a Array votosComputados    
                }
                ler.close();

                for (Candidato candidato : votosComputados) {
                    System.out.println("Nome: " + candidato.getNome() + " | Número: " + candidato.getNumero());
                }
            } catch (FileNotFoundException ex) {
                Logger.getLogger(GerenteArquivos.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return null;
    }

    public static void main(String[] args) {
        GerenteArquivos gerente = new GerenteArquivos();
    }
}
