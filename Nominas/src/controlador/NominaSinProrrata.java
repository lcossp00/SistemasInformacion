package controlador;

import java.util.ArrayList;


public class NominaSinProrrata
{
    public void crearNominasSinProrrata(ArrayList<Objeto> nominas)
    {
        ArrayList<Objeto> nominasSinProrrateo = new ArrayList<Objeto>();
        ArrayList<Objeto> nominasProrrateo = new ArrayList<Objeto>();
        
        
        for(Objeto n : nominas)
        {
            
            if(n.getProrrata().equals("NO"))
            {
                nominasSinProrrateo.add(n);
                
            }
            else
            {
                nominasProrrateo.add(n);
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
            double prorrateo = calcularProrrateo(salarioBase,complemento,trienios);
            double ss = (prorrateo * Double.parseDouble(ValoresEstaticos.objcuotas.getObreroGeneralTrab()))/100;
            double desempleo = (prorrateo * Double.parseDouble(ValoresEstaticos.objcuotas.getDesempleoTrab()))/100;
            double formacion = (prorrateo * Double.parseDouble(ValoresEstaticos.objcuotas.getFormacionTrab()))/100;
            double liquidoMensual = brutoMes - (ss + desempleo + formacion + Double.parseDouble(IRPF));
            
            double costeReal = calcularEmpresario(prorrateo) + brutoMes;
              
        }
        
        for(Objeto n: nominasProrrateo)
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
            double prorrateo = calcularProrrateo(salarioBase,complemento,trienios); 
            double brutoMes = Double.parseDouble(salarioBase)/14 + Double.parseDouble(complemento)/14 + trienios/14 +  prorrateo;
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
            
            double irpf = (brutoMes * Double.parseDouble(IRPF))/ 100;
            double ss = (prorrateo * Double.parseDouble(ValoresEstaticos.objcuotas.getObreroGeneralTrab()))/100;
            double desempleo = (prorrateo * Double.parseDouble(ValoresEstaticos.objcuotas.getDesempleoTrab()))/100;
            double formacion = (prorrateo * Double.parseDouble(ValoresEstaticos.objcuotas.getFormacionTrab()))/100;
            double liquidoMensual = brutoMes - (ss + desempleo + formacion + irpf);
            
            double costeReal = calcularEmpresario(prorrateo) + brutoMes;
            
            
              
        }
    }
    
    public double calcularProrrateo(String salario, String complemento, int trienio)
    {
        double salarioD = Double.parseDouble(salario)/14;
        double complementoD = Double.parseDouble(complemento)/14;
        double trienioD = trienio;
        
        double prorrateo = salarioD/6 + complementoD/6 + trienioD/6;
        
        return prorrateo;
    }
    public int calculaNumTrienos(String antiguedad)
    {
        
        return 0;
    }
    
    public double extra(Double brutoMes, Double irpf, String antiguedad)
    {
        //if(antiguedad)
        //double total = brutoMes - irpf;
        
       // return total;
        return 0.0;
    }
    public double calcularEmpresario(Double prorrateo)
    {
        double ssEmpre = (prorrateo * Double.parseDouble(ValoresEstaticos.objcuotas.getObreroGeneralTrab()))/100;
        double desempleoEmpre = (prorrateo / Double.parseDouble(ValoresEstaticos.objcuotas.getDesempleoEmpre()))/100;
        double formacionEmpre = (prorrateo / Double.parseDouble(ValoresEstaticos.objcuotas.getFormacionEmpre()))/100;
        double accidentesEmpre = (prorrateo / Double.parseDouble(ValoresEstaticos.objcuotas.getAccidentes()))/100;
        
        double empresario = ssEmpre + desempleoEmpre + formacionEmpre + accidentesEmpre;
        
        return empresario;
    }
}
