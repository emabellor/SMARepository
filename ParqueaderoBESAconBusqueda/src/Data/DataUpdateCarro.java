/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import BESA.Kernell.Agent.Event.DataBESA;
import Carro.EstadoCarro;

/**
 *
 * @author Mauricio
 */
public class DataUpdateCarro extends DataBESA
{
    public String sender;
    public int nuevoX;
    public int nuevoY;
    public EstadoCarro.Orientacion nuevaOrientacion;
    
    public DataUpdateCarro (String _sender, int _nuevoX, int _nuevoY, EstadoCarro.Orientacion _nuevaOrientacion)
    {
        sender = _sender;
        nuevoX = _nuevoX;
        nuevoY = _nuevoY;
        nuevaOrientacion = _nuevaOrientacion;
    }
    
}
