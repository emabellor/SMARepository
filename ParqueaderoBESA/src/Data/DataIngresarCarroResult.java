/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import BESA.Kernell.Agent.Event.DataBESA;
import Carro.*;

/**
 *
 * @author Mauricio
 */
public class DataIngresarCarroResult extends DataBESA
{
    public String sender;
    public boolean resultado;
    public int nuevoX;
    public int nuevoY;
    public EstadoCarro.Orientacion nuevaOrientacion;
}
