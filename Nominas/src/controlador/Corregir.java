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
        for(int i = 1; i<listDni.size();++i)
        {
            dni = listDni.get(i);
            
            //COMPRUEBA QUE EL DNI NO SE VACIO(BLANK,NULL O EMPY)
            if(dni.equals("BLANK") || dni == null || dni.isEmpty())
            {
                listCorrect.add(dni);
            }
            //COMPRUEBA LA LETRA DEL DNI ES CORRECTA
            else
            {
                listDni.set(i,corregirLetra(dni));
                
                if(dni.charAt(dni.length() - 1) != listDni.get(i).charAt(dni.length() - 1))
                {
                    if(listCorrect.contains(dni)){   
                    }else
                    {
                       listCorrect.add(dni); 
                    }
                }
            }
            
            
        }
        return listCorrect;
    }
    
    //CORRIGE EL DNI Y ENVIA UNA LISTA DE POSICIONES DE LOS DNI ERRONEOS
     public ArrayList<Integer> corregirDniPosicion(ArrayList<String> listDni)
    {
        for(int i = 1; i<listDni.size(); i++)
        {
            dni = listDni.get(i);
            
            //COMPRUEBA QUE EL DNI NO SE VACIO(BLANK,NULL O EMPY)
            if(dni.isEmpty() || dni == null || dni.equals("BLANK"))
            {
                id.add(i);
            }
            //COMPRUEBA LA LETRA DEL DNI ES CORRECTA
            else
            {
                listDni.set(i,corregirLetra(dni));
                
                if(dni.charAt(dni.length() - 1) != listDni.get(i).charAt(dni.length() - 1))
                {
                    if(listCorrect.contains(dni)){   
                    }else
                    {
                       id.add(i); 
                    }
                }
            }

        }   
        
        return id;
    }
    
    //COMPRUEBA SI EL DNI ES EXTRANJERO, LA LETRA ES CORRECTA(SUSTITUYE POR LA CORRECTA SEA O NO SEA CORRECTA) Y ENVIA UN STRING CON EL DNI CORREGIDO
    public static String corregirLetra(String dni)
    {
        charExtranjer = dni.charAt(0);
        
        //CAMBIA LAS LETRA DE LOS DNI EXTRANJEROS POR NUMEROS
        if(charExtranjer == 'y' || charExtranjer == 'Y')
        {
            dni = "1" + dni.substring(1);
            extranj = 1;
        }
        if(charExtranjer == 'x' || charExtranjer == 'X')
        {
             dni = "0" + dni.substring(1);
            extranj = 1;
        }
        if(charExtranjer == 'z' || charExtranjer == 'Z')
        {
             dni = "2" + dni.substring(1);
            extranj = 1;
        }
        
        sinLetra = dni.substring(0, dni.length() - 1); 
        ultNum = Integer.parseInt(dni.substring(0, dni.length() - 1));
        diviDni = ultNum % 23;
        
        //COLOCA LA LETRA CORRECTA EN EL DNI
        switch(diviDni)
        {
            case 0:
                dni = sinLetra + "T";
                break;
            case 1:
                dni = sinLetra + "R";
                break;
            case 2:
                dni = sinLetra + "W";
                break;
            case 3:
                dni = sinLetra + "A";
                break;
            case 4:
                dni = sinLetra + "G";
                break;
            case 5:
                dni = sinLetra + "M";
                break;
            case 6:
                dni = sinLetra + "Y";
                break;
            case 7:
                dni = sinLetra + "F";
                break;
            case 8:
                dni = sinLetra + "P";
                break;
            case 9:
                dni = sinLetra + "D";
                break;
            case 10:
                dni = sinLetra + "X";
                break;
            case 11:
                dni = sinLetra + "B";
                break;
            case 12:
                dni = sinLetra + "N";
                break;
            case 13:
                dni = sinLetra + "J";
                break;
            case 14:
                dni = sinLetra + "Z";
                break;
            case 15:
                dni = sinLetra + "S";
                break;
            case 16:
                dni = sinLetra + "Q";
                break;
            case 17:
                dni = sinLetra + "V";
                break;
            case 18:
                dni = sinLetra + "H";
                break;
            case 19:
                dni = sinLetra + "L";
                break;
            case 20:
                dni = sinLetra + "C";
                break;
            case 21:
                dni = sinLetra + "K";
                break;
            case 22:
                dni = sinLetra + "E";
                break;
        }
        
        //SI EL DNI ERA EXTRANJERO LO VUELVE A SU ESTADO ORIGINAL(CON LA LETRA AL PRINCIPIO) Y CON SU LETRA CORRECTA
        switch(extranj)
        {
            case 0:
                break;
            case 1:
                dni = charExtranjer + dni.substring(1);
                break;
        }
        
        dni = dni.toUpperCase();
        return dni;
    }
    
    //COMPRUEBA LOS DUPLICADOS Y LOS AÑADE A LA LISTA DE LAS POSICIONES ERRONEAS Y RETORNA ESTA LISTA
    public ArrayList<Integer> duplicados(ArrayList<String> listDni,ArrayList<Integer> idPos)
    {
        //COMPRUEBA DUPLICADOS
        for (int i = 0; i < listDni.size() ; i++)
        {
           for (int j = i + 1 ; j < listDni.size() ; j++)
           {
               if (listDni.get(i).equals(listDni.get(j))) 
               {
                   listDuplicados.add(i);
               } 
           } 
       }
       //AÑADE A LA LISTA DE POS ERRONEAS
        for(int j:listDuplicados){
            if(idPos.contains(j)){
            }else
            {
                idPos.add(j);
            }
        }
       
       return idPos;
    }
}
