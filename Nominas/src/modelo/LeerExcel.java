
package modelo;

import controlador.Objeto;
import controlador.ObjetoBrutoAnual;
import controlador.ObjetoCategorias;
import controlador.ObjetoCuotas;
import controlador.ObjetoTrineos;
import controlador.ValoresEstaticos;
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
                        
                        stringValorCelda = valorCelda.getCell(c) == null?"":
                                (valorCelda.getCell(c).getCellType() == CellType.STRING)?valorCelda.getCell(c).getStringCellValue():
                                (valorCelda.getCell(c).getCellType() == CellType.NUMERIC)?"" + valorCelda.getCell(c).getNumericCellValue():
                                (valorCelda.getCell(c).getCellType() == CellType.BOOLEAN)?"" + valorCelda.getCell(c).getBooleanCellValue():
                                (valorCelda.getCell(c).getCellType() == CellType.BLANK)?"BLANK":
                                (valorCelda.getCell(c).getCellType() == CellType.FORMULA)?"FORMULA":
                                (valorCelda.getCell(c).getCellType() == CellType.ERROR)?"ERROR":"";
                        pos = r + "";
                        obj.setPos(pos);
                        switch(c)
                        {
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
                            case 4:
                                obj.setFechaAlta(Double.parseDouble(stringValorCelda));
                                break;
                            case 8:
                                obj.setProrrata(stringValorCelda);
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
            
            //RECORRE LAS FILAS 2-50 Y COGE TODO LOS VALORES BRUTO Y LAS RETENCIONES
            for (int r = 1; r < 50 ; r++) 
            {
                valorCelda = leerFilCoum.getRow(r);
                ObjetoBrutoAnual bruto = new ObjetoBrutoAnual();
                if (valorCelda == null)
                {
                    //SI NO EXISTE VALOR EN ESA CELDA NO SE RECORRERA
                    break;
                }
                else
                {
 
                    stringValorCelda = valorCelda.getCell(5) == null?"":(valorCelda.getCell(5).getCellType() == CellType.STRING)?valorCelda.getCell(5).getStringCellValue():(valorCelda.getCell(5).getCellType() == CellType.NUMERIC)?"" + valorCelda.getCell(5).getNumericCellValue():(valorCelda.getCell(5).getCellType() == CellType.BOOLEAN)?"" + valorCelda.getCell(5).getBooleanCellValue():(valorCelda.getCell(5).getCellType() == CellType.BLANK)?"BLANK":(valorCelda.getCell(5).getCellType() == CellType.FORMULA)?"FORMULA":(valorCelda.getCell(5).getCellType() == CellType.ERROR)?"ERROR":"";
                    bruto.setBrutoAnual(stringValorCelda);
                    stringValorCelda = valorCelda.getCell(6) == null?"":(valorCelda.getCell(6).getCellType() == CellType.STRING)?valorCelda.getCell(6).getStringCellValue():(valorCelda.getCell(6).getCellType() == CellType.NUMERIC)?"" + valorCelda.getCell(6).getNumericCellValue():(valorCelda.getCell(6).getCellType() == CellType.BOOLEAN)?"" + valorCelda.getCell(6).getBooleanCellValue():(valorCelda.getCell(6).getCellType() == CellType.BLANK)?"BLANK":(valorCelda.getCell(6).getCellType() == CellType.FORMULA)?"FORMULA":(valorCelda.getCell(6).getCellType() == CellType.ERROR)?"ERROR":"";   
                    bruto.setRetencion(stringValorCelda); 
                }
                ValoresEstaticos.brutoAnual.add(bruto);
            }
            
            //RECORREO LAS FILAS CATEGORIAS Y GUARDA LOS VALORES
            for (int r = 1; r < 15 ; r++) 
            {
                valorCelda = leerFilCoum.getRow(r);
                ObjetoCategorias categoria  = new ObjetoCategorias();
                if (valorCelda == null)
                {
                    //SI NO EXISTE VALOR EN ESA CELDA NO SE RECORRERA
                    break;
                }
                else
                {
 
                    stringValorCelda = valorCelda.getCell(0) == null?"":(valorCelda.getCell(0).getCellType() == CellType.STRING)?valorCelda.getCell(0).getStringCellValue():(valorCelda.getCell(0).getCellType() == CellType.NUMERIC)?"" + valorCelda.getCell(0).getNumericCellValue():(valorCelda.getCell(0).getCellType() == CellType.BOOLEAN)?"" + valorCelda.getCell(0).getBooleanCellValue():(valorCelda.getCell(0).getCellType() == CellType.BLANK)?"BLANK":(valorCelda.getCell(0).getCellType() == CellType.FORMULA)?"FORMULA":(valorCelda.getCell(0).getCellType() == CellType.ERROR)?"ERROR":"";
                    categoria.setCategoria(stringValorCelda);
                    stringValorCelda = valorCelda.getCell(1) == null?"":(valorCelda.getCell(1).getCellType() == CellType.STRING)?valorCelda.getCell(1).getStringCellValue():(valorCelda.getCell(1).getCellType() == CellType.NUMERIC)?"" + valorCelda.getCell(1).getNumericCellValue():(valorCelda.getCell(1).getCellType() == CellType.BOOLEAN)?"" + valorCelda.getCell(1).getBooleanCellValue():(valorCelda.getCell(1).getCellType() == CellType.BLANK)?"BLANK":(valorCelda.getCell(1).getCellType() == CellType.FORMULA)?"FORMULA":(valorCelda.getCell(1).getCellType() == CellType.ERROR)?"ERROR":"";   
                    categoria.setSalarioBase(stringValorCelda); 
                    stringValorCelda = valorCelda.getCell(2) == null?"":(valorCelda.getCell(2).getCellType() == CellType.STRING)?valorCelda.getCell(2).getStringCellValue():(valorCelda.getCell(2).getCellType() == CellType.NUMERIC)?"" + valorCelda.getCell(2).getNumericCellValue():(valorCelda.getCell(2).getCellType() == CellType.BOOLEAN)?"" + valorCelda.getCell(2).getBooleanCellValue():(valorCelda.getCell(2).getCellType() == CellType.BLANK)?"BLANK":(valorCelda.getCell(2).getCellType() == CellType.FORMULA)?"FORMULA":(valorCelda.getCell(2).getCellType() == CellType.ERROR)?"ERROR":"";   
                    categoria.setComplementos(stringValorCelda);
                }
                 ValoresEstaticos.categorias.add(categoria);
            }
            
            //Recorre Cuotas y guarda los valores
            for (int r = 17; r < 25 ; r++) 
            {
                valorCelda = leerFilCoum.getRow(r);
                if (valorCelda == null)
                {
                    //SI NO EXISTE VALOR EN ESA CELDA NO SE RECORRERA
                    break;
                }
                else
                {
                    
                    switch(r)
                    {
                            case 17:
                                stringValorCelda = valorCelda.getCell(1) == null?"":(valorCelda.getCell(1).getCellType() == CellType.STRING)?valorCelda.getCell(1).getStringCellValue():(valorCelda.getCell(1).getCellType() == CellType.NUMERIC)?"" + valorCelda.getCell(1).getNumericCellValue():(valorCelda.getCell(1).getCellType() == CellType.BOOLEAN)?"" + valorCelda.getCell(1).getBooleanCellValue():(valorCelda.getCell(1).getCellType() == CellType.BLANK)?"BLANK":(valorCelda.getCell(1).getCellType() == CellType.FORMULA)?"FORMULA":(valorCelda.getCell(1).getCellType() == CellType.ERROR)?"ERROR":"";   
                                ValoresEstaticos.objcuotas.setObreroGeneralTrab(stringValorCelda);
                                break;
                            case 18:
                                stringValorCelda = valorCelda.getCell(1) == null?"":(valorCelda.getCell(1).getCellType() == CellType.STRING)?valorCelda.getCell(1).getStringCellValue():(valorCelda.getCell(1).getCellType() == CellType.NUMERIC)?"" + valorCelda.getCell(1).getNumericCellValue():(valorCelda.getCell(1).getCellType() == CellType.BOOLEAN)?"" + valorCelda.getCell(1).getBooleanCellValue():(valorCelda.getCell(1).getCellType() == CellType.BLANK)?"BLANK":(valorCelda.getCell(1).getCellType() == CellType.FORMULA)?"FORMULA":(valorCelda.getCell(1).getCellType() == CellType.ERROR)?"ERROR":"";   
                                ValoresEstaticos.objcuotas.setDesempleoTrab(stringValorCelda); 
                                break;
                            case 19:
                                stringValorCelda = valorCelda.getCell(1) == null?"":(valorCelda.getCell(1).getCellType() == CellType.STRING)?valorCelda.getCell(1).getStringCellValue():(valorCelda.getCell(1).getCellType() == CellType.NUMERIC)?"" + valorCelda.getCell(1).getNumericCellValue():(valorCelda.getCell(1).getCellType() == CellType.BOOLEAN)?"" + valorCelda.getCell(1).getBooleanCellValue():(valorCelda.getCell(1).getCellType() == CellType.BLANK)?"BLANK":(valorCelda.getCell(1).getCellType() == CellType.FORMULA)?"FORMULA":(valorCelda.getCell(1).getCellType() == CellType.ERROR)?"ERROR":"";   
                                ValoresEstaticos.objcuotas.setFormacionTrab(stringValorCelda);
                                break;
                            case 20:
                                stringValorCelda = valorCelda.getCell(1) == null?"":(valorCelda.getCell(1).getCellType() == CellType.STRING)?valorCelda.getCell(1).getStringCellValue():(valorCelda.getCell(1).getCellType() == CellType.NUMERIC)?"" + valorCelda.getCell(1).getNumericCellValue():(valorCelda.getCell(1).getCellType() == CellType.BOOLEAN)?"" + valorCelda.getCell(1).getBooleanCellValue():(valorCelda.getCell(1).getCellType() == CellType.BLANK)?"BLANK":(valorCelda.getCell(1).getCellType() == CellType.FORMULA)?"FORMULA":(valorCelda.getCell(1).getCellType() == CellType.ERROR)?"ERROR":"";   
                                ValoresEstaticos.objcuotas.setContingenciasComunesEmpre(stringValorCelda);
                                break;
                            case 21:
                                stringValorCelda = valorCelda.getCell(1) == null?"":(valorCelda.getCell(1).getCellType() == CellType.STRING)?valorCelda.getCell(1).getStringCellValue():(valorCelda.getCell(1).getCellType() == CellType.NUMERIC)?"" + valorCelda.getCell(1).getNumericCellValue():(valorCelda.getCell(1).getCellType() == CellType.BOOLEAN)?"" + valorCelda.getCell(1).getBooleanCellValue():(valorCelda.getCell(1).getCellType() == CellType.BLANK)?"BLANK":(valorCelda.getCell(1).getCellType() == CellType.FORMULA)?"FORMULA":(valorCelda.getCell(1).getCellType() == CellType.ERROR)?"ERROR":"";   
                                ValoresEstaticos.objcuotas.setFogosa(stringValorCelda);
                                break;
                            case 22:
                                stringValorCelda = valorCelda.getCell(1) == null?"":(valorCelda.getCell(1).getCellType() == CellType.STRING)?valorCelda.getCell(1).getStringCellValue():(valorCelda.getCell(1).getCellType() == CellType.NUMERIC)?"" + valorCelda.getCell(1).getNumericCellValue():(valorCelda.getCell(1).getCellType() == CellType.BOOLEAN)?"" + valorCelda.getCell(1).getBooleanCellValue():(valorCelda.getCell(1).getCellType() == CellType.BLANK)?"BLANK":(valorCelda.getCell(1).getCellType() == CellType.FORMULA)?"FORMULA":(valorCelda.getCell(1).getCellType() == CellType.ERROR)?"ERROR":"";   
                                ValoresEstaticos.objcuotas.setDesempleoEmpre(stringValorCelda);
                                break;
                            case 23:
                                stringValorCelda = valorCelda.getCell(1) == null?"":(valorCelda.getCell(1).getCellType() == CellType.STRING)?valorCelda.getCell(1).getStringCellValue():(valorCelda.getCell(1).getCellType() == CellType.NUMERIC)?"" + valorCelda.getCell(1).getNumericCellValue():(valorCelda.getCell(1).getCellType() == CellType.BOOLEAN)?"" + valorCelda.getCell(1).getBooleanCellValue():(valorCelda.getCell(1).getCellType() == CellType.BLANK)?"BLANK":(valorCelda.getCell(1).getCellType() == CellType.FORMULA)?"FORMULA":(valorCelda.getCell(1).getCellType() == CellType.ERROR)?"ERROR":"";   
                                ValoresEstaticos.objcuotas.setFormacionEmpre(stringValorCelda);
                                break;
                            case 24:
                                stringValorCelda = valorCelda.getCell(1) == null?"":(valorCelda.getCell(1).getCellType() == CellType.STRING)?valorCelda.getCell(1).getStringCellValue():(valorCelda.getCell(1).getCellType() == CellType.NUMERIC)?"" + valorCelda.getCell(1).getNumericCellValue():(valorCelda.getCell(1).getCellType() == CellType.BOOLEAN)?"" + valorCelda.getCell(1).getBooleanCellValue():(valorCelda.getCell(1).getCellType() == CellType.BLANK)?"BLANK":(valorCelda.getCell(1).getCellType() == CellType.FORMULA)?"FORMULA":(valorCelda.getCell(1).getCellType() == CellType.ERROR)?"ERROR":"";   
                                ValoresEstaticos.objcuotas.setAccidentes(stringValorCelda);
                                break;
                    }
                }

            }
            
            
            //RECORRE TRINEOS
            for (int r = 17; r < 36 ; r++) 
            {
                valorCelda = leerFilCoum.getRow(r);
                ObjetoTrineos trineos  = new ObjetoTrineos();
                if (valorCelda == null)
                {
                    //SI NO EXISTE VALOR EN ESA CELDA NO SE RECORRERA
                    break;
                }
                else
                {
 
                    stringValorCelda = valorCelda.getCell(2) == null?"":(valorCelda.getCell(2).getCellType() == CellType.STRING)?valorCelda.getCell(2).getStringCellValue():(valorCelda.getCell(2).getCellType() == CellType.NUMERIC)?"" + valorCelda.getCell(2).getNumericCellValue():(valorCelda.getCell(2).getCellType() == CellType.BOOLEAN)?"" + valorCelda.getCell(2).getBooleanCellValue():(valorCelda.getCell(2).getCellType() == CellType.BLANK)?"BLANK":(valorCelda.getCell(2).getCellType() == CellType.FORMULA)?"FORMULA":(valorCelda.getCell(2).getCellType() == CellType.ERROR)?"ERROR":"";
                    trineos.setNumTrineos(stringValorCelda);
                    stringValorCelda = valorCelda.getCell(3) == null?"":(valorCelda.getCell(3).getCellType() == CellType.STRING)?valorCelda.getCell(3).getStringCellValue():(valorCelda.getCell(3).getCellType() == CellType.NUMERIC)?"" + valorCelda.getCell(3).getNumericCellValue():(valorCelda.getCell(3).getCellType() == CellType.BOOLEAN)?"" + valorCelda.getCell(3).getBooleanCellValue():(valorCelda.getCell(3).getCellType() == CellType.BLANK)?"BLANK":(valorCelda.getCell(3).getCellType() == CellType.FORMULA)?"FORMULA":(valorCelda.getCell(3).getCellType() == CellType.ERROR)?"ERROR":"";   
                    trineos.setImporteBruto(stringValorCelda); 
                }
                 ValoresEstaticos.trineos.add(trineos);
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
