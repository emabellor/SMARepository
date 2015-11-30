/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import BESA.Kernell.Agent.Event.DataBESA;

/**
 *
 * @author Mauricio
 */
public class DataWakeUpPeaton extends DataBESA
{
    public String sender;
    public String planoActual;
    public int posX;
    public int posY;
   
    public int posVehiculoX;
    public int posVehiculoY;
    
    public DataWakeUpPeaton (String _sender, String _planoActual, int _posX, int _posY, int _posVehiculoX, int _posVehiculoY)
    {
        sender = _sender;
        planoActual = _planoActual;
        posX = _posX;
        posY = _posY;
        posVehiculoX = _posVehiculoX;
        posVehiculoY = _posVehiculoY;
    }
}
