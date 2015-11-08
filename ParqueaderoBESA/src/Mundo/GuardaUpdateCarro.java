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
import Carro.GuardaIngresarCarroResult;
import Carro.GuardaUpdateCarroResult;
import Data.ClassElemento;
import Data.ClassObjetoCarro;
import Data.DataUpdateCarro;
import Data.DataUpdateCarroResult;
import Data.TipoElemento;
import Logging.ClassLogger;
import java.awt.Point;

/**
 *
 * @author Mauricio
 */
public class GuardaUpdateCarro extends GuardBESA
{
    @Override
    public boolean funcEvalBool(StateBESA objEvalBool) 
    {
        return true;
    }
    
    @Override
    public void funcExecGuard(EventBESA ebesa) 
    {
        DataUpdateCarro data = (DataUpdateCarro)ebesa.getData();
        String sender = data.sender;
        AgentBESA agent = this.getAgent();
        EstadoPiso estado = (EstadoPiso)agent.getState();
        
        Point posicion = new Point();
        posicion.x = data.nuevoX;
        posicion.y = data.nuevoY;
        ClassElemento [][] estadoMundo = estado.ObtenerEstadoMapa(posicion.x, posicion.y, sender);
        
        boolean isDesocupado = true;
        //Distingue posiciones entre los carros para evitar problemas
        if (data.nuevaOrientacion == EstadoCarro.Orientacion.arriba || data.nuevaOrientacion == EstadoCarro.Orientacion.abajo)
        {
            for (int i = 1; i < 3; i++)
            {
                for (int j = 0; j < 4; j++)
                {
                    if (estadoMundo[i][j].tipo != TipoElemento.Calle && estadoMundo[i][j].tipo != TipoElemento.Bahia)
                    {
                        isDesocupado = false;
                    }
                }
            }
        }
        else
        {
            for (int i = 0; i < 4; i++)
            {
                for (int j = 1; j < 3; j++)
                {
                    if (estadoMundo[i][j].tipo != TipoElemento.Calle && estadoMundo[i][j].tipo != TipoElemento.Bahia)
                    {
                        isDesocupado = false;
                    }
                }
            }
        }
        

       
        if (isDesocupado)
        {
            ClassObjetoCarro objCarro = new ClassObjetoCarro();
            objCarro.orientacion = data.nuevaOrientacion;
            boolean result = estado.ActualizarAgente (data.sender, posicion, objCarro);
        
            if (result == false)
            {
                ClassLogger.LogMsg("No se encuentra el nombre del agente en la lista: " + sender);
                isDesocupado = false;
            }
        }
        
        DataUpdateCarroResult dataEvent = new DataUpdateCarroResult();
        dataEvent.sender = agent.getAlias();
        dataEvent.resultado = isDesocupado;
        dataEvent.nuevoX = data.nuevoX;
        dataEvent.nuevoY = data.nuevoY;
        dataEvent.nuevaOrientacion = data.nuevaOrientacion;
        
        
        EventBESA event = new EventBESA(GuardaUpdateCarroResult.class.getName(), dataEvent);
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
