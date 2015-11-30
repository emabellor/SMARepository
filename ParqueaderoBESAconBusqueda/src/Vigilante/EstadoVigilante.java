/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vigilante;

import BESA.Kernell.Agent.StateBESA;
import java.awt.Point;

/**
 *
 * @author Mauricio
 */
public class EstadoVigilante extends StateBESA
{
     public enum DeseoVigilante
    {
        ingresar,
        irSuperiorDerecha,
        irSuperiorIzquierda,
        irInferiorDerecha,
        irInferiorIzquierda,
    }
    
    private int x;
    private int y;
    
    private int size;
    
    private DeseoVigilante deseo;
    
    public EstadoVigilante(int _size)
    {
        x = -1;
        y = -1;
        deseo = DeseoVigilante.ingresar;
        size = _size;
    }
    
    public Point GetPosicion()
    {
        return new Point(x, y);
    }
    
    public int GetX()
    {
        return x;
    }
    
    public int GetY()
    {
        return y;
    }
    
    public void SetX(int _x)
    {
        x = _x;
    }
    
    public void SetY(int _y)
    {
        y = _y;
    }
    
    public DeseoVigilante GetDeseo()
    {
        return deseo;
    }
    
    public void SetDeseo(DeseoVigilante _deseo)
    {
        deseo = _deseo;
    }
    
    public int GetSize()
    {
        return size;
    }
}
