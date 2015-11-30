/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vigilante;

import BESA.Kernell.Agent.Event.*;
import BESA.Kernell.Agent.*;
import Data.*;

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

    }
}
