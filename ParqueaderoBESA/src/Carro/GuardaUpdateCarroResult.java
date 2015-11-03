/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Carro;

import BESA.ExceptionBESA;
import BESA.Kernell.Agent.AgentBESA;
import BESA.Kernell.Agent.Event.DataBESA;
import BESA.Kernell.Agent.Event.EventBESA;
import BESA.Kernell.Agent.GuardBESA;
import BESA.Kernell.Agent.StateBESA;
import BESA.Kernell.System.Directory.AgHandlerBESA;
import BESA.Log.ReportBESA;
import Data.DataGetCarroStatus;
import Data.DataUpdateCarro;
import Data.DataUpdateCarroResult;
import Data.TipoElemento;
import Logging.ClassLogger;
import Logging.LogLevel;
import java.awt.Point;
import java.util.Random;
import Mundo.*;

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
        ThreadSleep(estado.actualizacionMs);
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
    
        
    private void ThreadSleep (int timeoutMS)
    {
        try
        {
            Thread.sleep(timeoutMS);
        }
        catch (InterruptedException ex)
        {
            ClassLogger.LogMsg(ex.getMessage(), LogLevel.ERROR);
        }
        
    }
}


