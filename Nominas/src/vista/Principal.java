package vista;

import controlador.ConvertirDOM;
import controlador.Corregir;
import controlador.GeneradorEmail;
import controlador.GeneradorIBAN;
import controlador.Objeto;
import controlador.NominaSinProrrata;
import controlador.ValoresEstaticos;
import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;
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
//PRACTICA 4
    

    
    
    
    public static void main(String[] args) throws ParserConfigurationException, TransformerException, ParseException 
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
       
       //LLAMADAS
       listDNI = read.primeraLectura(fileExcel);
       id = correct.corregirDniPosicion(listDNI);
       listCorrect = correct.corregirDni(listDNI);
       idPos = correct.duplicados(listDNI,id);
       listErroneos = read.segundaLectura(fileExcel, listCorrect, idPos);
       System.out.println("Fichero renovado con los correctos");
       dom.crearDOM(listErroneos);
       System.out.println("Fichero xml creado");
 
    //PRACTICA 3

        
        //IBAN CALCULADO
        trabajadores = read.devuelveTodo(fileExcel);
        ibanCorrecto = iban.geneararIban(trabajadores);
        
        
        
        //EMAIL
        emailCompleto = email.generarEmail(ibanCorrecto);
        read.escribirIbanEmail(fileExcel, emailCompleto);
        dom.crearDOMIban(emailCompleto);
        
    //PRACTICA 4
        read.devuelveTodoHoja2(fileExcel);
        
        //REALIZAMOS LAS NOMINAS COMPLETAS 
        System.out.println("Introduzca una fecha(dd/mm/aaaa)");
        Scanner sc = new Scanner(System.in);
        String fecha = sc.nextLine();
        ValoresEstaticos.mes = fecha.substring(3,5);
        ValoresEstaticos.año = fecha.substring(6,10);
        
        /*Double value = 38930.0;
        Date javaDate= DateUtil.getJavaDate((double)value);
        System.out.println(new SimpleDateFormat("MM/dd/yyyy").format(javaDate));*/
       NominaSinProrrata nsp = new NominaSinProrrata();
       nsp.crearNominasSinProrrata(emailCompleto);

    }
    
    
}