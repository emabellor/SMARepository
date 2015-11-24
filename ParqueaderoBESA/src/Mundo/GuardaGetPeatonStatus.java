/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mundo;

import BESA.ExceptionBESA;
import BESA.Kernell.Agent.*;
import BESA.Kernell.Agent.Event.EventBESA;
import BESA.Kernell.System.Directory.AgHandlerBESA;
import BESA.Log.ReportBESA;
import Data.*;
import Peaton.*;

/**
 *
 * @author Mauricio
 */
public class GuardaGetPeatonStatus extends GuardBESA
{
     @Override
    public boolean funcEvalBool(StateBESA objEvalBool) 
    {
        return true;
    }
    
    @Override
    public void funcExecGuard(EventBESA ebesa) 
    {
        DataGetPeatonStatus data = (DataGetPeatonStatus)ebesa.getData();
        AgentBESA agent = this.getAgent();
        EstadoPiso estado = (EstadoPiso)agent.getState();
        String sender = data.sender;
        
        DataGetPeatonStatusResult dataEvent = new DataGetPeatonStatusResult();
        dataEvent.sender = agent.getAlias();
        dataEvent.estadoAgente = estado.ObtenerEstadoMapaPeaton(data.x, data.y, sender);
        
        EventBESA event = new EventBESA(GuardaGetPeatonStatusResult.class.getName(), dataEvent);
        try
        {
            AgHandlerBESA ah = agent.getAdmLocal().getHandlerByAlias(sender);
            ah.sendEvent(event);
        }
        catch (ExceptionBESA e)
        {
             ReportBESA.error(e);
        }
    }  
}
