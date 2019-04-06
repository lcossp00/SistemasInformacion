package controlador;


import java.math.BigInteger;
import java.util.ArrayList;

public class GeneradorIBAN 
{

    static ArrayList<Integer> segundaParteInt = new ArrayList<Integer>();
    static ArrayList<Integer> primeraParteInt = new ArrayList<Integer>();
    
    static String cuenta;
    static String nacion;
    static String primeraParte;
    static String segundaParte;
    static String digitosControlOld;

    
    
    
    
    //GENERA EL IBAN Y CORRIGE LE CCC
    public ArrayList<Objeto> geneararIban(ArrayList<Objeto> cuentasBancarias)
    {
        for(Objeto i : cuentasBancarias)
        {
            if(i.getCuenta() != null && i.getCuenta() != "" && i.getCuenta() != "BLANK")
            {
                cuenta = i.getCuenta();
                nacion = i.getNacion();

                //SEPARAMOS POR PARTES LA CCC
                primeraParte = "00" + cuenta.substring(0,8);
                digitosControlOld = cuenta.substring(8,10);
                segundaParte = cuenta.substring(10,20);
                
                primeraParteInt = new ArrayList<Integer>();
                segundaParteInt = new ArrayList<Integer>();

                //AÑADIMOS EN UN ARRAYLSIT UNO A UNA LOS NUMEROS DE LA PRIMERA PARTE Y LA SEGUNDA
                for(int j= 0;j < primeraParte.length();j++)
                {
                    primeraParteInt.add(Integer.parseInt("" + primeraParte.charAt(j)));
                    segundaParteInt.add(Integer.parseInt("" + segundaParte.charAt(j)));
                }
                
                //CREAMOS EL NUEVO DIGITO DE CONTROL
                String nuevoDigito = Integer.toString(sumaNum(primeraParteInt)) + Integer.toString(sumaNum(segundaParteInt));

                //SI NO ES CORRECTO LO CORREGIMOS LA CUENTA
                if(!digitosControlOld.equals(nuevoDigito))
                {
                    i.setcuentaErronea(cuenta);
                    cuenta = cuenta.substring(0,8) + nuevoDigito + cuenta.substring(10,20);
                }

                //CREAMOS EL IBAN DIVIDIENDO ENTRE 97 Y LUEGO EL RESTO RESTALO DE 98
                String nacionPrimeraLetra = Integer.toString(convertirLetra(nacion.charAt(0)));
                String nacionSegundaLetra = Integer.toString(convertirLetra(nacion.charAt(1)));
                String letrasNacion = Integer.toString(convertirLetra(nacion.charAt(0))) + Integer.toString(convertirLetra(nacion.charAt(1))) + "00";
                String division = cuenta + letrasNacion;

                BigInteger intConvertiodo= new BigInteger(division);
                BigInteger resto;
                int resto2;

                resto = intConvertiodo.remainder(BigInteger.valueOf(97));
                resto2 = resto.intValue();
                resto2 = 98 - resto2;

                letrasNacion = nacion + resto2;

                String iban = letrasNacion + cuenta;

                i.setCuenta(cuenta);
                i.setIban(iban);
            }
        }
        
        
        return cuentasBancarias;
    }
    
    //DEVUELVE 2N MOD 11
    public int mod(int num)
    {
        switch(num)
        {
            case 0:
              return 1;
            case 1:
              return 2;  
            case 2:
              return 4;
            case 3:
              return 8;
            case 4:
              return 5;
            case 5:
              return 10;
            case 6:
              return 9;
            case 7:
              return 7;
            case 8:
              return 3;
            case 9:
              return 6;
        }
        return 0;
    }
    
    //CONVIERTE LA LETRA DE LA NACION
    private int convertirLetra(char letra)
    {
         if('A' == letra)
            return 10;
        if('B' == letra)
            return 11;
         if('C' == letra)
            return 12;           
        if('D' == letra)
            return 13;
        if('E' == letra)
            return 14;
        if('F' == letra)
            return 15;
        if('G' == letra)
            return 16;
        if('H' == letra)
            return 17;
        if('I' == letra)
            return 18;
        if('J' == letra)
            return 19;
        if('K' == letra)
            return 20;
        if('L' == letra)
            return 21;
        if('M' == letra)
            return 22;
        if('N' == letra)
            return 23;
        if('O' == letra)
            return 24;
        if('P' == letra)
            return 25;
        if('Q' == letra)
            return 26;
        if('R' == letra)
            return 27;
        if('S' == letra)
            return 28;
        if('T' == letra)
            return 29;
        if('U' == letra)
            return 30;
        if('V' == letra)
            return 31;
        if('W' == letra)
            return 32;
        if('X' == letra)
            return 33;
        if('Y' == letra)
            return 34;
        if('Z' == letra)
            return 35;
        else
            return 0;
    }
    
    //SUMATORIO PARA HALLAR EL DIGITO DE CONTROL
    public int sumaNum(ArrayList<Integer> numList)
    {
        int suma = 0;
        int resto;
        int digitoControl;
        //SUMATORIO DE LOS VALORES DE LA PRIMERA PARTE Y LA SEGUNDA Y MULTIPLICADO POR EL NUMEROi POR MOD(i)
        for(int i = 0; i< numList.size();i++)
        {
            suma = suma + (mod(i)*numList.get(i));
        }
        
        resto = suma % 11;
        digitoControl = 11 - resto;
        
        if(digitoControl == 10)
        {
            digitoControl = 1;
        }
        if(digitoControl == 11)
        {
            digitoControl = 0;
        }

        return digitoControl;
    }
}
