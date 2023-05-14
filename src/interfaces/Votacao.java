/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaces;

import java.util.ArrayList;

/**
 *
 * @author Yasmim
 */
public class Votacao {
    
    ArrayList<Voto> votos = new ArrayList();
    
    public void receberVoto(Voto voto){
        votos.add(voto);
    }
    
    public String numeroVotos(){
        return votos.size() + "";
    }
    
}
