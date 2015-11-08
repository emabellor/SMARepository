/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mundo;

import BESA.ExceptionBESA;
import BESA.Kernell.Agent.AgentBESA;
import BESA.Kernell.Agent.Event.EventBESA;
import BESA.Kernell.Agent.GuardBESA;
import BESA.Kernell.Agent.StateBESA;
import BESA.Kernell.System.Directory.AgHandlerBESA;
import BESA.Log.ReportBESA;
import Carro.EstadoCarro;
import Carro.GuardaGetCarroStatusResult;
import Carro.GuardaIngresarCarroResult;
import Carro.GuardaUpdateCarroResult;
import Data.ClassElemento;
import Data.ClassObjetoCarro;
import Data.DataGetCarroStatus;
import Data.DataGetCarroStatusResult;
import Data.DataIngresarCarro;
import Data.DataIngresarCarroResult;
import Data.TipoElemento;
import java.awt.Point;

/**
 *
 * @author Mauricio
 */
public class GuardaGetCarroStatus extends GuardBESA
{
    @Override
    public boolean funcEvalBool(StateBESA objEvalBool) 
    {
        return true;
    }
    
    @Override
    public void funcExecGuard(EventBESA ebesa) 
    {
        DataGetCarroStatus data = (DataGetCarroStatus)ebesa.getData();
        AgentBESA agent = this.getAgent();
        EstadoPiso estado = (EstadoPiso)agent.getState();
        String sender = data.sender;
        
        DataGetCarroStatusResult statusResult = new DataGetCarroStatusResult();
        statusResult.sender= agent.getAlias();
        
        estado.ObtenerEstadoVehiculo(sender, statusResult);
        EventBESA event = new EventBESA(GuardaGetCarroStatusResult.class.getName(), statusResult);
        try
        {
            AgHandlerBESA ah = agent.getAdmLocal().getHandlerByAlias(sender);
            ah.sendEvent(event);
        }
        catch (ExceptionBESA e)
        {
             ReportBESA.error(e);
        }
    }
    
}
