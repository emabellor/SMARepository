/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mundo;

import BESA.Kernell.Agent.KernellAgentExceptionBESA;
import BESA.Kernell.Agent.*;
import Logging.*;

/**
 *
 * @author Mauricio
 */
public class AgentePiso extends AgentBESA
{
    public AgentePiso(String alias, StateBESA state, StructBESA structAgent, double passwd) throws KernellAgentExceptionBESA 
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
