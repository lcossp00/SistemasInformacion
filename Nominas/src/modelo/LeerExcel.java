
package modelo;

import controlador.Objeto;
import controlador.ObjetoHoja2;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class LeerExcel 
{
   //XSS
   static XSSFRow valorCelda;
   static XSSFCell celda;
   
   //VARIABLES
   static int filas;
   static int colum;
   static int id;
   static int count = 0;
   static String err;
   static String stringValorCelda;
   static String pos;
   
   //LISTAS
   static ArrayList<Objeto> erroneos = new ArrayList<Objeto>();
   static InputStream inputExcel = null;
   static ArrayList<Objeto> iban = new ArrayList<Objeto>();
   static ArrayList<String> listDNI = new ArrayList<String>();
   static ArrayList<Objeto> salarios = new ArrayList<Objeto>();
   
   //Practica 4
    static ArrayList<String> categoria = new ArrayList<String>();
    static ArrayList<String> salarioBase = new ArrayList<String>();
    static ArrayList<String> complementos = new ArrayList<String>();
    
    static ArrayList<String> brutoAnual = new ArrayList<String>();
    static ArrayList<String> retencion = new ArrayList<String>();
    
    static ArrayList<String> cuotas = new ArrayList<String>();
    static ArrayList<String> nombreCuotas = new ArrayList<String>();
    
    static ArrayList<String> numeroTrienios = new ArrayList<String>();
    static ArrayList<String> importeBruto = new ArrayList<String>();
   


   //LLAMADAS
   static Objeto obj = new Objeto();
   
    //GUARDA EN UN OBJETO TODOS LOS DATOS NECESARIOS
    public ArrayList<Objeto> devuelveTodo (File fileExcel)
    {  
        try 
        {
            inputExcel = new FileInputStream(fileExcel);
            XSSFWorkbook parametro = new XSSFWorkbook(inputExcel);
            XSSFSheet leerFilCoum = parametro.getSheetAt(0);                       
            filas = leerFilCoum.getLastRowNum();
            colum = 0;  
            
            for (int r = 1; r < filas +1; r++) 
            {
                obj = new Objeto();
                valorCelda = leerFilCoum.getRow(r);

                if (valorCelda == null)
                {
                    //SI NO EXISTE VALOR EN ESA CELDA NO SE RECORRERA
                    break;
                }
                else
                {
                    for (int c = 0; c < (colum = valorCelda.getLastCellNum()); c++) 
                    {
                        
                        stringValorCelda = valorCelda.getCell(c) == null?"":(valorCelda.getCell(c).getCellType() == CellType.STRING)?valorCelda.getCell(c).getStringCellValue():(valorCelda.getCell(c).getCellType() == CellType.NUMERIC)?"" + valorCelda.getCell(c).getNumericCellValue():(valorCelda.getCell(c).getCellType() == CellType.BOOLEAN)?"" + valorCelda.getCell(c).getBooleanCellValue():(valorCelda.getCell(c).getCellType() == CellType.BLANK)?"BLANK":(valorCelda.getCell(c).getCellType() == CellType.FORMULA)?"FORMULA":(valorCelda.getCell(c).getCellType() == CellType.ERROR)?"ERROR":"";
                        pos = r + "";
                        obj.setPos(pos);
                        switch(c){
                            case 0:
                                obj.setNombreTrabajador(stringValorCelda);
                                break;
                            case 1:
                                obj.setPrimerApell(stringValorCelda);
                                break;
                            case 2:
                                obj.setSegApell(stringValorCelda);
                                break;
                            case 6:
                                obj.setNombreEmpre(stringValorCelda);
                                break;
                            case 9:
                                obj.setCategoria(stringValorCelda);
                                break;
                            case 10:
                                obj.setCuenta(stringValorCelda);
                                break;
                            case 11:
                                obj.setNacion(stringValorCelda);
                                break;
                        }
                       
                    }
                }
                iban.add(obj);
            }
            
            return iban;
        } 
        catch (FileNotFoundException notFound) 
        {
            System.out.println("El fichero no fue encontrado: " + notFound);
        } 
        catch (IOException process) 
        {
            System.out.println("Error al procesarlo: " + process);
        } 
        finally 
        {
            try 
            {
                inputExcel.close();
            } 
            catch (IOException close) 
            {
                System.out.println("Error al cerrar el fichero: " + close);
            }
        }
        return iban;
    }
    
    
    //DEVUELVE EL CONTENIDO DE LA HOJA DOS DE EXCEL Practica 4
    public void devuelveTodoHoja2 (File fileExcel)
    {  
        try 
        {
            inputExcel = new FileInputStream(fileExcel);
            XSSFWorkbook parametro = new XSSFWorkbook(inputExcel);
            XSSFSheet leerFilCoum = parametro.getSheetAt(1);                       
            filas = leerFilCoum.getLastRowNum();
            colum = 0; 
            ObjetoHoja2 obj2;
            
            //LEER SALARIO BASE Y COMPLEMENTOS
            for (int r = 1; r < 14; r++) 
            {
                obj2 = new ObjetoHoja2();
                valorCelda = leerFilCoum.getRow(r);
               
                if (valorCelda == null)
                {
                    //SI NO EXISTE VALOR EN ESA CELDA NO SE RECORRERA
                    break;
                }
                else
                {
                    for (int c = 0; c <= 2; c++) 
                    {
                         
                        stringValorCelda = valorCelda.getCell(c) == null?"":(valorCelda.getCell(c).getCellType() == CellType.STRING)?valorCelda.getCell(c).getStringCellValue():(valorCelda.getCell(c).getCellType() == CellType.NUMERIC)?"" + valorCelda.getCell(c).getNumericCellValue():(valorCelda.getCell(c).getCellType() == CellType.BOOLEAN)?"" + valorCelda.getCell(c).getBooleanCellValue():(valorCelda.getCell(c).getCellType() == CellType.BLANK)?"BLANK":(valorCelda.getCell(c).getCellType() == CellType.FORMULA)?"FORMULA":(valorCelda.getCell(c).getCellType() == CellType.ERROR)?"ERROR":"";
                        pos = r + "";
                        obj.setPos(pos);
                        switch(c)
                        {
                            case 0:
                                categoria.add(stringValorCelda);
                                //Deduzco que la siquiente linea es erronea, asi como las de los otros case
                                //obj2.setCategoria(stringValorCelda);
                              break;
                                
                            case 1:
                                salarioBase.add(stringValorCelda);
                               // obj2.setsalarioBase(stringValorCelda);
                                break;
                            case 2:
                                complementos.add(stringValorCelda);
                              //  obj2.setcomplementos(stringValorCelda);
                                break;
                              
                        }
                       
                    }
                }
                salarios.add(obj);
           
            }
             for(int y=0; y<categoria.size(); y++){
              System.out.print(categoria.get(y)+" ");
              }
            System.out.print("\n");
              for(int y=0; y<salarioBase.size(); y++){
              System.out.print(salarioBase.get(y)+" ");
              }
            System.out.print("\n");  
            for(int y=0; y<complementos.size(); y++){
              System.out.print(complementos.get(y)+" ");
              }
            System.out.print("\n");
            //LEER CUOTAS
              for (int p = 17; p <= 24; p++) 
                {
                    valorCelda = leerFilCoum.getRow(p);
                    if (valorCelda == null)
                     {
                        //SI NO EXISTE VALOR EN ESA CELDA NO SE RECORRERA
                        break;
                    }else{
                        for (int c = 0; c <= 1; c++)
                        {
                            stringValorCelda = valorCelda.getCell(c) == null?"":(valorCelda.getCell(c).getCellType() == CellType.STRING)?valorCelda.getCell(c).getStringCellValue():(valorCelda.getCell(c).getCellType() == CellType.NUMERIC)?"" + valorCelda.getCell(c).getNumericCellValue():(valorCelda.getCell(c).getCellType() == CellType.BOOLEAN)?"" + valorCelda.getCell(c).getBooleanCellValue():(valorCelda.getCell(c).getCellType() == CellType.BLANK)?"BLANK":(valorCelda.getCell(c).getCellType() == CellType.FORMULA)?"FORMULA":(valorCelda.getCell(c).getCellType() == CellType.ERROR)?"ERROR":"";
                            pos = p + "";
                            obj.setPos(pos);
                            switch(c)
                            {
                                case 0:
                                    nombreCuotas.add(stringValorCelda);
                                    
                                    break;
                                case 1:
                                    cuotas.add(stringValorCelda);
                                   
                                    break;
                                 
                            }
                        }
                    }
                }
              for(int y=0; y<8; y++){
              System.out.print(nombreCuotas.get(y)+" ");
              }
              System.out.print("\n");
              //LEER NUMERO DE TRIENIO E IMPORTE BRUTO
            for (int p = 18; p <= 35 ; p++) 
                {
                    valorCelda = leerFilCoum.getRow(p);
                    if (valorCelda == null)
                     {
                        //SI NO EXISTE VALOR EN ESA CELDA NO SE RECORRERA
                        break;
                    }else{
                        for (int c = 2; c <= 3; c++)
                        {
                            stringValorCelda = valorCelda.getCell(c) == null?"":(valorCelda.getCell(c).getCellType() == CellType.STRING)?valorCelda.getCell(c).getStringCellValue():(valorCelda.getCell(c).getCellType() == CellType.NUMERIC)?"" + valorCelda.getCell(c).getNumericCellValue():(valorCelda.getCell(c).getCellType() == CellType.BOOLEAN)?"" + valorCelda.getCell(c).getBooleanCellValue():(valorCelda.getCell(c).getCellType() == CellType.BLANK)?"BLANK":(valorCelda.getCell(c).getCellType() == CellType.FORMULA)?"FORMULA":(valorCelda.getCell(c).getCellType() == CellType.ERROR)?"ERROR":"";
                            pos = p + "";
                            obj.setPos(pos);
                            switch(c)
                            {
                                case 2:
                                    numeroTrienios.add(stringValorCelda);
                                    
                                    break;
                                case 3:
                                    importeBruto.add(stringValorCelda);
                                   
                                    break;
                                 
                            }
                        }
                    }
                }
            for(int y=0; y<numeroTrienios.size(); y++){
              System.out.print(numeroTrienios.get(y)+" ");
              }
            System.out.print("\n");
            for(int y=0; y<importeBruto.size(); y++){
              System.out.print(importeBruto.get(y)+"");
              }
            System.out.print("\n");

            //LEER BRUTO ANUAL Y RETENCION
            for (int p = 1; p <= 49 ; p++) 
                {
                    valorCelda = leerFilCoum.getRow(p);
                    if (valorCelda == null)
                     {
                        //SI NO EXISTE VALOR EN ESA CELDA NO SE RECORRERA
                        break;
                    }else{
                        for (int c = 5; c <= 6; c++)
                        {
                            stringValorCelda = valorCelda.getCell(c) == null?"":(valorCelda.getCell(c).getCellType() == CellType.STRING)?valorCelda.getCell(c).getStringCellValue():(valorCelda.getCell(c).getCellType() == CellType.NUMERIC)?"" + valorCelda.getCell(c).getNumericCellValue():(valorCelda.getCell(c).getCellType() == CellType.BOOLEAN)?"" + valorCelda.getCell(c).getBooleanCellValue():(valorCelda.getCell(c).getCellType() == CellType.BLANK)?"BLANK":(valorCelda.getCell(c).getCellType() == CellType.FORMULA)?"FORMULA":(valorCelda.getCell(c).getCellType() == CellType.ERROR)?"ERROR":"";
                            pos = p + "";
                            obj.setPos(pos);
                            switch(c)
                            {
                                case 5:
                                    brutoAnual.add(stringValorCelda);
                                    
                                    break;
                                case 6:
                                    retencion.add(stringValorCelda);
                                   
                                    break;
                                 
                            }
                        }
                    }
                }
            System.out.print("\n"+"bruto anual"+"\n");
            for(int y=0; y<brutoAnual.size(); y++){
              System.out.print(brutoAnual.get(y)+" ");
              }
            System.out.print("\n"+"Retencion"+"\n");
            for(int y=0; y<retencion.size(); y++){
              System.out.print(retencion.get(y)+" ");
              }
            System.out.print("\n");
        } 
        
        catch (FileNotFoundException notFound) 
        {
            System.out.println("El fichero no fue encontrado: " + notFound);
        } 
        catch (IOException process) 
        {
            System.out.println("Error al procesarlo: " + process);
        } 
        finally 
        {
            try 
            {
                inputExcel.close();
            } 
            catch (IOException close) 
            {
                System.out.println("Error al cerrar el fichero: " + close);
            }
        }
       
    }

    //ESCRIBE EN EL EXCEL EL EMAIL Y EL IBAN
    public void escribirIbanEmail(File fileExcel,ArrayList<Objeto> emailCompleto)
    {
     try 
        {
            inputExcel = new FileInputStream(fileExcel);
            XSSFWorkbook parametro = new XSSFWorkbook(inputExcel);
            XSSFSheet leerFilCoum = parametro.getSheetAt(0);                       
            filas = leerFilCoum.getLastRowNum();
            colum = 0;            
            int count = 0;
            int r;


                for(Objeto obj : emailCompleto)
                {
                    r = Integer.parseInt(obj.getPos());
                    valorCelda = leerFilCoum.getRow(r);
                    err = obj.getEmail();
                    
                    
                    if (valorCelda == null)
                    {
                        //SI NO EXISTE VALOR EN ESA CELDA NO SE RECORRERA
                        break;
                    }
                    else
                    {
                        for(int c = 0; c <= (colum = valorCelda.getLastCellNum()); c++) 
                        {
                            //SE COGE SOLO LA 3 COLUMNA DONDE ESTA EL DNI, PARA PROCESAR SOLO ESOS DATOS
                            if(c == 5 && r > 0)
                            {
                               stringValorCelda = valorCelda.getCell(c) == null?"":(valorCelda.getCell(c).getCellType() == CellType.STRING)?valorCelda.getCell(c).getStringCellValue():(valorCelda.getCell(c).getCellType() == CellType.NUMERIC)?"" + valorCelda.getCell(c).getNumericCellValue():(valorCelda.getCell(c).getCellType() == CellType.BOOLEAN)?"" + valorCelda.getCell(c).getBooleanCellValue():(valorCelda.getCell(c).getCellType() == CellType.BLANK)?"BLANK":(valorCelda.getCell(c).getCellType() == CellType.FORMULA)?"FORMULA":(valorCelda.getCell(c).getCellType() == CellType.ERROR)?"ERROR":"";

                               leerFilCoum.getRow(r).createCell(c).setCellValue(err);

                               try (FileOutputStream ficheroCambiado = new FileOutputStream(new File("resources/SistemasInformacionII.xlsx"))) {
                                                parametro.write(ficheroCambiado);
                               }catch(Exception e)
                               {}

                            }
                            if(c == 10 && r > 0)
                            {
                               stringValorCelda = valorCelda.getCell(c) == null?"":(valorCelda.getCell(c).getCellType() == CellType.STRING)?valorCelda.getCell(c).getStringCellValue():(valorCelda.getCell(c).getCellType() == CellType.NUMERIC)?"" + valorCelda.getCell(c).getNumericCellValue():(valorCelda.getCell(c).getCellType() == CellType.BOOLEAN)?"" + valorCelda.getCell(c).getBooleanCellValue():(valorCelda.getCell(c).getCellType() == CellType.BLANK)?"BLANK":(valorCelda.getCell(c).getCellType() == CellType.FORMULA)?"FORMULA":(valorCelda.getCell(c).getCellType() == CellType.ERROR)?"ERROR":"";

                               leerFilCoum.getRow(r).createCell(c).setCellValue(obj.getCuenta());

                               try (FileOutputStream ficheroCambiado = new FileOutputStream(new File("resources/SistemasInformacionII.xlsx"))) {
                                                parametro.write(ficheroCambiado);
                               }catch(Exception e)
                               {} 
                            }
                            if(c == 12 && r > 0)
                            {
                               stringValorCelda = valorCelda.getCell(c) == null?"":(valorCelda.getCell(c).getCellType() == CellType.STRING)?valorCelda.getCell(c).getStringCellValue():(valorCelda.getCell(c).getCellType() == CellType.NUMERIC)?"" + valorCelda.getCell(c).getNumericCellValue():(valorCelda.getCell(c).getCellType() == CellType.BOOLEAN)?"" + valorCelda.getCell(c).getBooleanCellValue():(valorCelda.getCell(c).getCellType() == CellType.BLANK)?"BLANK":(valorCelda.getCell(c).getCellType() == CellType.FORMULA)?"FORMULA":(valorCelda.getCell(c).getCellType() == CellType.ERROR)?"ERROR":"";
                               
                               leerFilCoum.getRow(r).createCell(c).setCellValue(obj.getIban());

                               try (FileOutputStream ficheroCambiado = new FileOutputStream(new File("resources/SistemasInformacionII.xlsx"))) {
                                                parametro.write(ficheroCambiado);
                               }catch(Exception e)
                               {} 
                            }
                        }
                    }
                    count++;
                }
            
        } 
        catch (FileNotFoundException notFound) 
        {
            System.out.println("El fichero no fue encontrado: " + notFound);
        } 
        catch (IOException process) 
        {
            System.out.println("Error al procesarlo: " + process);
        } 
        finally 
        {
            try 
            {
                inputExcel.close();
            } 
            catch (IOException close) 
            {
                System.out.println("Error al cerrar el fichero: " + close);
            }
        }
    }
    
    //HACE UNA PRIMEARA APERTURA DEL ARCHIVO EXCEL Y DEVUELTE UN ARRAYLIST<OBJETO>(LISTA DE TRABAJADORES)
    public ArrayList<String> primeraLectura(File fileExcel)
    {  
        try 
        {
            inputExcel = new FileInputStream(fileExcel);
            XSSFWorkbook parametro = new XSSFWorkbook(inputExcel);
            XSSFSheet leerFilCoum = parametro.getSheetAt(0);                       
            filas = leerFilCoum.getLastRowNum();
            colum = 0;            


            for (int r = 0; r < filas; r++) 
            {
                valorCelda = leerFilCoum.getRow(r);

                if (valorCelda == null)
                {
                    //SI NO EXISTE VALOR EN ESA CELDA NO SE RECORRERA
                    break;
                }
                else
                {
                    for (int c = 0; c < (colum = valorCelda.getLastCellNum()); c++) 
                    {
                        //SE COGE SOLO LA 3 COLUMNA DONDE ESTA EL DNI, PARA PROCESAR SOLO ESOS DATOS
                        if(c == 3 && r > 0)
                        {
                            stringValorCelda = valorCelda.getCell(c) == null?"":(valorCelda.getCell(c).getCellType() == CellType.STRING)?valorCelda.getCell(c).getStringCellValue():(valorCelda.getCell(c).getCellType() == CellType.NUMERIC)?"" + valorCelda.getCell(c).getNumericCellValue():(valorCelda.getCell(c).getCellType() == CellType.BOOLEAN)?"" + valorCelda.getCell(c).getBooleanCellValue():(valorCelda.getCell(c).getCellType() == CellType.BLANK)?"BLANK":(valorCelda.getCell(c).getCellType() == CellType.FORMULA)?"FORMULA":(valorCelda.getCell(c).getCellType() == CellType.ERROR)?"ERROR":"";
                            listDNI.add(stringValorCelda);
                        }
                    }
                }
            }
            
            return listDNI;
        } 
        catch (FileNotFoundException notFound) 
        {
            System.out.println("El fichero no fue encontrado: " + notFound);
        } 
        catch (IOException process) 
        {
            System.out.println("Error al procesarlo: " + process);
        } 
        finally 
        {
            try 
            {
                inputExcel.close();
            } 
            catch (IOException close) 
            {
                System.out.println("Error al cerrar el fichero: " + close);
            }
        }
        return listDNI;
    }
    
    //HACE UNA SEGUNDA APERTURA DEL ARCHIVO EXCEL Y DEVUELTE UN ARRAYLIST<OBJETO>(LISTA DE TRABAJADORES)
    public ArrayList<Objeto> segundaLectura(File fileExcel,ArrayList<String> listErroneos,ArrayList<Integer> idPos)
    {
     try 
        {
            inputExcel = new FileInputStream(fileExcel);
            XSSFWorkbook parametro = new XSSFWorkbook(inputExcel);
            XSSFSheet leerFilCoum = parametro.getSheetAt(0);                       
            colum = 0;
            


            for (int r:idPos) 
            {
                id = r + 1;
                valorCelda = leerFilCoum.getRow(id);
                obj.setPos("" + id);
                

                if (valorCelda == null)
                {
                    //SI NO EXISTE VALOR EN ESA CELDA NO SE RECORRERA
                    break;
                }
                else
                {
                    for (int c = 0; c < (colum = valorCelda.getLastCellNum()); c++) 
                    {
                        //SE COGE SOLO LA 3 COLUMNA DONDE ESTA EL DNI, PARA PROCESAR SOLO ESOS DATOS
                        if(r > 0)
                        {
                            stringValorCelda = valorCelda.getCell(c) == null?"":(valorCelda.getCell(c).getCellType() == CellType.STRING)?valorCelda.getCell(c).getStringCellValue():(valorCelda.getCell(c).getCellType() == CellType.NUMERIC)?"" + valorCelda.getCell(c).getNumericCellValue():(valorCelda.getCell(c).getCellType() == CellType.BOOLEAN)?"" + valorCelda.getCell(c).getBooleanCellValue():(valorCelda.getCell(c).getCellType() == CellType.BLANK)?"BLANK":(valorCelda.getCell(c).getCellType() == CellType.FORMULA)?"FORMULA":(valorCelda.getCell(c).getCellType() == CellType.ERROR)?"ERROR":"";
                            
                            if(c == 0)
                            {
                               obj.setNombreTrabajador(stringValorCelda);
                            }
                            if(c == 1)
                            {
                                obj.setPrimerApell(stringValorCelda);
                            }
                            if(c == 2)
                            {
                                obj.setSegApell(stringValorCelda);
                            }
                            if(c == 3)
                            {
                                if(count < listErroneos.size())
                                {
                                    err = listErroneos.get(count);
                                    
                                    if(valorCelda.getCell(c) != null)
                                    {
                                         valorCelda.getCell(c).setCellValue(err);
                                        try (FileOutputStream ficheroCambiado = new FileOutputStream(new File("resources/SistemasInformacionII.xlsx"))) {
                                            parametro.write(ficheroCambiado);
                                        }       
                                    }
                                    
                                    
                                }
                            }
                            if(c == 6)
                            {
                               obj.setNombreEmpre(stringValorCelda);
                            }
                            if(c == 9)
                            {
                                obj.setCategoria(stringValorCelda);
                            }
                            
                                
                        }
                    }
                }
                count++;
                erroneos.add(obj);
            }
            
            return erroneos;
        } 
        catch (FileNotFoundException notFound) 
        {
            System.out.println("El fichero no fue encontrado: " + notFound);
        } 
        catch (IOException process) 
        {
            System.out.println("Error al procesarlo: " + process);
        } 
        finally 
        {
            try 
            {
                inputExcel.close();
            } 
            catch (IOException close) 
            {
                System.out.println("Error al cerrar el fichero: " + close);
            }
        }
        return erroneos;
    }
    

}
