package controlador;

import java.io.File;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

//CONVIERTE UN ARBOL DOM EN UN XML, ESCOGIENDO LOS DATOS RECOPILADOS ANTERIORMENTE DE LOS TRABJADORES CON DNI ERRONEOS
public class ConvertirDOM 
{
   //VARIABLES
    static String fileErroneos = "resources/SistemasInformacionIIErroneos.xml";
    
    //DOCUMENT
    static DocumentBuilderFactory documentF;
    static DocumentBuilder documentB;
    Document document;
    static Element empleado;
    static Element nombreTrabajador;
    static Element apellidoPrimero;
    static Element apellidoSegundo;
    static Element categoria;
    static Element empresa;
    
    //TRANFORMAR DOM A XML
    static TransformerFactory transformerFactory;
    static Transformer transformer; 
    static DOMSource domSource;
    static StreamResult streamResult;
    
    Attr attr;
            
    public void crearDOM(ArrayList<Objeto> listErroneos) throws ParserConfigurationException, TransformerException
    {
        //DOCUMENT
        documentF = DocumentBuilderFactory.newInstance();
        documentB = documentF.newDocumentBuilder();
        document = documentB.newDocument();
        Element root = document.createElement("Trabajadores");
        document.appendChild(root);
 
            for(Objeto t:listErroneos)
            {
              if(null != t.getNombreTrabajador())
              { 
                empleado = document.createElement("Trabajador");
                root.appendChild(empleado);
                attr = document.createAttribute("Id");
                attr.setValue(t.getPos());
                
                //ETIQUETAS
                nombreTrabajador = document.createElement("Nombre_Trabajador");
                apellidoPrimero = document.createElement("Primer_Apellido");
                apellidoSegundo = document.createElement("Segundo_Apellido");
                empresa = document.createElement("Nombre_Empresa");
                categoria = document.createElement("Categoria");
                
                //VALORES DE LAS ETIQUETAS
                empleado.setAttributeNode(attr);
                
                //NOMBRE DEL TRABAJADOR
                nombreTrabajador.appendChild(document.createTextNode(t.getNombreTrabajador()));
                empleado.appendChild(nombreTrabajador);

                //PRIMER APELLIDO
                apellidoPrimero.appendChild(document.createTextNode(t.getPrimerApell()));
                empleado.appendChild(apellidoPrimero);

                //SEGUNDO APELLIDO
                apellidoSegundo.appendChild(document.createTextNode(t.getSegApell()));
                empleado.appendChild(apellidoSegundo);

                //NOMBRE DE LA EMPRESA
                empresa.appendChild(document.createTextNode(t.getNombreEmpre()));
                empleado.appendChild(empresa);

                //CATEGORIA EN LA QUE SE ENCUENTRA EL TRABAJADOR
                categoria.appendChild(document.createTextNode(t.getCategoria()));
                empleado.appendChild(categoria);
              }
                
            }

            //TRANFORMACION A XML
            transformerFactory = TransformerFactory.newInstance();
            transformer = transformerFactory.newTransformer();
            domSource = new DOMSource(document);
            streamResult = new StreamResult(new File(fileErroneos));
            transformer.transform(domSource, streamResult);
                
    }
}
