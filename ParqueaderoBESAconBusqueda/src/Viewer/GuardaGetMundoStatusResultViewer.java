/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Viewer;

import BESA.Kernell.Agent.*;
import BESA.Kernell.Agent.Event.EventBESA;
import Data.*;
import Logging.*;

/**
 *
 * @author Mauricio
 */
public class GuardaGetMundoStatusResultViewer extends GuardBESA
{
    @Override
    public boolean funcEvalBool(StateBESA objEvalBool) 
    {
        return true;
    }
    
    @Override
    public void funcExecGuard(EventBESA ebesa) 
    {
        DataGetMundoStatusResult data = (DataGetMundoStatusResult)ebesa.getData();
        EstadoViewer estado = (EstadoViewer)this.getAgent().getState();
        AgenteViewer agente = (AgenteViewer)this.getAgent();
        
        estado.callbackViewer.Callback(data.listaElementos, data.listaAgentes);
        ClassLogger.ThreadSleep(100);
        
        agente.SolicitarEstadoMundo();
    }
}
