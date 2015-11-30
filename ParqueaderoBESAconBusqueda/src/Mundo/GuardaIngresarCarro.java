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
        AgentBESA agente = this.getAgent();
        EstadoPiso estado = (EstadoPiso)agent.getState();
        
        Point posicionInicial = data.puntoIngreso;
        ClassElemento [][] estadoMundo = estado.ObtenerEstadoMapa(posicionInicial.x, posicionInicial.y);
        EstadoCarro.Orientacion orientacionIngreso = EstadoCarro.Orientacion.derecha;
        
        boolean isDesocupado;
        long currentTimeMillis = System.currentTimeMillis();
        
        //Solo evalua frecuencia de ingreso en el agente mundo del primer piso
        if (currentTimeMillis - estado.ultimaAparicionCarro < estado.tiempoAparicionCarroMs && agente.getAlias().equals("WORLD"))
        {
            isDesocupado = false;
        }
        else
        {
            isDesocupado = AgentePiso.EsCasillaDesocupada(orientacionIngreso, estadoMundo);
        }

        
        if (isDesocupado)
        {
            ClassObjetoCarro objCarro = new ClassObjetoCarro();
            objCarro.orientacion = orientacionIngreso;
            ClassElemento nuevoCarro = new ClassElemento(posicionInicial.x, posicionInicial.y, data.nombreAgente, TipoElemento.Auto, objCarro);
            estado.AdicionarAgente(nuevoCarro);
            estado.ultimaAparicionCarro = currentTimeMillis;
        }
        
        
        DataIngresarCarroResult dataEvent = new DataIngresarCarroResult(
            agente.getAlias(),
            isDesocupado,
            posicionInicial.x,
            posicionInicial.y,
            EstadoCarro.Orientacion.derecha,
            data.nombreAgente
        );
            
        EventBESA event;
        if (data.type == AgenteCarro.class)
            event = new EventBESA(GuardaIngresarCarroResult.class.getName(), dataEvent);
        else //Agente Piso
            event = new EventBESA(GuardaIngresarCarroMundoResult.class.getName(), dataEvent);
     
        try
        {
            AgHandlerBESA ah = agente.getAdmLocal().getHandlerByAlias(data.sender);
            ah.sendEvent(event);
        }
        catch (ExceptionBESA e)
        {
             ReportBESA.error(e);
        }
    }
}
