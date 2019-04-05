/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controlador;

import java.math.BigInteger;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class GeneradorIBAN 
{
    static ArrayList<String> ibanCompleto = new ArrayList<String>();
    static ArrayList<String> nacionInt = new ArrayList<String>();
    static ArrayList<BigInteger> ibanInt = new ArrayList<BigInteger>();
    static ArrayList<Integer> restoMod97 = new ArrayList<Integer>();
    static ArrayList<String> completo = new ArrayList<String>();
    static ArrayList<String> comprobar = new ArrayList<String>();
    
    
    public ArrayList<Objeto> geneararIban(ArrayList<Objeto> cuentasBancarias)
    {

        return cuentasBancarias;
    }
    
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
}
