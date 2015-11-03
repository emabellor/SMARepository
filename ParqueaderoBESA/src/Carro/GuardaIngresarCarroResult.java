/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Carro;

import BESA.ExceptionBESA;
import BESA.Kernell.Agent.AgentBESA;
import BESA.Kernell.Agent.Event.EventBESA;
import BESA.Kernell.Agent.GuardBESA;
import BESA.Kernell.Agent.StateBESA;
import BESA.Kernell.System.Directory.AgHandlerBESA;
import Data.DataGetCarroStatus;
import Data.DataIngresarCarro;
import Data.DataIngresarCarroResult;
import Data.DataUpdateCarro;
import Data.DataUpdateCarroResult;
import Logging.ClassLogger;
import Logging.LogLevel;
import Mundo.GuardaGetCarroStatus;
import Mundo.GuardaIngresarCarro;
import Mundo.GuardaUpdateCarro;

/**
 *
 * @author Mauricio
 */
public class GuardaIngresarCarroResult extends GuardBESA 
{
    @Override
    public boolean funcEvalBool(StateBESA objEvalBool) 
    {
        return true;
    }
    
    @Override
    public void funcExecGuard(EventBESA ebesa) 
    {
        DataIngresarCarroResult evArgs = (DataIngresarCarroResult)ebesa.getData();
        AgentBESA agente = this.getAgent();
        EstadoCarro estado = (EstadoCarro)agente.getState();
        
        try
        {
            Thread.sleep(estado.actualizacionMs);
        }
        catch (InterruptedException ex)
        {
            ClassLogger.LogMsg(ex.getMessage(), LogLevel.ERROR);
        }

        if (evArgs.resultado == true)
        {
            //Actualiza y solicita status
            estado.x = evArgs.nuevoX;
            estado.y = evArgs.nuevoY;
            estado.orientacion = evArgs.nuevaOrientacion;
            
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
        else
        {
           //Vuelve a solicitar actualizacion
            DataIngresarCarro dataEvent = new DataIngresarCarro();
            dataEvent.sender = agente.getAlias();
            EventBESA event = new EventBESA(GuardaIngresarCarro.class.getName(), dataEvent);
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
    
}
