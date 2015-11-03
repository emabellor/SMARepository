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
import Data.DataIngresarCarroResult;
import Data.DataRemoverCarro;
import Data.DataRemoverCarroResult;
import Logging.ClassLogger;
import Logging.LogLevel;
import Mundo.GuardaRemoverCarro;
import Mundo.GuardaUpdateCarro;

/**
 *
 * @author Mauricio
 */
public class GuardaRemoverCarroResult extends GuardBESA
{
    @Override
    public boolean funcEvalBool(StateBESA objEvalBool) 
    {
        return true;
    }
    
    @Override
    public void funcExecGuard(EventBESA ebesa) 
    {
        DataRemoverCarroResult evArgs = (DataRemoverCarroResult)ebesa.getData();
        AgentBESA agente = this.getAgent();
        EstadoCarro estado = (EstadoCarro)agente.getState();
        
        //TODO - Despues de que el agente se haya posicionado para ser removido, se debe enviar
        if (evArgs.result == true)
        {
            ClassLogger.LogMsg("El agente fue removido del parqueadero. Termina!", LogLevel.DEBUG);
        }
        else
        {
            //Espera un segundo y vuelve a enviar el evento
            try
            {
                Thread.sleep(estado.actualizacionMs);
            }
            catch (InterruptedException ex)
            {
                ClassLogger.LogMsg("Excepcion al momentode ejecutar el timer: " + ex.getMessage());
            }
            
               
            DataRemoverCarro removeCarro = new DataRemoverCarro();
            removeCarro.sender = agente.getAlias();
            EventBESA event = new EventBESA(GuardaRemoverCarro.class.getName(), removeCarro);
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
