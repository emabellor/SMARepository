/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import BESA.Kernell.Agent.Event.DataBESA;
import java.awt.*;

/**
 *
 * @author Mauricio
 */
public class DataIngresarCarro extends DataBESA
{
    public String sender;
    public Class type;
    public String nombreAgente;
    public Point puntoIngreso;
    
    public DataIngresarCarro(String _sender, Class _type, String _nombreAgente, Point _puntoIngreso)
    {
        sender = _sender;
        type = _type;
        nombreAgente = _nombreAgente;
        puntoIngreso = _puntoIngreso;
    }   
}
