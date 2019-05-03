package controlador;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import org.apache.poi.ss.usermodel.DateUtil;


public class NominaSinProrrata
{
    public void crearNominasSinProrrata(ArrayList<Objeto> nominas) throws ParseException
    {
        ArrayList<Objeto> nominasSinProrrateo = new ArrayList<Objeto>();
        ArrayList<Objeto> nominasProrrateo = new ArrayList<Objeto>();
        ArrayList<Objeto> nominasValidas = new ArrayList<Objeto>();
        
        //COMPROBAMOS QUE LA FECHA DE ENTRADA ES VALIDA
        for(Objeto n : nominas)
        {
            Date javaDate= DateUtil.getJavaDate((double)n.getFechaAlta());
            String añoEntrada = javaDate.toString();
            String añoVerdadero = añoEntrada.substring(añoEntrada.length()-4,añoEntrada.length());
            String mesVerdadero = new SimpleDateFormat("MM/dd/yyyy").format(javaDate).substring(0,2);
    
            n.setAnio(Integer.parseInt(añoVerdadero));
            n.setMes(Integer.parseInt(mesVerdadero));
            
            if((n.getMes() > Integer.parseInt(ValoresEstaticos.mes)) && (Integer.parseInt(añoVerdadero) >= Integer.parseInt(ValoresEstaticos.año)))
            {
                
                
            }
            else
            {
                nominasValidas.add(n);
            }
        }
        
        //LAS DIVIDIMOS EN CON PRORRATEO Y SIN PRORRATEO
        for(Objeto n : nominasValidas)
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
            
            for(ObjetoCategorias cat : ValoresEstaticos.categorias)
            {
                if(categoria.equals(cat.getCategoria()))
                {
                    salarioBase = cat.getSalarioBase();
                    complemento = cat.getComplementos(); 
                }
            }
            
            int trienios = calculaNumTrienos(n.getAnio(),n.getMes());
            double importeBrutoTri = 0;
            String strienios = trienios + ".0";
            for(ObjetoTrineos tri : ValoresEstaticos.trineos)
            {
                  
                if(tri.getNumTrineos().equals(strienios))
                {
                    
                   importeBrutoTri = Double.parseDouble(tri.getImporteBruto());
                   
                }
            }
            
            
            
            double brutoMes = Double.parseDouble(salarioBase)/14 + Double.parseDouble(complemento)/14 + importeBrutoTri/14;
            double brutoAnual = Double.parseDouble(salarioBase) + Double.parseDouble(complemento)+ importeBrutoTri;
            
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
            
            System.out.println(liquidoMensual);
            double costeReal = calcularEmpresario(prorrateo) + brutoMes;
              
        }
        
        for(Objeto n: nominasProrrateo)
        {
            String categoria = n.getCategoria();
            String salarioBase = "";
            String complemento = "";
            String antiguedad = n.getFechaAlta().toString();
            
            for(ObjetoCategorias cat : ValoresEstaticos.categorias)
            {
                if(categoria.equals(cat.getCategoria()))
                {
                    salarioBase = cat.getSalarioBase();
                    complemento = cat.getComplementos(); 
                }
            }
            int trienios = calculaNumTrienos(n.getAnio(),n.getMes());
            double importeBrutoTri = 0;
            String strienios = trienios + ".0";
            for(ObjetoTrineos tri : ValoresEstaticos.trineos)
            {
                  
                if(tri.getNumTrineos().equals(strienios))
                {
                    
                   importeBrutoTri = Double.parseDouble(tri.getImporteBruto());
                   
                }
            }
            double prorrateo = calcularProrrateo(salarioBase,complemento,trienios); 
            double brutoMes = Double.parseDouble(salarioBase)/14 + Double.parseDouble(complemento)/14 + importeBrutoTri/14 +  prorrateo;
            double brutoAnual = Double.parseDouble(salarioBase) + Double.parseDouble(complemento)+ importeBrutoTri;
            
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
    public int calculaNumTrienos(int año,int mes)
    {
        int numeroTrienios = 0;
        int añoActual = Integer.parseInt(ValoresEstaticos.año);
        int mesActual = Integer.parseInt(ValoresEstaticos.mes);
        int añoTrabajador = año;
        int mesTrabajador = mes;
        
        int resta = añoActual - añoTrabajador;
        int resta2 = resta / 3;
        
        if(resta % 3 == 0)
        {

            if(mesActual>mesTrabajador)
            {
                numeroTrienios = resta2;
            }else
            {
                numeroTrienios = resta2 - 1;
            }
           
        }
        else
        {
            numeroTrienios = resta2;
        }
        
        return numeroTrienios;
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
