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
import Carro.*;
import Data.*;
import java.awt.Point;

/**
 *
 * @author Mauricio
 */
public class GuardaIngresarCarro extends GuardBESA
{
    @Override
    public boolean funcEvalBool(StateBESA objEvalBool) 
    {
        return true;
    }
    
    @Override
    public void funcExecGuard(EventBESA ebesa) 
    {
        DataIngresarCarro data = (DataIngresarCarro)ebesa.getData();
        AgentBESA agent = this.getAgent();
        EstadoPiso estado = (EstadoPiso)agent.getState();
        String sender = data.sender;
        
        Point posicionInicial = EstadoPiso.puntoEntrada;
        ClassElemento [][] estadoMundo = estado.ObtenerEstadoMapa(posicionInicial.x, posicionInicial.y);
        
        boolean isDesocupado = true;
        long currentTimeMillis = System.currentTimeMillis();
        
        if (currentTimeMillis - estado.ultimaAparicionCarro < estado.tiempoAparicionCarroMs)
        {
            isDesocupado = false;
        }
        else
        {
            for (int i = 0; i < 4; i++)
            {
                for (int j = 0; j < 4; j++)
                {
                    if (estadoMundo[i][j].tipo != TipoElemento.Calle)
                    {
                        isDesocupado = false;
                    }
                }
            }
        }

        
       
        if (isDesocupado)
        {
            ClassObjetoCarro objCarro = new ClassObjetoCarro();
            objCarro.orientacion = EstadoCarro.Orientacion.derecha;
            ClassElemento nuevoCarro = new ClassElemento(posicionInicial.x, posicionInicial.y, sender, TipoElemento.Auto, objCarro);
            estado.AdicionarAgente(nuevoCarro);
            estado.ultimaAparicionCarro = currentTimeMillis;
        }
        
        
        DataIngresarCarroResult dataEvent = new DataIngresarCarroResult();
        dataEvent.nuevoX = posicionInicial.x;
        dataEvent.nuevoY = posicionInicial.y;
        dataEvent.nuevaOrientacion = EstadoCarro.Orientacion.derecha;
        dataEvent.resultado = isDesocupado;
    
        
        EventBESA event = new EventBESA(GuardaIngresarCarroResult.class.getName(), dataEvent);
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
