/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Viewer;

import BESA.Kernell.Agent.Event.EventBESA;
import BESA.Kernell.Agent.GuardBESA;
import BESA.Kernell.Agent.StateBESA;
import Data.*;
import Logging.*;

/**
 *
 * @author Mauricio
 */
public class GuardaChangeMundoReference extends GuardBESA
{
    @Override
    public boolean funcEvalBool(StateBESA objEvalBool) 
    {
        return true;
    }
    
    @Override
    public void funcExecGuard(EventBESA ebesa) 
    {
        DataChangeMundoReference dato = (DataChangeMundoReference)ebesa.getData();
        EstadoViewer estado = (EstadoViewer)this.getAgent().getState();
        
        estado.pisoActual = dato.nuevoPisoActual;
        ClassLogger.LogMsg("Cambia numero nuevo piso agente viewer a " + dato.nuevoPisoActual);
    }
}
