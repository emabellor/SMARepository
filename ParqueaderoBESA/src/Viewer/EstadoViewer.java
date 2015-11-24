/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Viewer;

import BESA.Kernell.Agent.*;
import Parqueadero.ParqueaderoBESA.ICallbackViewer;

/**
 *
 * @author Mauricio
 */
public class EstadoViewer extends StateBESA
{
    ICallbackViewer callbackViewer;
    int numeroPisos;
    int pisoActual;
    
    
    public EstadoViewer (ICallbackViewer _callbackViewer, int _numeroPisos)
    {
        callbackViewer = _callbackViewer;
        numeroPisos = _numeroPisos;
        pisoActual = 1;
    }
}
