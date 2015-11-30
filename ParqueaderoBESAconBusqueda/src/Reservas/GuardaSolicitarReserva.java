/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reservas;

import BESA.ExceptionBESA;
import BESA.Kernell.Agent.Event.EventBESA;
import BESA.Kernell.Agent.*;
import BESA.Kernell.System.Directory.AgHandlerBESA;
import Data.*;
import Logging.*;
import Mundo.*;

/**
 *
 * @author Mauricio
 */
public class GuardaSolicitarReserva extends GuardBESA
{
    @Override
    public boolean funcEvalBool(StateBESA objEvalBool) 
    {
        return true;
    }
    
    @Override
    public void funcExecGuard(EventBESA ebesa) 
    {
        DataSolicitarReserva data = (DataSolicitarReserva)ebesa.getData();
        ClassLogger.LogMsg("Generando solicitud de reservas agente " + data.sender);
        
        DataGetMundoStatus dataToSend = new DataGetMundoStatus(this.getAgent().getAlias(), this.getAgent().getClass(), data.sender, data.posicionActual);
        
        EventBESA event = new EventBESA(GuardaGetMundoStatus.class.getName(), dataToSend);
        try
        {
            AgHandlerBESA ah = this.getAgent().getAdmLocal().getHandlerByAlias("WORLD");
            ah.sendEvent(event);
        }
        catch(ExceptionBESA ex)
        {
            ClassLogger.LogMsg(ex.getMessage(), LogLevel.ERROR);
        } 
    }    
}
