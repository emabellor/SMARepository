/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mundo;

import BESA.ExceptionBESA;
import BESA.Kernell.Agent.Event.*;
import BESA.Kernell.Agent.*;
import BESA.Kernell.System.Directory.AgHandlerBESA;
import BESA.Log.ReportBESA;
import Carro.*;
import Data.*;

/**
 *
 * @author Mauricio
 */
public class GuardaIngresarCarroMundoResult extends GuardBESA
{
    @Override
    public boolean funcEvalBool(StateBESA objEvalBool) 
    {
        return true;
    }
    
    @Override
    public void funcExecGuard(EventBESA ebesa) 
    {
        DataIngresarCarroResult data = (DataIngresarCarroResult)ebesa.getData();
        EstadoPiso estado = (EstadoPiso)this.getAgent().getState();
        
        
        //Si el proceso fue exitoso, removemos el agente del mapa
        if (data.resultado == true)
            estado.RemoverAgente(data.nombreAgente);
        
        DataUpdateCarroResult dataToSend = new DataUpdateCarroResult(       
            this.getAgent().getAlias(),
            data.resultado,
            data.nuevoX, 
            data.nuevoY, 
            data.nuevaOrientacion,
            data.sender //PisoActual
        );
        
        EventBESA event = new EventBESA(GuardaUpdateCarroResult.class.getName(), dataToSend);
        try
        {
            AgHandlerBESA ah = this.getAgent().getAdmLocal().getHandlerByAlias(data.nombreAgente);
            ah.sendEvent(event);
        }
        catch (ExceptionBESA e)
        {
             ReportBESA.error(e);
        }
    }
}
