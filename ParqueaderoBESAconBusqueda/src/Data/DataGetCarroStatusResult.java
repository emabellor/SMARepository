/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import BESA.Kernell.Agent.Event.DataBESA;
import Carro.EstadoCarro;

/**
 *
 * @author Mauricio
 */
public class DataGetCarroStatusResult extends DataBESA
{
    public String sender;
    
    public TipoElemento tipoBahia;
    public boolean estadoBahiaIzquierda;
    public boolean estadoBahiaDerecha;
    public boolean estadoBahiaFrontal;
    public boolean estadoBahiaTrasera;
    
    public EstadoCarro.Orientacion direccionCalle1;
    public EstadoCarro.Orientacion direccionCalle2;
}
