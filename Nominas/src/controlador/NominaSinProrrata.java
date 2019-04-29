package controlador;

import java.util.ArrayList;


public class NominaSinProrrata
{
    public void crearNominasSinProrrata(ArrayList<Objeto> nominas)
    {
        ArrayList<Objeto> nominasSinProrrateo = new ArrayList<Objeto>();
        
        for(Objeto n : nominas)
        {
            
            if(n.getProrrata().equals("NO"))
            {
                nominasSinProrrateo.add(n);
                
            }
        }
        
        for(Objeto n: nominasSinProrrateo)
        {
            String categoria = n.getCategoria();
            String salarioBase = "";
            String complemento = "";
            String antiguedad = n.getFechaAlta();
            
            for(ObjetoCategorias cat : ValoresEstaticos.categorias)
            {
                if(categoria.equals(cat.getCategoria()))
                {
                    salarioBase = cat.getSalarioBase();
                    complemento = cat.getComplementos(); 
                }
            }
            
            int trienios = calculaNumTrienos(antiguedad);
            double brutoMes = Double.parseDouble(salarioBase)/14 + Double.parseDouble(complemento)/14 + trienios/14;
            double brutoAnual = Double.parseDouble(salarioBase) + Double.parseDouble(complemento)+ trienios;
            
            String IRPF = "";
            
            for(ObjetoBrutoAnual cat : ValoresEstaticos.brutoAnual)
            {
                if(brutoAnual < (Double.parseDouble(cat.getBrutoAnual())))
                { 
                    IRPF = cat.getRetencion(); 
                    break;
                }
            }
            
        }
    }
    
    public int calculaNumTrienos(String antiguedad)
    {
        return 0;
    }
}
