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
public class DataGetMundoStatus extends DataBESA
{
    public String sender;
    public Class senderClass;
    public String referenciaAgente;
    public Point referenciaPunto;
    
    public DataGetMundoStatus(String _sender, Class _senderClass, String _referenciaAgente, Point _referenciaPunto)
    {
        sender = _sender;
        senderClass = _senderClass;
        referenciaAgente = _referenciaAgente;
        referenciaPunto = _referenciaPunto;
    }    
}
