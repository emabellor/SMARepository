/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Peaton;

import BESA.ExceptionBESA;
import BESA.Kernell.Agent.AgentBESA;
import BESA.Kernell.Agent.Event.EventBESA;
import BESA.Kernell.Agent.GuardBESA;
import BESA.Kernell.Agent.StateBESA;
import BESA.Kernell.System.Directory.AgHandlerBESA;
import BESA.Log.ReportBESA;
import Data.*;
import Mundo.*;

/**
 *
 * @author Mauricio
 */
public class GuardaWakeUpPeaton extends GuardBESA
{
    @Override
    public boolean funcEvalBool(StateBESA objEvalBool) 
    {
        return true;
    }
    
    @Override
    public void funcExecGuard(EventBESA ebesa) 
    {
        DataWakeUpPeaton evArgs = (DataWakeUpPeaton)ebesa.getData();
        AgentBESA agente = this.getAgent();
        EstadoPeaton estado = (EstadoPeaton)agente.getState();
        
        estado.posX = evArgs.posX;
        estado.posY = evArgs.posY;
        estado.carroName = evArgs.sender;
        estado.posVehiculoX = evArgs.posVehiculoX;
        estado.posVehiculoY = evArgs.posVehiculoY;
        
        //Solicita el ingreso dentro del mapa
        DataIngresarPeaton dataEvent = new DataIngresarPeaton();
        dataEvent.sender = agente.getAlias();
        dataEvent.posX = estado.posX;
        dataEvent.posY = estado.posY;

        EventBESA event = new EventBESA(GuardaIngresarPeaton.class.getName(), dataEvent);
        try
        {
            AgHandlerBESA ah = agent.getAdmLocal().getHandlerByAlias("WORLD");
            ah.sendEvent(event);
        }
        catch (ExceptionBESA e)
        {
             ReportBESA.error(e);
        }
    }
}
