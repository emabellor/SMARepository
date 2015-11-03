/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Carro.EstadoCarro;

/**
 *
 * @author Mauricio
 */
public class ClassObjetoCalle 
{   
    public EstadoCarro.Orientacion direccionCalle1;
    public EstadoCarro.Orientacion direccionCalle2;
    
    public ClassObjetoCalle (EstadoCarro.Orientacion dirCalle1)
    {
        direccionCalle1 = dirCalle1;
        direccionCalle2 = EstadoCarro.Orientacion.ninguno;
    }
    
    public ClassObjetoCalle (EstadoCarro.Orientacion dirCalle1, EstadoCarro.Orientacion dirCalle2)
    {
        direccionCalle1 = dirCalle1;
        direccionCalle2 = dirCalle2;
    }
}
