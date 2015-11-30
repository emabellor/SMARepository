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
import Data.*;
import Logging.*;
import Mundo.*;
import Reservas.*;
import java.awt.Point;

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
                AgHandlerBESA ah = agent.getAdmLocal().getHandlerByAlias(estado.pisoActual);
                ah.sendEvent(event);
            }
            catch(ExceptionBESA ex)
            {
                ClassLogger.LogMsg(ex.getMessage(), LogLevel.ERROR);
            }
            
            //Creando ejemplo dummy para la prueba del A*
            DataSolicitarReserva dataReserva = new DataSolicitarReserva(agente.getAlias(), new Point(0,0));
            event = new EventBESA(GuardaSolicitarReserva.class.getName(), dataReserva);
            try
            {
                AgHandlerBESA ah = agente.getAdmLocal().getHandlerByAlias("Reservas");
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
            DataIngresarCarro dataEvent = new DataIngresarCarro(agente.getAlias(), AgenteCarro.class, agente.getAlias(), EstadoPiso.puntoEntradaPisoAbajo);
            EventBESA event = new EventBESA(GuardaIngresarCarro.class.getName(), dataEvent);
            try
            {
                AgHandlerBESA ah = agent.getAdmLocal().getHandlerByAlias(estado.pisoActual);
                ah.sendEvent(event);
            }
            catch(ExceptionBESA ex)
            {
                ClassLogger.LogMsg(ex.getMessage(), LogLevel.ERROR);
            }
        }
    }
    
}
