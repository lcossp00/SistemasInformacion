package vista;

import controlador.ConvertirDOM;
import controlador.Corregir;
import controlador.GeneradorEmail;
import controlador.GeneradorIBAN;
import controlador.Objeto;
import java.io.File;
import java.util.ArrayList;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import modelo.LeerExcel;



public class Principal 
{
   
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
    static ConvertirDOM dom = new ConvertirDOM();
    
//PRACTICA 3
    //LLAMADAS
    static GeneradorIBAN iban = new GeneradorIBAN();
    static GeneradorEmail email = new GeneradorEmail();
    
    //LISTAS
    static ArrayList<Objeto> trabajadores = new ArrayList<Objeto>();
    static ArrayList<Objeto> ibanCorrecto = new ArrayList<Objeto>();
    static ArrayList<Objeto> emailCompleto = new ArrayList<Objeto>();
    
    
    
    public static void main(String[] args) throws ParserConfigurationException, TransformerException 
    {
        
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
       
       //APERTURA DEL OBJETO
       File fileExcel = new File("resources/SistemasInformacionII.xlsx");
       /*
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

        
        //IBAN CALCULADO
        trabajadores = read.devuelveTodo(fileExcel);
        ibanCorrecto = iban.geneararIban(trabajadores);
        
        
        
        //EMAIL

        emailCompleto = email.generarEmail(trabajadores);
  
        /*for(Objeto i: emailCompleto)
        {
           System.out.println(i.getEmail());
        }*/
        
        read.escribirIbanEmail(fileExcel, emailCompleto);

    }
    
    
}