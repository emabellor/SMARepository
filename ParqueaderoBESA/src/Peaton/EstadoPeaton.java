/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Peaton;

import BESA.Kernell.Agent.StateBESA;
import java.awt.Point;

/**
 *
 * @author Mauricio
 */
public class EstadoPeaton extends StateBESA
{
    public int posX;
    public int posY;
    public boolean volver = false;
    
    public int posVehiculoX;
    public int posVehiculoY;
    public String carroName;
    
    public int actualizacionMs = 200;
    public int tiempoFueraMs = 60 * 1000;
    
    public EstadoPeaton()
    {
        posX = -1;
        posY = -1;
    }
    
}
