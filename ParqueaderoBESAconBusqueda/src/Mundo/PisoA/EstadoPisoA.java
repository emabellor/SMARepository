    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mundo.PisoA;

import Carro.EstadoCarro;
import Data.ClassElemento;
import Data.ClassObjetoBahia;
import Data.ClassObjetoCalle;
import Data.TipoElemento;
import Mundo.EstadoPiso;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DANTE STERPIN
 */
public class EstadoPisoA extends EstadoPiso
{
    public EstadoPisoA(int _size)
    {
        super(_size);
        ModificarCasillas();
    }
    
    private void ModificarCasillas()
    {
        ClassObjetoCalle objCalleDerecha = new ClassObjetoCalle(EstadoCarro.Orientacion.derecha);
        ClassObjetoCalle objCalleIzquierda = new ClassObjetoCalle(EstadoCarro.Orientacion.izquierda);
        
        
        
        //Cambio de casillas
        CambiarCasilla(0, 2, TipoElemento.Calle, objCalleIzquierda);
        CambiarCasilla(1, 2, TipoElemento.Calle, objCalleIzquierda);
        CambiarCasilla(2, 2, TipoElemento.Calle, new ClassObjetoCalle(EstadoCarro.Orientacion.abajo, EstadoCarro.Orientacion.izquierda));
        
        CambiarCasilla(0, 3, TipoElemento.Calle, objCalleDerecha);
        CambiarCasilla(1, 3, TipoElemento.Calle, objCalleDerecha);
        CambiarCasilla(2, 3, TipoElemento.Calle, new ClassObjetoCalle(EstadoCarro.Orientacion.abajo, EstadoCarro.Orientacion.derecha));
    }
}
