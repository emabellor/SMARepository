/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reservas;

import BESA.Kernell.Agent.*;
import Carro.EstadoCarro;
import Data.*;
import Logging.*;
import java.awt.*;
import java.util.*;

/**
 *
 * @author Mauricio
 */
public class AgenteReservas extends AgentBESA
{
    public AgenteReservas(String alias, StateBESA state, StructBESA structAgent, double passwd) throws KernellAgentExceptionBESA 
    {
        super(alias, state, structAgent, passwd);
    }
    
    @Override
    public void setupAgent() 
    {
        ClassLogger.LogMsg("SETUP AGENT -> " + getAlias());
    }

    @Override
    public void shutdownAgent() 
    {
        ClassLogger.LogMsg("SHUTDOWN AGENT -> " + getAlias());
    }
}
