/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Carro;

import BESA.ExceptionBESA;
import BESA.Kernell.Agent.*;
import BESA.Kernell.Agent.Event.*;
import BESA.Kernell.Agent.KernellAgentExceptionBESA;
import BESA.Kernell.System.Directory.AgHandlerBESA;
import Data.*;
import Logging.*;
import Mundo.*;
import Reservas.GuardaSolicitarReserva;
import java.awt.*;

/**
 *
 * @author Mauricio
 */
public class AgenteCarro extends AgentBESA
{
    public AgenteCarro(String alias, StateBESA state, StructBESA structAgent, double passwd) throws KernellAgentExceptionBESA 
    {
        super(alias, state, structAgent, passwd);
    }
    
    @Override
    public void setupAgent() 
    {
        ClassLogger.LogMsg("SETUP AGENT -> " + getAlias());    
        EstadoCarro estado = (EstadoCarro)this.getState();
        estado.x = -1;
        estado.y = -1;
        estado.orientacion = EstadoCarro.Orientacion.derecha;
        
        DataIngresarCarro dataEvent = new DataIngresarCarro();
        dataEvent.sender = this.getAlias();
        EventBESA evento = new EventBESA(GuardaIngresarCarro.class.getName(), dataEvent);

        try
        {
            AgHandlerBESA ah = this.getAdmLocal().getHandlerByAlias("WORLD");
            ah.sendEvent(evento);
        }
        catch(ExceptionBESA ex)
        {
            ClassLogger.LogMsg(ex.getMessage(), LogLevel.ERROR);
        }
    }

    @Override
    public void shutdownAgent() 
    {
        ClassLogger.LogMsg("SHUTDOWN AGENT -> " + getAlias());
    }
}
