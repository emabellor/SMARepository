/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import BESA.Kernell.Agent.Event.DataBESA;
import Carro.*;

/**
 *
 * @author Mauricio
 */
public class DataIngresarCarroResult extends DataBESA
{
    public String sender;
    public boolean resultado;
    public int nuevoX;
    public int nuevoY;
    public EstadoCarro.Orientacion nuevaOrientacion;
    public String nombreAgente;

    public DataIngresarCarroResult(String _sender, boolean _resultado, int _nuevoX, int _nuevoY, EstadoCarro.Orientacion _nuevaOrientacion, String _nombreAgente)
    {
        sender = _sender;
        resultado = _resultado;
        nuevoX = _nuevoX;
        nuevoY = _nuevoY;
        nuevaOrientacion = _nuevaOrientacion;
        nombreAgente = _nombreAgente;
    }
    
}
