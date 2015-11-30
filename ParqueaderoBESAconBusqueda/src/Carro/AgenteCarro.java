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
import java.util.*;

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
        
        
        List<EstadoCarro.Orientacion> listaMovimientos = new ArrayList<>();
        listaMovimientos.add(EstadoCarro.Orientacion.derecha);
        listaMovimientos.add(EstadoCarro.Orientacion.derecha);
        listaMovimientos.add(EstadoCarro.Orientacion.derecha);
        listaMovimientos.add(EstadoCarro.Orientacion.arriba);
        listaMovimientos.add(EstadoCarro.Orientacion.arriba);
        listaMovimientos.add(EstadoCarro.Orientacion.arriba);
        listaMovimientos.add(EstadoCarro.Orientacion.arriba);
        listaMovimientos.add(EstadoCarro.Orientacion.arriba);
        listaMovimientos.add(EstadoCarro.Orientacion.arriba);
        listaMovimientos.add(EstadoCarro.Orientacion.arriba);
        listaMovimientos.add(EstadoCarro.Orientacion.arriba);
        listaMovimientos.add(EstadoCarro.Orientacion.arriba);
        listaMovimientos.add(EstadoCarro.Orientacion.izquierda);
        listaMovimientos.add(EstadoCarro.Orientacion.izquierda);
        listaMovimientos.add(EstadoCarro.Orientacion.izquierda);
        listaMovimientos.add(EstadoCarro.Orientacion.izquierda);
        listaMovimientos.add(EstadoCarro.Orientacion.derecha);
        listaMovimientos.add(EstadoCarro.Orientacion.derecha);
        listaMovimientos.add(EstadoCarro.Orientacion.abajo);
        estado.ActualizarListaMovimientos(listaMovimientos);

                
        DataIngresarCarro dataEvent = new DataIngresarCarro(this.getAlias(), AgenteCarro.class, this.getAlias(), EstadoPiso.puntoEntradaPisoAbajo);
        EventBESA evento = new EventBESA(GuardaIngresarCarro.class.getName(), dataEvent);

        try
        {
            AgHandlerBESA ah = this.getAdmLocal().getHandlerByAlias(estado.pisoActual);
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
