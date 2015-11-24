/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Carro;

import Mundo.*;
import BESA.ExceptionBESA;
import BESA.Kernell.Agent.*;
import BESA.Kernell.Agent.Event.*;
import BESA.Kernell.System.Directory.AgHandlerBESA;
import Data.*;
import Logging.*;
/**
 *
 * @author Mauricio
 */
public class GuardaUpdateCarroResult extends GuardBESA
{
    @Override
    public boolean funcEvalBool(StateBESA objEvalBool) 
    {
        return true;
    }
    
    @Override
    public void funcExecGuard(EventBESA ebesa) 
    {
        DataUpdateCarroResult evArgs = (DataUpdateCarroResult)ebesa.getData();
        AgentBESA agente = this.getAgent();
        EstadoCarro estado = (EstadoCarro)agente.getState();
       
        if (evArgs.resultado == true)
        {
            estado.x = evArgs.nuevoX;
            estado.y = evArgs.nuevoY;
            estado.orientacion = evArgs.nuevaOrientacion;

        }

        //Vuelve a solicitar status
        ClassLogger.ThreadSleep(estado.actualizacionMs);
        DataGetCarroStatus getStatus = new DataGetCarroStatus();
        getStatus.sender = agente.getAlias();
        EventBESA event = new EventBESA(GuardaGetCarroStatus.class.getName(), getStatus);
        try
        {
            AgHandlerBESA ah = agent.getAdmLocal().getHandlerByAlias("WORLD");
            ah.sendEvent(event);
        }
        catch(ExceptionBESA ex)
        {
            ClassLogger.LogMsg(ex.getMessage(), LogLevel.ERROR);
        }
    }
}


