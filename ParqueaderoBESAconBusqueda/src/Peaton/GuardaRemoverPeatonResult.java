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
import Carro.*;
import Data.*;
import Logging.*;
import Mundo.*;

/**
 *
 * @author Mauricio
 */
public class GuardaRemoverPeatonResult extends GuardBESA
{
    @Override
    public boolean funcEvalBool(StateBESA objEvalBool) 
    {
        return true;
    }
    
    @Override
    public void funcExecGuard(EventBESA ebesa) 
    {
        DataRemovePeatonResult evArgs = (DataRemovePeatonResult)ebesa.getData();
        AgentBESA agente = this.getAgent();
        EstadoPeaton estado = (EstadoPeaton)agente.getState();
        
        if (evArgs.result == true)
        {
            if (estado.volver == true)
            {
                ClassLogger.LogMsg("El peaton ya se encuentra de nuevo en el vehiculo. Envia evento a carro!");
                DataRetornoPeaton dataEvent = new DataRetornoPeaton();
                dataEvent.sender = agente.getAlias();
                
                EventBESA event = new EventBESA(GuardaRetornoPeaton.class.getName(), dataEvent);
                try
                {
                    AgHandlerBESA ah = agent.getAdmLocal().getHandlerByAlias(estado.carroName);
                    ah.sendEvent(event);
                }
                catch (ExceptionBESA e)
                {
                     ReportBESA.error(e);
                }
            }
            else
            {
                //Espera 10 segundos y envia el evento de ingresar
                try
                {
                    Thread.sleep(estado.tiempoFueraMs);
                }
                catch (InterruptedException ex)
                {
                    ClassLogger.LogMsg("Excepcion no controlada en la aplicacion: " + ex.getMessage(), LogLevel.ERROR);
                }

                estado.volver = true;
                estado.posX = EstadoPiso.puntoEntradaPeatones.x;
                estado.posY = EstadoPiso.puntoEntradaPeatones.y;
                
                DataIngresarPeaton dataEvent = new DataIngresarPeaton();
                dataEvent.sender = agente.getAlias();
                dataEvent.posX = estado.posX;
                dataEvent.posY = estado.posY;
                
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
        }
        else
        {
            //Vuelve a intentar enviar la orden
            try
            {
                Thread.sleep(estado.actualizacionMs);
            }
            catch(InterruptedException ex)
            {
                ClassLogger.LogMsg("Excepcion en la aplicacion: " + ex.getMessage(), LogLevel.ERROR);
            }
            
            DataRemovePeaton dataEvent = new DataRemovePeaton();
            dataEvent.sender = agente.getAlias();
            EventBESA event = new EventBESA(GuardaRemoverPeaton.class.getName(), dataEvent);
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
