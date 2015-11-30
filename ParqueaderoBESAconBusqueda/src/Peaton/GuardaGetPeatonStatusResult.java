/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Peaton;

import BESA.ExceptionBESA;
import BESA.Kernell.Agent.*;
import BESA.Kernell.Agent.Event.EventBESA;
import BESA.Kernell.System.Directory.AgHandlerBESA;
import BESA.Log.ReportBESA;
import Data.*;
import Mundo.*;
import java.awt.Point;

/**
 *
 * @author Mauricio
 */
public class GuardaGetPeatonStatusResult extends GuardBESA
{
    @Override
    public boolean funcEvalBool(StateBESA objEvalBool) 
    {
        return true;
    }
    
    @Override
    public void funcExecGuard(EventBESA ebesa) 
    {
        DataGetPeatonStatusResult evArgs = (DataGetPeatonStatusResult)ebesa.getData();
        AgentBESA agente = this.getAgent();
        EstadoPeaton estado = (EstadoPeaton)agente.getState();
        String sender = evArgs.sender;

        
        DataUpdatePeaton updatePeaton = new DataUpdatePeaton();
        updatePeaton.sender = agente.getAlias();
        updatePeaton.posX = estado.posX;
        updatePeaton.posY = estado.posY;

        Point puntoReferencia = new Point();
        if (estado.volver == false)
            puntoReferencia = EstadoPiso.puntoSalidaPeatones;
        else
            puntoReferencia = new Point(estado.posVehiculoX, estado.posVehiculoY);
        
        int deltaX =  puntoReferencia.x - estado.posX;
        int deltaY = puntoReferencia.y - estado.posY;

        if (deltaX > 0)
            deltaX = 1;
        if (deltaX < 0)
            deltaX = -1;

        if (deltaY > 0)
            deltaY = 1;
        if (deltaY < 0)
            deltaY = -1;

        int nuevoDeltaX = deltaX + 1;
        int nuevoDeltaY = deltaY + 1;
        boolean encontrado = false;

        if (EspacioValido(evArgs, nuevoDeltaX, nuevoDeltaY) == true)
        {
            //Encuentra
            encontrado = true;
        }
        else
        {
            //Evalua posibilidad movimientos parciales hacia el objetivo
            if (deltaX != 0 && deltaY != 0)
            {
                nuevoDeltaX = 1;
                nuevoDeltaY = deltaY + 1;
                if (EspacioValido (evArgs, nuevoDeltaX, nuevoDeltaY) == true)
                {
                    deltaX = 0;
                    encontrado = true;
                }
                else
                {
                    nuevoDeltaX = deltaX + 1;
                    nuevoDeltaY = 1;
                    if (EspacioValido(evArgs, nuevoDeltaX, nuevoDeltaY) == true)
                    {
                        deltaY = 0;
                        encontrado = true;
                    }
                }
            }
        }    

        if (encontrado)
        {
            updatePeaton.posX += deltaX;
            updatePeaton.posY += deltaY;
        }
        else 
        {
            //Selecciona la primera posicion desocupada que encuentre en la lista!
            for (int i = 0; i < 3; i++)
            {
                for (int j = 0; j < 3; j++)
                {
                    if (i == 1 && j == 1)
                        continue;

                    if (EspacioValido(evArgs, i, j) == true)
                    {
                        updatePeaton.posX += (i - 1);
                        updatePeaton.posY += (j - 1);
                        encontrado = true;
                        break;
                    }
                }
                
                if (encontrado)
                    break;
            }
        }

        //Envia el evento de actualizacion de espacio
        EventBESA event = new EventBESA(GuardaUpdatePeaton.class.getName(), updatePeaton);
        try
        {
            AgHandlerBESA ah = agent.getAdmLocal().getHandlerByAlias(estado.planoActual);
            ah.sendEvent(event);
        }
        catch (ExceptionBESA e)
        {
             ReportBESA.error(e);
        }

    }
    
    private boolean EspacioValido(DataGetPeatonStatusResult evArgs, int deltaX, int deltaY)
    {
        if (TipoElemento.IsAgent(evArgs.estadoAgente[deltaX][deltaY].tipo) == false && evArgs.estadoAgente[deltaX][deltaY].tipo != TipoElemento.Ninguno)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
