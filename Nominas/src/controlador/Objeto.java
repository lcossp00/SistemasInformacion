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
    String nacion;
    String email;
    String cuentaErronea;
    //Practica 4
    String salarioBase;
    String complementos;
    String numTrienios;
    String importeBruto;
    String brutoAnual;
    String retencion;
    
    public String getcuentaErronea()
    {
        return this.cuentaErronea;
    }
    public void setcuentaErronea(String cuentaErronea)
    {
        this.cuentaErronea = cuentaErronea;
    }
    
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
        //puede que aqui se calcule el salrio base y los completmetnos
    }
     
    //Practica 4
    public String getSalarioBase()
    {
        return this.salarioBase;
    }
     public String getComplementos()
    {
        return this.complementos;
    }
     
    public void setBrutoAnual(String brutoAnual)
    {
        this.brutoAnual = brutoAnual;
        //puede que aqui se calcule el salrio base y los completmetnos
    }
    public String getBrutoAnual()
    {
        return this.salarioBase;
    }
    public String getRetencion(){
        return this.retencion;
    }
    
    public void setTrienios(String numTrienios){
        this.numTrienios = numTrienios;
    }
    public String getTrienios(){
        return this.numTrienios;
    }
    public String getImporteBruto(){
        return this.importeBruto;
    }
}

