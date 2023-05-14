/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package arquivos;

/**
 *
 * @author Gabriel S
 */
public class Candidato {

    private String nome;
    private String numero;

    public Candidato(String nome, String numero) {
        this.nome = nome;
        this.numero = numero;
    }
    public Candidato(){}

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
    
}
