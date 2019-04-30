/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controlador;

import java.util.ArrayList;

/**
 *
 * @author User
 */
public class GeneraNomina {
    static ArrayList<Objeto> personas = new ArrayList<Objeto>();
    static ArrayList<String> categorias = new ArrayList<String>();
    static ArrayList<Integer> salarioBase = new ArrayList<Integer>();
    static ArrayList<Integer> complementos = new ArrayList<Integer>();
    
   
    //Necesito los demas array list
    public void primerPaso(ArrayList<Objeto> personas){
        this.personas = personas;
    }
    public void calculaSalarioBase(){ //Se toma el salario base y se divide entre 14 simpre??
        String categ;
        for(Objeto i : personas){
            categ = i.getCategoria();
            for(String jCateg : categorias){
                if(categ==jCateg){
                    //i.se
                }
            }
        }
    }
    
    public boolean necesitaPorrateo(){
        
        return true;
    }
    public void calculaPorrateo(){
        
    }
    public void calculaComplemento(){
        
    }
    public void calculaNumTrienios(){
        
    }
    public void calculaValorTrienios(){
        
    }
    public void calculaSS(){
        
    }
    public void calculaDesempleo(){
        
    }
    public void calculaFormacion(){
        
    }
    public void calculaIRPF(){
        
    }
    public void calculoLiquidoMensual(){
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
