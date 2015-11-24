/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mundo;

import BESA.ExceptionBESA;
import BESA.Kernell.Agent.*;
import BESA.Kernell.Agent.Event.EventBESA;
import BESA.Kernell.System.Directory.AgHandlerBESA;
import BESA.Log.ReportBESA;
import Data.*;
import Viewer.*;


/**
 *
 * @author Mauricio
 */
public class GuardaGetMundoStatus extends GuardBESA
{
    @Override
    public boolean funcEvalBool(StateBESA objEvalBool) 
    {
        return true;
    }
    
    @Override
    public void funcExecGuard(EventBESA ebesa) 
    {
        EstadoPiso estado = (EstadoPiso)this.getAgent().getState();
        
        DataGetMundoStatus dataReceived = (DataGetMundoStatus)ebesa.getData();
        
        DataGetMundoStatusResult dataToSend = new DataGetMundoStatusResult();
        dataToSend.listaElementos = estado.GetElementos();
        dataToSend.listaAgentes = estado.GetAgentes();
        
        
        EventBESA event = new EventBESA(GuardaGetMundoStatusResult.class.getName(), dataToSend);
        try
        {
            AgHandlerBESA ah = agent.getAdmLocal().getHandlerByAlias(dataReceived.sender);
            ah.sendEvent(event);
        }
        catch (ExceptionBESA e)
        {
             ReportBESA.error(e);
        }
    }
}
