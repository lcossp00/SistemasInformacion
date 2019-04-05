
package modelo;

import controlador.Objeto;
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
   
   //LISTAS
   static ArrayList<String> cuenta = new ArrayList<String>();
    static ArrayList<String> nacion = new ArrayList<String>();
   static ArrayList<Objeto> erroneos = new ArrayList<Objeto>();
   static InputStream inputExcel = null;

   //LLAMADAS
   static Objeto obj = new Objeto();
   
    //HACE UNA PRIMERA APERTURA DEL ARCHIVO EXCEL Y DEVUELVE UNA LISTA CON LOS DNI PARA BUSCAR LOS ERRONEOS
    public ArrayList<String> devuelveCuenta (File fileExcel)
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
                        if(c == 10 && r > 0)
                        {
                            stringValorCelda = valorCelda.getCell(c) == null?"":(valorCelda.getCell(c).getCellType() == CellType.STRING)?valorCelda.getCell(c).getStringCellValue():(valorCelda.getCell(c).getCellType() == CellType.NUMERIC)?"" + valorCelda.getCell(c).getNumericCellValue():(valorCelda.getCell(c).getCellType() == CellType.BOOLEAN)?"" + valorCelda.getCell(c).getBooleanCellValue():(valorCelda.getCell(c).getCellType() == CellType.BLANK)?"BLANK":(valorCelda.getCell(c).getCellType() == CellType.FORMULA)?"FORMULA":(valorCelda.getCell(c).getCellType() == CellType.ERROR)?"ERROR":"";
                            cuenta.add(stringValorCelda);
                        }
                    }
                }
            }
            
            return cuenta;
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
        return cuenta;
    }
    
    public ArrayList<String> devuelveNacion(File fileExcel)
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
                        if(c == 11 && r > 0)
                        {
                            stringValorCelda = valorCelda.getCell(c) == null?"":(valorCelda.getCell(c).getCellType() == CellType.STRING)?valorCelda.getCell(c).getStringCellValue():(valorCelda.getCell(c).getCellType() == CellType.NUMERIC)?"" + valorCelda.getCell(c).getNumericCellValue():(valorCelda.getCell(c).getCellType() == CellType.BOOLEAN)?"" + valorCelda.getCell(c).getBooleanCellValue():(valorCelda.getCell(c).getCellType() == CellType.BLANK)?"BLANK":(valorCelda.getCell(c).getCellType() == CellType.FORMULA)?"FORMULA":(valorCelda.getCell(c).getCellType() == CellType.ERROR)?"ERROR":"";
                            nacion.add(stringValorCelda);
                        }
                    }
                }
            }
            
            return nacion;
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
        return nacion;
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
