/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mundo;

import BESA.ExceptionBESA;
import BESA.Kernell.Agent.AgentBESA;
import BESA.Kernell.Agent.Event.EventBESA;
import BESA.Kernell.Agent.GuardBESA;
import BESA.Kernell.Agent.StateBESA;
import BESA.Kernell.System.Directory.AgHandlerBESA;
import Carro.GuardaRemoverCarroResult;
import Data.DataIngresarPeaton;
import Data.DataRemovePeaton;
import Data.DataRemovePeatonResult;
import Data.DataRemoverCarroResult;
import Logging.ClassLogger;
import Logging.LogLevel;
import Peaton.GuardaRemoverPeatonResult;

/**
 *
 * @author Mauricio
 */
public class GuardaRemoverPeaton extends GuardBESA
{
    @Override
    public boolean funcEvalBool(StateBESA objEvalBool) 
    {
        return true;
    }
    
    @Override
    public void funcExecGuard(EventBESA ebesa) 
    {
        DataRemovePeaton data = (DataRemovePeaton)ebesa.getData();
        AgentBESA agent = this.getAgent();
        EstadoMundo estado = (EstadoMundo)agent.getState();
        String sender = data.sender;
        
        estado.RemoverAgente(sender);
        
        DataRemovePeatonResult dataEvent = new DataRemovePeatonResult();
        dataEvent.result = true;
        dataEvent.sender = agent.getAlias();
        
        EventBESA event =  new EventBESA(GuardaRemoverPeatonResult.class.getName(), dataEvent);
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
