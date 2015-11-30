/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import BESA.Kernell.Agent.Event.DataBESA;
import java.awt.*;
import java.util.List;

/**
 *
 * @author Mauricio
 */
public class DataGetMundoStatusResult extends DataBESA
{
    public ClassElemento[][] listaElementos;
    public List<ClassElemento> listaAgentes;
    public String referenciaAgente;
    public Point referenciaPunto;
    
    public DataGetMundoStatusResult (ClassElemento[][] _listaElementos, List<ClassElemento> _listaAgentes, String _referenciaAgente, Point _referenciaPunto)
    {
        listaElementos = _listaElementos;
        listaAgentes = _listaAgentes;
        referenciaAgente = _referenciaAgente;
        referenciaPunto = _referenciaPunto;
    }
}
