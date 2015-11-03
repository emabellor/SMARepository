/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import BESA.Kernell.Agent.AgentBESA;
import BESA.Kernell.Agent.Event.DataBESA;

/**
 *
 * @author Mauricio
 */
public class ClassElemento 
{
    public int posX;
    public int posY;
    public String label;
    public TipoElemento tipo;
    public Object parametro;
    
    public ClassElemento (ClassElemento agentPos)
    {
        label = agentPos.label;
        tipo = agentPos.tipo;
        parametro = agentPos.parametro;
        posX = agentPos.posX;
        posY = agentPos.posY;
    }
    
    public ClassElemento(String _label, TipoElemento _tipo, Object _parametro)
    {
        label = _label;
        tipo = _tipo;
        parametro = _parametro;
        posX = -1;
        posY = -1;
    }
    
    public ClassElemento(int _posX, int _posY, String _label, TipoElemento _tipo, Object _parametro)
    {
        posX = _posX;
        posY = _posY;
        label = _label;
        tipo = _tipo;
        parametro = _parametro;
    }
}
