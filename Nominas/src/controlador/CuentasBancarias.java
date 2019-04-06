/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controlador;

/**
 *
 * @author User
 */
public class CuentasBancarias 
{
    String codigoCuenta; //20960043043554600000
    String codPais;  //ES
    String codPaisNum; //1428 se guardan los numeros correspondientes a ES, se calcula en la clase GeneradorIBAN
    String codCompleto; //Todos los strings juntos, se calcula en la clase GeneradorIBAN
    String resModulo97;
    String IBAN;
    
    //Metodos:
    //SETTERS
    public void setCodPaisNum(String codPaisNum){
        this.codPaisNum = codPaisNum;
    }
    public void setCodCompleto(String codCompleto){
        this.codCompleto = codCompleto;
    }
    public void setResModulo97(String resModulo97){
        this.resModulo97 = resModulo97;
    }
    public void setIBAN(String IBAN){
        this.IBAN = IBAN;
    }
    
    //GETTERS
    public String getCodigoCuenta(){
        return codigoCuenta;
    }
    public String getCodPais(){
        return codPais;
    }
    public String getCodPaisNum(){
        return codPaisNum;
    }
    public String getCodCompleto(){
        return codCompleto;
    }
    public String getResModulo97(){ //ES03 1111 2222 00 4444444444 = Solo contiene el 03
        return resModulo97;
    }
    public String getIBAN(){ // ES03 1111 2222 00 4444444444 = Lo contiene todo
        return IBAN;
    }
    
    
    
    
}
