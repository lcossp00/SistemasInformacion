/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import controlador.GeneradorIBAN;
import java.io.File;
import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import modelo.LeerExcel;



public class Principal 
{
   /*
   //VARIABLES
    static int i = 0;
    //LISTAS
    static ArrayList<String> listDNI = new ArrayList<String>();
    static ArrayList<Integer> id = new ArrayList<Integer>();
    static ArrayList<String> listCorrect = new ArrayList<String>();
    static ArrayList<Integer> idPos = new ArrayList<Integer>();
     static ArrayList<Objeto> listErroneos = new ArrayList<Objeto>();
    //LLAMADAS
    static LeerExcel read = new LeerExcel();
    static Corregir correct = new Corregir();
   // static ConvertirDOM dom = new ConvertirDOM();*/
    //PRACTICA 3
    static LeerExcel read = new LeerExcel();
    static GeneradorIBAN iban = new GeneradorIBAN();
    static ArrayList<String> cuentasBancarias = new ArrayList<String>();
    static ArrayList<String> nacion = new ArrayList<String>();
    static ArrayList<String> ibanCorrecto = new ArrayList<String>();
    
    
    public static void main(String[] args) throws ParserConfigurationException, TransformerException 
    {
        // TODO code application logic here
        
        //PRACTICA 1
        /*System.out.println("Introduzca un DNI:");
        Scanner sc = new Scanner(System.in);
        String dni = sc.next();
        
        NewHibernateUtil sesion = new NewHibernateUtil();
        boolean correcto = sesion.controlDni(dni);
        if(correcto == true)
        {
           sesion.datos(dni);
           sesion.incrementar(dni);
           sesion.eliminar(); 
        }
        else
        {
            System.out.println("ERROR");
        }*/
        
        //PRACTICA 2
        /*
       //APERTURA DEL OBJETO
       File fileExcel = new File("resources/SistemasInformacionII.xlsx");
       
       //LLAMADAS
       listDNI = read.primeraLectura(fileExcel);
       id = correct.corregirDniPosicion(listDNI);
       listCorrect = correct.corregirDni(listDNI);
       idPos = correct.duplicados(listDNI,id);
       listErroneos = read.segundaLectura(fileExcel, listCorrect, idPos);
       System.out.println("Fichero renovado con los correctos");
       dom.crearDOM(listErroneos);
       System.out.println("Fichero xml creado");*/
 
        //PRACTICA 3
        File fileExcel = new File("resources/SistemasInformacionII.xlsx");
        cuentasBancarias = read.devuelveCuenta(fileExcel);
        nacion = read.devuelveNacion(fileExcel);
        ibanCorrecto = iban.geneararIban(cuentasBancarias,nacion);

        
    }
    
    
}
