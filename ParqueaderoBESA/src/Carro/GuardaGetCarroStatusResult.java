/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Carro;

import BESA.ExceptionBESA;
import BESA.Kernell.Agent.AgentBESA;
import BESA.Kernell.Agent.Event.EventBESA;
import BESA.Kernell.Agent.GuardBESA;
import BESA.Kernell.Agent.StateBESA;
import BESA.Kernell.System.Directory.AgHandlerBESA;
import Data.ClassObjetoCalle;
import Data.DataGetCarroStatus;
import Data.DataGetCarroStatusResult;
import Data.DataIngresarCarroResult;
import Data.DataRemoverCarro;
import Data.DataUpdateCarro;
import Data.DataWakeUpPeaton;
import Data.TipoElemento;
import Logging.ClassLogger;
import Logging.LogLevel;
import Mundo.EstadoPiso;
import Mundo.GuardaGetCarroStatus;
import Mundo.GuardaRemoverCarro;
import Mundo.GuardaUpdateCarro;
import Peaton.GuardaWakeUpPeaton;
import java.util.Random;

/**
 *
 * @author Mauricio
 */
public class GuardaGetCarroStatusResult extends GuardBESA
{
    @Override
    public boolean funcEvalBool(StateBESA objEvalBool) 
    {
        return true;
    }
    
    @Override
    public void funcExecGuard(EventBESA ebesa) 
    {
        //Solicita status de orientacion
        DataGetCarroStatusResult evArgs = (DataGetCarroStatusResult)ebesa.getData();
        AgentBESA agente = this.getAgent();
        EstadoCarro estado = (EstadoCarro)agente.getState();
        

        if (estado.saliendo == false)
        {
            if (evArgs.tipoBahia == TipoElemento.Bahia)
            {
                ClassLogger.LogMsg("Carro parqueado");
                
                int index = 0;
                for(String ocupante : estado.listaOcupantes)
                {
                    int nuevoX = estado.x;
                    int nuevoY = estado.y;
                
                    if (estado.orientacion == EstadoCarro.Orientacion.derecha || estado.orientacion == EstadoCarro.Orientacion.izquierda)
                    {
                        if (index == 0)
                        {
                            nuevoX += 1;
                        }
                        else if (index == 1)
                        {
                            nuevoX += 2;
                        }
                        else if (index == 2)
                        {
                            nuevoY += 3;
                            nuevoX += 1;
                        }
                        else
                        {
                            nuevoY += 3;
                            nuevoX += 2;
                        }
                    }
                    else
                    {
                        if (index == 0)
                        {
                            nuevoY += 1;
                        }
                        else if (index == 1)
                        {
                            nuevoY += 2;
                        }
                        else if (index == 2)
                        {
                            nuevoX += 3;
                            nuevoY += 1;
                        }
                        else
                        {
                            nuevoX += 3;
                            nuevoY += 2;
                        }
                    }
                    
                        
                    DataWakeUpPeaton wakeUpData = new DataWakeUpPeaton();
                    wakeUpData.posVehiculoX = estado.x;
                    wakeUpData.posVehiculoY = estado.y;
                    wakeUpData.posX = nuevoX;
                    wakeUpData.posY = nuevoY;
                    wakeUpData.sender = agente.getAlias();
                    
                    EventBESA event = new EventBESA(GuardaWakeUpPeaton.class.getName(), wakeUpData);
                    try
                    {
                        AgHandlerBESA ah = agent.getAdmLocal().getHandlerByAlias(ocupante);
                        ah.sendEvent(event);
                    }
                    catch(ExceptionBESA ex)
                    {
                        ClassLogger.LogMsg(ex.getMessage(), LogLevel.ERROR);
                    }
                    
                    index++;
                }
            }
            else
            {
                ProcesarEntradaCarro(evArgs);   
            }
        }
        else
        {            
            if (estado.x == EstadoPiso.puntoSalida.x && estado.y == EstadoPiso.puntoSalida.y)
            {
                EnviarEventoSalida(evArgs);
            }
            else
            {
                MoverCarroSalida(evArgs);
            }
        }
    }
    
    private void EnviarEventoSalida(DataGetCarroStatusResult evArgs)
    {
        AgentBESA agente = this.getAgent();
        DataRemoverCarro removerCarro = new DataRemoverCarro();
        removerCarro.sender = agente.getAlias();
        
        EventBESA event = new EventBESA(GuardaRemoverCarro.class.getName(), removerCarro);
        try
        {
            AgHandlerBESA ah = agent.getAdmLocal().getHandlerByAlias("WORLD");
            ah.sendEvent(event);
        }
        catch(ExceptionBESA ex)
        {
            ClassLogger.LogMsg(ex.getMessage(), LogLevel.ERROR);
        }
    }
    
    private void MoverCarroSalida(DataGetCarroStatusResult evArgs)
    {
        AgentBESA agente = this.getAgent();
        EstadoCarro estado = (EstadoCarro)agente.getState();
        
        DataUpdateCarro updateCarro = new DataUpdateCarro();
        updateCarro.nuevoX = estado.x;
        updateCarro.nuevoY = estado.y;
        updateCarro.nuevaOrientacion = estado.orientacion;
        updateCarro.sender = agente.getAlias();
        
        //Monta la direccion 1
        if (evArgs.direccionCalle1 != EstadoCarro.Orientacion.ninguno)
            updateCarro.nuevaOrientacion = evArgs.direccionCalle1;
        
        //Continua deambulando!
        if (updateCarro.nuevaOrientacion == EstadoCarro.Orientacion.arriba)
            updateCarro.nuevoY--;
        else if (updateCarro.nuevaOrientacion == EstadoCarro.Orientacion.abajo)
            updateCarro.nuevoY++;
        else if (updateCarro.nuevaOrientacion == EstadoCarro.Orientacion.izquierda)
            updateCarro.nuevoX--;
        else
            updateCarro.nuevoX++;


        EventBESA event = new EventBESA(GuardaUpdateCarro.class.getName(), updateCarro);
        try
        {
            AgHandlerBESA ah = agent.getAdmLocal().getHandlerByAlias("WORLD");
            ah.sendEvent(event);
        }
        catch(ExceptionBESA ex)
        {
            ClassLogger.LogMsg(ex.getMessage(), LogLevel.ERROR);
        }
    }
    
    private void ProcesarEntradaCarro(DataGetCarroStatusResult evArgs)
    {        
        AgentBESA agente = this.getAgent();
        EstadoCarro estado = (EstadoCarro)agente.getState();
        
        DataUpdateCarro updateCarro = new DataUpdateCarro();
        updateCarro.nuevoX = estado.x;
        updateCarro.nuevoY = estado.y;
        updateCarro.nuevaOrientacion = estado.orientacion;
        updateCarro.sender = agente.getAlias();

        
        if (evArgs.estadoBahiaDerecha == true)
        {
            updateCarro.nuevaOrientacion = ObtenerOrientacionDerecha(estado.orientacion);
        }
        else if (evArgs.estadoBahiaIzquierda == true)
        {
            updateCarro.nuevaOrientacion = ObtenerOrientacionIzquierda(estado.orientacion);
        }
        else if (evArgs.estadoBahiaFrontal == true)
        {
            updateCarro.nuevaOrientacion = ObtenerOrientacionArriba(estado.orientacion);
        }
        else if (evArgs.estadoBahiaTrasera == true)
        {
            updateCarro.nuevaOrientacion = ObtenerOrientacionAbajo(estado.orientacion);
        }
        else if (evArgs.direccionCalle1 != EstadoCarro.Orientacion.ninguno && evArgs.direccionCalle2 == EstadoCarro.Orientacion.ninguno)
        {
            updateCarro.nuevaOrientacion = evArgs.direccionCalle1;
        }
        else if (evArgs.direccionCalle1 != EstadoCarro.Orientacion.ninguno && evArgs.direccionCalle2 != EstadoCarro.Orientacion.ninguno)
        {
            if (estado.continuarDireccion == false)
            {
                //Escoge una orientacion random entre los 2
                Random rnd = new Random();
                int index = rnd.nextInt(2);
                
                if (index == 0)
                {
                    updateCarro.nuevaOrientacion = evArgs.direccionCalle1;
                }
                else
                {
                    updateCarro.nuevaOrientacion = evArgs.direccionCalle2;
                }
                
                estado.continuarDireccion = true;
            }
        }
        
        if (evArgs.direccionCalle1 != EstadoCarro.Orientacion.ninguno ^ evArgs.direccionCalle2 != EstadoCarro.Orientacion.ninguno)
            estado.continuarDireccion = false;  //Vuelve a bahia de un solo sentido, evalua nuevamente direccion

        //Continua deambulando!
        if (updateCarro.nuevaOrientacion == EstadoCarro.Orientacion.arriba)
            updateCarro.nuevoY--;
        else if (updateCarro.nuevaOrientacion == EstadoCarro.Orientacion.abajo)
            updateCarro.nuevoY++;
        else if (updateCarro.nuevaOrientacion == EstadoCarro.Orientacion.izquierda)
            updateCarro.nuevoX--;
        else
            updateCarro.nuevoX++;


        EventBESA event = new EventBESA(GuardaUpdateCarro.class.getName(), updateCarro);
        try
        {
            AgHandlerBESA ah = agent.getAdmLocal().getHandlerByAlias("WORLD");
            ah.sendEvent(event);
        }
        catch(ExceptionBESA ex)
        {
            ClassLogger.LogMsg(ex.getMessage(), LogLevel.ERROR);
        }
    }
    
    
    private EstadoCarro.Orientacion ObtenerOrientacionDerecha(EstadoCarro.Orientacion orientacion)
    {
        switch (orientacion)
        {
            case abajo:
                return orientacion.izquierda;
            case izquierda:
                return orientacion.arriba;
            case arriba:
                return orientacion.derecha;
            default:
                return orientacion.abajo;
        }
    }
    
    private EstadoCarro.Orientacion ObtenerOrientacionIzquierda(EstadoCarro.Orientacion orientacion)
    {
        switch (orientacion)
        {
            case abajo:
                return orientacion.derecha;
            case derecha:
                return orientacion.arriba;
            case arriba:
                return orientacion.izquierda;
            default:
                return orientacion.abajo;
        }
    }
    
    
    private EstadoCarro.Orientacion ObtenerOrientacionArriba(EstadoCarro.Orientacion orientacion)
    {
        return orientacion;
    }
    
    private EstadoCarro.Orientacion ObtenerOrientacionAbajo(EstadoCarro.Orientacion orientacion)
    {
        switch (orientacion)
        {
            case abajo:
                return orientacion.arriba;
            case derecha:
                return orientacion.izquierda;
            case arriba:
                return orientacion.abajo;
            default:
                return orientacion.arriba;
        }
    }
}
