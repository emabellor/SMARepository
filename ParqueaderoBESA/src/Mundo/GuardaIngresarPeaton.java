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
import Carro.GuardaGetCarroStatusResult;
import Carro.GuardaIngresarCarroResult;
import Data.ClassElemento;
import Data.DataIngresarCarro;
import Data.DataIngresarPeaton;
import Data.DataIngresarPeatonResult;
import Data.TipoElemento;
import Peaton.GuardaIngresarPeatonResult;

/**
 *
 * @author Mauricio
 */
public class GuardaIngresarPeaton extends GuardBESA
{
     @Override
    public boolean funcEvalBool(StateBESA objEvalBool) 
    {
        return true;
    }
    
    @Override
    public void funcExecGuard(EventBESA ebesa) 
    {
        DataIngresarPeaton data = (DataIngresarPeaton)ebesa.getData();
        AgentBESA agent = this.getAgent();
        EstadoPiso estado = (EstadoPiso)agent.getState();
        String sender = data.sender;
        
        DataIngresarPeatonResult ingresarResult = new DataIngresarPeatonResult();
        ingresarResult.posX = data.posX;
        ingresarResult.posY = data.posY;
        ingresarResult.sender = agent.getAlias();
        
        
        if (estado.VerificarCasillaDisponiblePeaton(data.posX, data.posY, sender) == true)
        {
            ingresarResult.result = true;
            ClassElemento nuevoPeaton = new ClassElemento(data.posX, data.posY, sender, TipoElemento.Peaton, null);
            estado.AdicionarAgente(nuevoPeaton);
        }
        else
        {
            ingresarResult.result = false;
        }
        
        //Envia OK al sender
        EventBESA event = new EventBESA(GuardaIngresarPeatonResult.class.getName(), ingresarResult);
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
