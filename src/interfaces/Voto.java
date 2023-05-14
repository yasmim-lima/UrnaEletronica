/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaces;

/**
 *
 * @author Yasmim
 */
public class Voto {
    
    private String numeroCandidato;
    
    public Voto(String numeroCandidato){
        this.numeroCandidato = numeroCandidato;
    }
    
    public String getNumeroCandidato() {
        return numeroCandidato;
    }
    
    public void setNumeroCandidato(String numeroCandidato) {
        this.numeroCandidato = numeroCandidato;
    }
    
}
