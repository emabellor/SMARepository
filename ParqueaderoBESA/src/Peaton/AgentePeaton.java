/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Peaton;

import BESA.ExceptionBESA;
import BESA.Kernell.Agent.AgentBESA;
import BESA.Kernell.Agent.Event.DataBESA;
import BESA.Kernell.Agent.Event.EventBESA;
import BESA.Kernell.Agent.KernellAgentExceptionBESA;
import BESA.Kernell.Agent.StateBESA;
import BESA.Kernell.Agent.StructBESA;
import BESA.Kernell.System.Directory.AgHandlerBESA;
import BESA.Log.ReportBESA;
import Carro.EstadoCarro;
import Data.ClassElemento;
import Data.TipoElemento;
import Logging.ClassLogger;

/**
 *
 * @author Mauricio
 */
public class AgentePeaton extends AgentBESA
{
    public AgentePeaton(String alias, StateBESA state, StructBESA structAgent, double passwd) throws KernellAgentExceptionBESA 
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
