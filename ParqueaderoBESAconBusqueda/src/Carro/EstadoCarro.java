/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Carro;

import BESA.Kernell.Agent.StateBESA;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Mauricio
 */
public class EstadoCarro extends StateBESA
{   
    public enum Orientacion
    {
        arriba,
        abajo,
        izquierda,
        derecha,
        ninguno,
    }
    
    public int x;
    public int y;
    public Orientacion orientacion;
    public boolean saliendo = false;
    public List<String> listaOcupantes = new ArrayList<>();
    public int conteoRegresoOcupantes = 0;
    public String pisoActual;
    public int actualizacionMs = 50;
    public boolean continuarDireccion = false;
    
    public List<Orientacion> listaMovimientos;
    public boolean seguirListaMovimientos = false;
    public Point ultimaPosicionDecision;
    public Orientacion ultimaOrientacion;
    
    
    
    public EstadoCarro()
    {
        pisoActual = "WORLD";
        x = -1;
        y = -1;
        orientacion = EstadoCarro.Orientacion.derecha;
        listaMovimientos = new ArrayList<>();
    }
    
    public void ActualizarListaMovimientos(List<Orientacion> nuevaLista)
    {
        listaMovimientos = nuevaLista;
        seguirListaMovimientos = true;
        ultimaPosicionDecision = new Point (-1, -1);
        ultimaOrientacion = Orientacion.derecha;
    }
    
}
