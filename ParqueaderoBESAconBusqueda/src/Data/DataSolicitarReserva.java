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
public class DataSolicitarReserva extends DataBESA
{
    public String sender;
    public Point posicionActual;
    
    public DataSolicitarReserva (String _sender, Point _posicionActual)
    {
        sender = _sender;
        posicionActual = _posicionActual;
    }
}
