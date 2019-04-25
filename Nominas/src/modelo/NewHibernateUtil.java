/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import controlador.Nomina;
import controlador.Trabajadorbbdd;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author virxi
 */
public class NewHibernateUtil 
{

    private static final SessionFactory sessionFactory;
    
    static 
    {
        try {
            // Create the SessionFactory from standard (hibernate.cfg.xml) 
            // config file.
            sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            // Log the exception. 
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    public boolean controlDni(String dni)
    {
        //REALIZAMOS UN QUERY EN BUSCA DEL DNI ENVIDADO
        boolean correcto = true;
        String busqueda = "FROM Trabajadorbbdd T WHERE T.nifnie = '" + dni + "'";
        
        SessionFactory sesionFactoria = NewHibernateUtil.getSessionFactory();
        Session session = sesionFactoria.openSession();
        
        Query error = session.createQuery(busqueda);
        
        //CREAMOS UNA LISTA, SI ESTA ESTA VACIA ESQUE NO EXISTE EL DNI Y SI ESTA LLENA SI EXISTE
        List<Trabajadorbbdd> datos = error.list();
        if(datos.isEmpty())
        {
            correcto = false;
        }
        
        return correcto;
    }
    public void datos(String dni)
    {
        //REALIZAMOS UNA QUERY DONDE SELECCIONAMOS DE LA TABLA TRABAJADORESBBDD EL TRBAJADOR
        //CON ESE DNI
        String hql = "FROM Trabajadorbbdd T WHERE T.nifnie = '" + dni + "'";
        SessionFactory sesionFactoria = NewHibernateUtil.getSessionFactory();
        
        Session session = sesionFactoria.openSession();
        
        Query query = session.createQuery(hql);
        //GUARDAMOS EN UNA LISTA LA QUERY OBTENIDA
        List<Trabajadorbbdd> datos = query.list();
        
        int idEmpresa = 0;
        
        //USAMOS UN FOR PARA OBTENER LOS DATOS DE ESE TRABAJADOR
        for (Trabajadorbbdd t : datos)
        {
            System.out.println(t.getNombre());
            System.out.println(t.getApellido1());
            System.out.println(t.getApellido2());
            System.out.println(t.getNifnie());
            
            idEmpresa = t.getEmpresas().getIdEmpresa();
        }
        
        //VOLVEMOS HACER OTRA QUERY PARA OBTENER EL NUMERO DE EMPRESAS
        String hql2 = "FROM Trabajadorbbdd T WHERE T.empresas.idEmpresa = " + idEmpresa;
        Query query2 = session.createQuery(hql2);
        
        List<Trabajadorbbdd> mismaEmpresa = query2.list();
        
        System.out.println(mismaEmpresa.size());
    }
    public void incrementar(String dni)
    {
        //REALIZAMOS UNA QUERY PARA OBTENER LOS DATOS DEL TRABAJADOR
        String hql = "FROM Trabajadorbbdd T WHERE T.nifnie = '" + dni + "'";
        SessionFactory sesionFactoria = NewHibernateUtil.getSessionFactory();
        
        Session session = sesionFactoria.openSession();
        
        Query query = session.createQuery(hql);
        
        List<Trabajadorbbdd> datos = query.list();
        
        int idTrabajador = 0;
        
        //OBTENEMOS SU ID PARA RELACIONARLO CON LA TABLA NOMINA
        for (Trabajadorbbdd t : datos)
        {
            idTrabajador = t.getIdTrabajador();
            //System.out.println(idTrabajador);
            
        }
        
        //REALIZAMOS OTRA QUERY PARA SELECCIONAR UN SALARIO SABIENDO EL ID
        String hql2 = "FROM Nomina T WHERE T.trabajadorbbdd.idTrabajador = " + idTrabajador;
        Query query2 = session.createQuery(hql2);
        List<Nomina> nominas = query2.list();
        
        double salario = 0;
        
        //BUSCAMOS EL IMPORTESALARIOMES Y LO INCREMENTAMOS
        for (Nomina t : nominas)
        {
            salario = t.getImporteSalarioMes();
        }
        salario = salario + 500.0;
        
        //UPDATE A LA QUERY SELECCIONADA
        Transaction tx;
        
        String incrementar = "UPDATE Nomina N set N.importeSalarioMes  = :importeSalarioMes WHERE  N.trabajadorbbdd.idTrabajador = :idTrabajador";
        Query queryIncrem = session.createQuery(incrementar);
        queryIncrem.setParameter("importeSalarioMes", salario);
        queryIncrem.setParameter("idTrabajador", idTrabajador);
        
        
        
        tx = session.beginTransaction();
        tx.commit();
    }
    
    public void eliminar()
    {
        //REALIZAMOS LA QUERY PARA BUSCAR EL IRPF
        String e = "SELECT N.irpf FROM Nomina N";
        SessionFactory sesionFactoria = NewHibernateUtil.getSessionFactory();
        
        Session session = sesionFactoria.openSession();
        
        Query queryEliminar = session.createQuery(e);
        
        List<Double> irpf = queryEliminar.list();
        
        double irpfMax = 0;
        
        //BUSCAMOS EL IRPF MAYOR
        for (Double n : irpf)
        {
            if(irpfMax < n)
            {
                irpfMax = n;
                
            }
            
        } 
        System.out.println(irpfMax);
        
        //LO ELIMINAMOS
        String eF = "DELETE FROM Nomina WHERE irpf = :irpfMax";
        Query query = session.createQuery(eF);
        query.setParameter("irpfMax", irpfMax);

    }
}
