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
    static String fileErroneos = "resources/erroneos.xml";
    static String fileErroneosCCC = "resources/erroneosCCC.xml";

     //CREA EL XML DE DNI       
    public void crearDOM(ArrayList<Objeto> listErroneos) throws ParserConfigurationException, TransformerException
    {
        //DOCUMENT
        DocumentBuilderFactory documentF = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentB = documentF.newDocumentBuilder();
        Document document = documentB.newDocument();
        Element root = document.createElement("Trabajadores");
        document.appendChild(root);
 
            for(Objeto t:listErroneos)
            {
              if(null != t.getNombreTrabajador())
              { 
                Element empleado = document.createElement("Trabajador");
                root.appendChild(empleado);
                Attr attr = document.createAttribute("Id");
                attr.setValue(t.getPos());
                
                //ETIQUETAS
                Element nombreTrabajador = document.createElement("Nombre_Trabajador");
                Element apellidoPrimero = document.createElement("Primer_Apellido");
                Element apellidoSegundo = document.createElement("Segundo_Apellido");
                Element empresa = document.createElement("Nombre_Empresa");
                Element categoria = document.createElement("Categoria");
                
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
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File(fileErroneos));
            transformer.transform(domSource, streamResult);
                
    }
    
    //CREA EL XML DE IBAN
    public void crearDOMIban(ArrayList<Objeto> listErroneos) throws ParserConfigurationException, TransformerException
    {
        //DOCUMENT
        DocumentBuilderFactory documentF = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentB = documentF.newDocumentBuilder();
        Document document = documentB.newDocument();
        Element root = document.createElement("Trabajadores");
        document.appendChild(root);
 
            for(Objeto t:listErroneos)
            {
              if(t.getcuentaErronea() != null)
              {
                Element empleado = document.createElement("Trabajador");
                root.appendChild(empleado);
                Attr attr = document.createAttribute("Id");
                attr.setValue(t.getPos());
                
                //ETIQUETAS
                Element nombreTrabajador = document.createElement("Nombre_Trabajador");
                Element apellidoPrimero = document.createElement("Primer_Apellido");
                Element apellidoSegundo = document.createElement("Segundo_Apellido");
                Element empresa = document.createElement("Nombre_Empresa");
                Element categoria = document.createElement("Categoria");
                Element cuentaErronea = document.createElement("CuentaErronea");
                Element iban = document.createElement("IBAN");
                
                //VALORES DE LAS ETIQUETAS
                empleado.setAttributeNode(attr);
                
                //NOMBRE DEL TRABAJADOR
                nombreTrabajador.appendChild(document.createTextNode(t.getNombreTrabajador()));
                empleado.appendChild(nombreTrabajador);

                //PRIMER APELLIDO
                apellidoPrimero.appendChild(document.createTextNode(t.getPrimerApell()));
                empleado.appendChild(apellidoPrimero);

                //SEGUNDO APELLIDO
                if(t.getSegApell() != null)
                {
                    apellidoSegundo.appendChild(document.createTextNode(t.getSegApell()));
                    empleado.appendChild(apellidoSegundo);
                }

                //NOMBRE DE LA EMPRESA
                empresa.appendChild(document.createTextNode(t.getNombreEmpre()));
                empleado.appendChild(empresa);

                //CATEGORIA EN LA QUE SE ENCUENTRA EL TRABAJADOR
                categoria.appendChild(document.createTextNode(t.getCategoria()));
                empleado.appendChild(categoria);
                
                //CATEGORIA EN LA QUE SE ENCUENTRA EL TRABAJADOR
                cuentaErronea.appendChild(document.createTextNode(t.getcuentaErronea()));
                empleado.appendChild(cuentaErronea);
                
                //IBAN DEL TRABAJADOR
                iban.appendChild(document.createTextNode(t.getIban()));
                empleado.appendChild(iban);
              }
                
            }

            //TRANFORMACION A XML
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File(fileErroneosCCC));
            transformer.transform(domSource, streamResult);
                
    }
}
