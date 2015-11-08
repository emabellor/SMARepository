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
import Carro.GuardaUpdateCarroResult;
import Data.ClassElemento;
import Data.ClassObjetoCarro;
import Data.DataGetPeatonStatus;
import Data.DataUpdateCarroResult;
import Data.DataUpdatePeaton;
import Data.DataUpdatePeatonResult;
import Data.TipoElemento;
import Logging.ClassLogger;
import Peaton.GuardaUpdatePeatonResult;
import java.awt.Point;

/**
 *
 * @author Mauricio
 */
public class GuardaUpdatePeaton extends GuardBESA
{
    @Override
    public boolean funcEvalBool(StateBESA objEvalBool) 
    {
        return true;
    }
    
    @Override
    public void funcExecGuard(EventBESA ebesa) 
    {
        DataUpdatePeaton data = (DataUpdatePeaton)ebesa.getData();
        AgentBESA agent = this.getAgent();
        EstadoPiso estado = (EstadoPiso)agent.getState();
        String sender = data.sender;
        
        Point posicion = new Point();
        posicion.x = data.posX;
        posicion.y = data.posY;
        ClassElemento estadoCasilla = estado.ObtenerEstadoCasilla(posicion.x, posicion.y, sender);
        
        boolean isDesocupado = false;
        if (TipoElemento.IsAgent(estadoCasilla.tipo) == false && estadoCasilla.tipo != TipoElemento.Ninguno)
            isDesocupado = true;
        
        if (isDesocupado)
        {
            boolean result = estado.ActualizarAgente (data.sender, posicion, null);
            if (result == false)
            {
                ClassLogger.LogMsg("No se encuentra el nombre del agente en la lista: " + sender);
                isDesocupado = false;
            }
        }
        
        DataUpdatePeatonResult dataEvent = new DataUpdatePeatonResult();
        dataEvent.sender = agent.getAlias();
        dataEvent.resultado = isDesocupado;
        dataEvent.posX = posicion.x;
        dataEvent.posY = posicion.y;
        
        
        EventBESA event = new EventBESA(GuardaUpdatePeatonResult.class.getName(), dataEvent);
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
