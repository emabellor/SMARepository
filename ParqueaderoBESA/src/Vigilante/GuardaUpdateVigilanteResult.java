/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vigilante;

import BESA.ExceptionBESA;
import BESA.Kernell.Agent.Event.DataBESA;
import BESA.Kernell.Agent.Event.EventBESA;
import BESA.Kernell.Agent.GuardBESA;
import BESA.Kernell.Agent.StateBESA;
import BESA.Kernell.System.Directory.AgHandlerBESA;
import BESA.Log.ReportBESA;
import Data.ClassElemento;
import Data.DataUpdateVigilanteResult;
import Data.TipoElemento;
import Peaton.EstadoPeaton;
import java.awt.Point;
import java.util.Random;

/**
 *
 * @author Mauricio
 */
public class GuardaUpdateVigilanteResult extends GuardBESA
{
    @Override
    public boolean funcEvalBool(StateBESA objEvalBool) 
    {
        return true;
    }
    
    @Override
    public void funcExecGuard(EventBESA ebesa) 
    {
        DataUpdateVigilanteResult datoEvento = (DataUpdateVigilanteResult)ebesa.getData();
        EstadoVigilante estado = (EstadoVigilante)this.getAgent().getState();
    }
}
