package controlador;

public class Objeto 
{
    String pos;
    String nombreTrabajador;
    String primerApell;
    String segApell;
    String nombreEmpre;
    String categoria;
    String cuenta;
    String iban;
    //CuentasBancarias iban; //quizas pueda ser una solucion ;_;
    String nacion;
    String email;
    
    public String getEmail()
    {
        return this.email;
    }
    public void setEmail(String email)
    {
        this.email = email;
    }
    
    public String getCuenta()
    {
        return this.cuenta;
    }
    public void setCuenta(String cuenta)
    {
        this.cuenta = cuenta;
    }
    public String getIban()
    {
        return this.iban;
    }
    public void setIban(String iban)
    {
        this.iban = iban;
    }
    
    public String getPos()
    {
        return this.pos;
    }
    public void setPos(String pos)
    {
        this.pos = pos;
    }
    
    public String getNacion()
    {
        return this.nacion;
    }
    public void setNacion(String nacion)
    {
        this.nacion = nacion;
    }
    
    public String getNombreTrabajador()
    {
        return this.nombreTrabajador;
    }
    public void setNombreTrabajador(String nombreTrabajador)
    {
        this.nombreTrabajador = nombreTrabajador;
    }
    
    public String getPrimerApell()
    {
        return this.primerApell;
    }
    public void setPrimerApell(String primerApell)
    {
        this.primerApell = primerApell;
    }
    
    public String getSegApell()
    {
        return this.segApell;
    }
    public void setSegApell(String segApell)
    {
        this.segApell = segApell;
    }
    
    public String getNombreEmpre()
    {
        return this.nombreEmpre;
    }
    public void setNombreEmpre(String nombreEmpre)
    {
        this.nombreEmpre = nombreEmpre;
    }
    
    public String getCategoria()
    {
        return this.categoria;
    }
    public void setCategoria(String categoria)
    {
        this.categoria = categoria;
    }
     
}

