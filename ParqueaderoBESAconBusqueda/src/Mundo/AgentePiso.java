/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mundo;

import BESA.Kernell.Agent.KernellAgentExceptionBESA;
import BESA.Kernell.Agent.*;
import Carro.EstadoCarro;
import Data.ClassElemento;
import Data.TipoElemento;
import Logging.*;

/**
 *
 * @author Mauricio
 */
public class AgentePiso extends AgentBESA
{
    public AgentePiso(String alias, StateBESA state, StructBESA structAgent, double passwd) throws KernellAgentExceptionBESA 
    {
        super(alias, state, structAgent, passwd);
    }
    
    @Override
    public void setupAgent() 
    {
        ClassLogger.LogMsg("SETUP AGENT -> " + getAlias());
    }

    @Override
    public void shutdownAgent() 
    {
        ClassLogger.LogMsg("SHUTDOWN AGENT -> " + getAlias());
    }
    
    
    public static boolean EsCasillaDesocupada(EstadoCarro.Orientacion orientacion, ClassElemento[][] estadoMundo)
    {
        boolean isDesocupado = true;
        
        //Distingue posiciones entre los carros para evitar problemas
        if (orientacion == EstadoCarro.Orientacion.arriba || orientacion == EstadoCarro.Orientacion.abajo)
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
        
        return isDesocupado;
    }
    
}
