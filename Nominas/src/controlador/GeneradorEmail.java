package controlador;

import java.util.ArrayList;

public class GeneradorEmail 
{
    static ArrayList<String> email = new ArrayList<String>();
    static ArrayList<String> nombre = new ArrayList<String>();
    static ArrayList<String> apellido1 = new ArrayList<String>();
    static ArrayList<String> apellido2 = new ArrayList<String>();
    static ArrayList<String> empresa = new ArrayList<String>();
    static ArrayList<String> emailCompleto = new ArrayList<String>();
    static int count = 0;
    static int repe;
    
    //EN ESTE METODO GENERAMOS LOS EMAILS Y LES GUARDAMOS EN EL OBJETO
    public ArrayList<Objeto> generarEmail(ArrayList<Objeto> trabajadores)
    {
        //SACAMOS LOS NOMBRES LOS APELLIDOS DEL OBJETO PARA TRABAJAR CON ELLOS.
        for(Objeto obj: trabajadores)
        {
            nombre.add(obj.getNombreTrabajador());
            apellido1.add(obj.getPrimerApell());
            apellido2.add(obj.getSegApell());
            empresa.add(obj.getNombreEmpre());
        }
        
        //CREAMOS LA PRIMERA PARTE DEL EMAIL
        for(String i : nombre)
        {
          email.add(i.toLowerCase().charAt(0) + "");
        }
        
        for(String j : apellido1)
        { 
          email.set(count, email.get(count) + j.toLowerCase().charAt(0)); 
          count++;
        }
        
        count = 0;
        for(String n : apellido2)
        {
          if(n.equals("BLANK") || n == null || n.isEmpty())
          {
              
          }
          else
          {
             email.set(count, email.get(count) + n.toLowerCase().charAt(0)); 
          } 
          count++;
        }

        
        //BUSCAMOS LOS REPETIDOS Y PONEMOS EL 00 EN CASO CONTRARIO
        ArrayList<Integer> listaRepeticiones = new ArrayList<Integer>();
        
        repe = 0;
        for(int i = 0; i < email.size(); i++)
        {
            for(int j = 0; j < i;j++)
            {
                if(email.get(j).equals(email.get(i)))
                {
                   repe++;
                }
            }
            
            listaRepeticiones.add(repe);
            repe = 0;
        }
        
        for(int i = 0; i < email.size(); i++)
        {
            int repeticiones = listaRepeticiones.get(i);
            String nuevo = "";
            
            if(repeticiones < 10)
            {
                nuevo = email.get(i) + "0" + repeticiones;
                email.set(i, nuevo);
            }
            else
            {
                nuevo = email.get(i) + repeticiones;
                email.set(i, nuevo);
            }
        }
        
        
        count = 0;
        
        //TERMINAMOS DE COMPLETAR EL EMAIL CON EL @NOMBREEMPRESA.ES
        for(String i: email)
        {
           email.set(count, email.get(count) + "@" + empresa.get(count).toLowerCase() + ".es");
           //System.out.println(email.get(count));
           count++;
        }
        count = 0;
        
        //RELLENAMOS EL OBJETO CON LOS EMAILS
        for(Objeto obj: trabajadores)
        {
            obj.setEmail(email.get(count));
            count++;
        }
        
        return trabajadores;
    }
}
