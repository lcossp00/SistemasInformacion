package controlador;

import java.util.ArrayList;

public class Corregir 
{
    //VALORES
    static String dni = "";
    static String sinLetra;
    static char charExtranjer;
    static int extranj = 0;
    static int ultNum;
    static int diviDni;
    //LISTAS
    static ArrayList<Integer> id = new ArrayList<Integer>();
    static ArrayList<String> listCorrect = new ArrayList<String>();
    static ArrayList<Integer> listDuplicados = new ArrayList<Integer>();
    
    //CORRIGE EL DNI Y ENVIA UNA LISTA DE DNI QUE ESTAN CORRECTOS
    public ArrayList<String> corregirDni(ArrayList<String> listDni)
    {
        char [] actual_dni;
        String dni_sinNumero = "";
        String dni_correcto;
        int numDni;
        char letra;
        char letra_correcta;
        
        
        for(int i=0; i<listDni.size(); i++)
        {
           actual_dni = listDni.get(i).toCharArray();
           
           //SI ES LETRA, ES UN DNI EXTRANGERO SINO, ESPAÑOL
           if (Character.isLetter(actual_dni[0])) 
           {
        	   for(int j=1; j<=8; j++) 
        	   {
        		   dni_sinNumero = dni_sinNumero + actual_dni[j];  
        	   }
        	   numDni = Integer.parseInt(dni_sinNumero);
        	   letra = actual_dni[0];
        	   letra_correcta = calculaLetraExtranjeros(numDni);
           }else{
        	   //EXTRAEMOS LA LETRA DEL DNI
        	   for(int j=0; j<=7; j++) 
        	   {
        		   dni_sinNumero = dni_sinNumero + actual_dni[j];  
        	   }
        	   numDni = Integer.parseInt(dni_sinNumero);
        	   letra = actual_dni[8];
        	   letra_correcta = calculaLetra(numDni);
        	   
           }
    	   if(letra_correcta != letra) 
    	   {
    		   id.add(i);
    	   }
    	   dni_correcto = String.valueOf(numDni) + String.valueOf(letra_correcta);
    	   listCorrect.add(i,dni_correcto);
       }
        return listCorrect;
    }
        
    private char calculaLetra(int dni) 
    { 
        String juegoCaracteres="TRWAGMYFPDXBNJZSQVHLCKE"; 
        int modulo= dni % 23; 
        char letra = juegoCaracteres.charAt(modulo); 
        return letra; 
    }
    private char calculaLetraExtranjeros(int dni) 
    { 
        String juegoCaracteres="XYZ"; 
        int modulo= dni % 3; 
        char letra = juegoCaracteres.charAt(modulo); 
        return letra; 
    }
    
    //CORRIGE EL DNI Y ENVIA UNA LISTA DE POSICIONES DE LOS DNI ERRONEOS
    public ArrayList<Integer> corregirDniPosicion(ArrayList<String> listDni)
    {
    	corregirDni(listDni);
        return id;
    }
    
    //COMPRUEBA SI EL DNI ES EXTRANJERO, LA LETRA ES CORRECTA(SUSTITUYE POR LA CORRECTA SEA O NO SEA CORRECTA) Y ENVIA UN STRING CON EL DNI CORREGIDO
    public static String corregirLetra(String dni)
    {
    	
        return dni;
    }
    
    //COMPRUEBA LOS DUPLICADOS Y LOS AÑADE A LA LISTA DE LAS POSICIONES ERRONEAS Y RETORNA ESTA LISTA
    public ArrayList<Integer> duplicados(ArrayList<String> listDni,ArrayList<Integer> idPos)
    {
        //COMPRUEBA DUPLICADOS
    	//AÑADE A LA LISTA DE POS ERRONEAS
    	corregirDni(listDni);
        for(int i=0; i<listCorrect.size();i++) 
        {
        	for(int j=0; j<listCorrect.size();j++) 
        	{
        		if(listCorrect.get(i)==listCorrect.get(j) && i!=j) {
        			idPos.add(i);
        		}
        	}
        }
       
       return idPos;
    }
}
