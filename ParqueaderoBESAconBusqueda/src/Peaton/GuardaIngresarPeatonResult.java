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
import Logging.*;
import Mundo.*;

/**
 *
 * @author Mauricio
 */
public class GuardaIngresarPeatonResult extends GuardBESA
{
    @Override
    public boolean funcEvalBool(StateBESA objEvalBool) 
    {
        return true;
    }
    
    @Override
    public void funcExecGuard(EventBESA ebesa) 
    {
        DataIngresarPeatonResult evArgs = (DataIngresarPeatonResult)ebesa.getData();
        AgentBESA agente = this.getAgent();
        EstadoPeaton estado = (EstadoPeaton)agente.getState();
        String sender = evArgs.sender;
        
        
        if (evArgs.result == false)
        {
            ClassLogger.LogMsg("Falla ingresando peaton. Vuelve a intentarlo!", LogLevel.ERROR);
            try
            {
                Thread.sleep(estado.actualizacionMs);
            }
            catch (InterruptedException ex)
            {
                ClassLogger.LogMsg("Excepcion en la aplicacion: " + ex.getMessage());
            }
            
            DataIngresarPeaton dataEvent = new DataIngresarPeaton();
            dataEvent.posX = evArgs.posX;
            dataEvent.posY = evArgs.posY;
            dataEvent.sender = agente.getAlias();
            
            EventBESA event = new EventBESA(GuardaIngresarPeaton.class.getName(), dataEvent);
            try
            {
                AgHandlerBESA ah = agent.getAdmLocal().getHandlerByAlias(estado.planoActual);
                ah.sendEvent(event);
            }
            catch (ExceptionBESA e)
            {
                 ReportBESA.error(e);
            }
        }
        else
        {
            //Exito - Continua con el siguiente paso - solicitar estado del peaton
            try
            {
                Thread.sleep(estado.actualizacionMs);
            }
            catch(InterruptedException ex)
            {
                ClassLogger.LogMsg("Exepcion no controlada en la aplicacion: " + ex.getMessage());
            }
               
            
            
            DataGetPeatonStatus dataEvent = new DataGetPeatonStatus();
            dataEvent.sender = agent.getAlias();
            dataEvent.x = estado.posX;
            dataEvent.y = estado.posY;
            
            EventBESA event = new EventBESA(GuardaGetPeatonStatus.class.getName(), dataEvent);
            try
            {
                AgHandlerBESA ah = agent.getAdmLocal().getHandlerByAlias(estado.planoActual);
                ah.sendEvent(event);
            }
            catch (ExceptionBESA e)
            {
                 ReportBESA.error(e);
            }
        }
    }
}
