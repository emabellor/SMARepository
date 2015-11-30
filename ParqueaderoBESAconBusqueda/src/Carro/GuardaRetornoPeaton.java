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
import Data.DataRetornoPeaton;
import Logging.ClassLogger;
import Logging.LogLevel;
import Mundo.GuardaGetCarroStatus;

/**
 *
 * @author Mauricio
 */
public class GuardaRetornoPeaton extends GuardBESA
{
    @Override
    public boolean funcEvalBool(StateBESA objEvalBool) 
    {
        return true;
    }
    
    @Override
    public void funcExecGuard(EventBESA ebesa) 
    {
        //Solicita status de orientacion
        DataRetornoPeaton evArgs = (DataRetornoPeaton)ebesa.getData();
        AgentBESA agente = this.getAgent();
        EstadoCarro estado = (EstadoCarro)agente.getState();
        
         
        estado.conteoRegresoOcupantes++;
        if (estado.conteoRegresoOcupantes == estado.listaOcupantes.size())
        {
            //Envia evento de regreso de ocupantes
            estado.saliendo = true;
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
        }
    }
}
