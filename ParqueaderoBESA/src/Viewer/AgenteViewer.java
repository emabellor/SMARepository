/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Viewer;

import BESA.ExceptionBESA;
import BESA.Kernell.Agent.*;
import BESA.Kernell.Agent.Event.EventBESA;
import BESA.Kernell.System.Directory.AgHandlerBESA;
import Data.*;
import Logging.ClassLogger;
import Logging.LogLevel;
import Mundo.*;

/**
 *
 * @author Mauricio
 */
public class AgenteViewer extends AgentBESA
{
    public AgenteViewer(String alias, StateBESA state, StructBESA structAgent, double passwd) throws KernellAgentExceptionBESA 
    {
        super(alias, state, structAgent, passwd);
    }
    
    @Override
    public void setupAgent() 
    {
        ClassLogger.LogMsg("SETUP AGENT -> " + getAlias());
        ClassLogger.LogMsg("Solicitando estado del mapa al agente mundo");
        SolicitarEstadoMundo();
    }

    @Override
    public void shutdownAgent() 
    {
        ClassLogger.LogMsg("SHUTDOWN AGENT -> " + getAlias());
    }
    
    public void SolicitarEstadoMundo()
    {
        EstadoViewer estado = (EstadoViewer)this.getState();
        String mundoActual = "WORLD";
        if (estado.pisoActual != 1)
            mundoActual += "_" + estado.pisoActual;
       
        
        DataGetMundoStatus dataToSend = new DataGetMundoStatus();
        dataToSend.sender = this.getAlias();        
        EventBESA event = new EventBESA(GuardaGetMundoStatus.class.getName(), dataToSend);
        try
        {
            AgHandlerBESA ah = this.getAdmLocal().getHandlerByAlias(mundoActual);
            ah.sendEvent(event);
        }
        catch(ExceptionBESA ex)
        {
            ClassLogger.LogMsg(ex.getMessage(), LogLevel.ERROR);
        } 
    }
    
}
