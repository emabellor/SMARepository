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
import Carro.GuardaRemoverCarroResult;
import Data.*;
import Logging.*;

/**
 *
 * @author Mauricio
 */
public class GuardaRemoverCarro extends GuardBESA
{
    
    @Override
    public boolean funcEvalBool(StateBESA objEvalBool) 
    {
        return true;
    }
    
    @Override
    public void funcExecGuard(EventBESA ebesa) 
    {
        DataRemoverCarro data = (DataRemoverCarro)ebesa.getData();
        AgentBESA agent = this.getAgent();
        EstadoPiso estado = (EstadoPiso)agent.getState();
        String sender = data.sender;
        
        estado.RemoverAgente(sender);
        
        DataRemoverCarroResult dataEvent = new DataRemoverCarroResult();
        dataEvent.result = true;
        dataEvent.sender = agent.getAlias();
        
        EventBESA event = new EventBESA(GuardaRemoverCarroResult.class.getName(), dataEvent);
        try
        {
            AgHandlerBESA ah = agent.getAdmLocal().getHandlerByAlias(sender);
            ah.sendEvent(event);
        }
        catch(ExceptionBESA ex)
        {
            ClassLogger.LogMsg(ex.getMessage(), LogLevel.ERROR);
        }        
    }
}
