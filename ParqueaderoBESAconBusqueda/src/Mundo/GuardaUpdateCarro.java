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
import Logging.*;
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
        Point posicion = new Point();
        posicion.x = data.nuevoX;
        posicion.y = data.nuevoY;
        EstadoPiso estado = (EstadoPiso)this.getAgent().getState();

        if (posicion.x == EstadoPiso.puntoSalidaPisoArriba.x && posicion.y == EstadoPiso.puntoSalidaPisoArriba.y)
        {
            int numeroPiso = estado.ObtenerNumeroPiso(this.getAgent().getAlias());
            String nombreNuevoPiso = estado.ObtenerNombrePiso(numeroPiso + 1);
            SolicitarIngresoNuevoPiso(data.sender, nombreNuevoPiso, EstadoPiso.puntoEntradaPisoAbajo);
        }
        else if (posicion.x == EstadoPiso.puntoSalidaPisoAbajo.x && posicion.y == EstadoPiso.puntoSalidaPisoAbajo.y)
        {
            int numeroPiso = estado.ObtenerNumeroPiso(this.getAgent().getAlias());
            String nombreNuevoPiso = estado.ObtenerNombrePiso(numeroPiso - 1);
            SolicitarIngresoNuevoPiso(data.sender, nombreNuevoPiso, EstadoPiso.puntoEntradaPisoArriba);
        }
        else
        {
            ActualizarEstado(ebesa);
        }
    }
    
    private void SolicitarIngresoNuevoPiso(String nombreAgente, String nombrePiso, Point puntoIngreso)
    {
        String sender = this.getAgent().getAlias();
        Class type = this.getAgent().getClass();

        DataIngresarCarro dataToSend = new DataIngresarCarro(sender, type, nombreAgente, puntoIngreso);
        EventBESA event = new EventBESA(GuardaIngresarCarro.class.getName(), dataToSend);
        try
        {
            AgHandlerBESA ah = this.getAgent().getAdmLocal().getHandlerByAlias(nombrePiso);
            ah.sendEvent(event);
        }
        catch (ExceptionBESA e)
        {
             ReportBESA.error(e);
        }
    }
    
    
    private void ActualizarEstado(EventBESA ebesa)
    {
        DataUpdateCarro data = (DataUpdateCarro)ebesa.getData();
        Point posicion = new Point();
        posicion.x = data.nuevoX;
        posicion.y = data.nuevoY;
        EstadoPiso estado = (EstadoPiso)this.getAgent().getState();
        ClassElemento [][] estadoMundo = estado.ObtenerEstadoMapa(posicion.x, posicion.y, data.sender);
        
        
        boolean isDesocupado = AgentePiso.EsCasillaDesocupada(data.nuevaOrientacion, estadoMundo);
        if (isDesocupado)
        {
            ClassObjetoCarro objCarro = new ClassObjetoCarro();
            objCarro.orientacion = data.nuevaOrientacion;
            boolean result = estado.ActualizarAgente (data.sender, posicion, objCarro);
        
            if (result == false)
            {
                ClassLogger.LogMsg("No se encuentra el nombre del agente en la lista: " + data.sender);
                isDesocupado = false;
            }
        }
        
        DataUpdateCarroResult dataEvent = new DataUpdateCarroResult(
                this.getAgent().getAlias(), 
                isDesocupado, 
                data.nuevoX, 
                data.nuevoY,
                data.nuevaOrientacion, 
                this.getAgent().getAlias());        
        
        EventBESA event = new EventBESA(GuardaUpdateCarroResult.class.getName(), dataEvent);
        try
        {
            AgHandlerBESA ah = this.getAgent().getAdmLocal().getHandlerByAlias(data.sender);
            ah.sendEvent(event);
        }
        catch (ExceptionBESA e)
        {
             ReportBESA.error(e);
        }
    }
}
