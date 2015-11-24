/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Peaton;

import BESA.ExceptionBESA;
import BESA.Kernell.Agent.Event.EventBESA;
import BESA.Kernell.Agent.*;
import BESA.Kernell.System.Directory.AgHandlerBESA;
import BESA.Log.ReportBESA;
import Data.*;
import Logging.*;
import Mundo.*;
import java.awt.Point;

/**
 *
 * @author Mauricio
 */
public class GuardaUpdatePeatonResult extends GuardBESA
{
    @Override
    public boolean funcEvalBool(StateBESA objEvalBool) 
    {
        return true;
    }
    
    @Override
    public void funcExecGuard(EventBESA ebesa) 
    {
        DataUpdatePeatonResult evArgs = (DataUpdatePeatonResult)ebesa.getData();
        AgentBESA agente = this.getAgent();
        EstadoPeaton estado = (EstadoPeaton)agente.getState();
        String sender = evArgs.sender;
        
        if (evArgs.resultado == true)
        {
            estado.posX = evArgs.posX;
            estado.posY = evArgs.posY;
        }
        
        boolean continuarMovimiento = true;
        Point puntoComparacion = new Point();
        if (estado.volver == false)
            puntoComparacion = EstadoPiso.puntoSalidaPeatones;
        else
            puntoComparacion = new Point(estado.posVehiculoX, estado.posVehiculoY);
        
        
        int deltaX = Math.abs(estado.posX - puntoComparacion.x);
        int deltaY = Math.abs(estado.posY - puntoComparacion.y);
        if (deltaX <= 1 && deltaY <= 1)
        {
            //Enviar el evento de eliminar el agente de la lista
            continuarMovimiento = false;
            
            DataRemovePeaton dataEvent = new DataRemovePeaton();
            dataEvent.sender = agente.getAlias();
                    
            EventBESA event = new EventBESA(GuardaRemoverPeaton.class.getName(), dataEvent);
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

        
        if (continuarMovimiento == true)
        {
            //Continua movimiento - Solicita nuevo estado a los sensores
            try
            {
                Thread.sleep(estado.actualizacionMs);
            }
            catch(InterruptedException ex)
            {
                ClassLogger.LogMsg("Excepcion no controlada en la aplicacion: " + ex.getMessage(), LogLevel.DEBUG);
            }

            DataGetPeatonStatus dataEvent = new DataGetPeatonStatus();
            dataEvent.sender = agente.getAlias();
            dataEvent.x = estado.posX;
            dataEvent.y = estado.posY;

            EventBESA event = new EventBESA(GuardaGetPeatonStatus.class.getName(), dataEvent);
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
}
