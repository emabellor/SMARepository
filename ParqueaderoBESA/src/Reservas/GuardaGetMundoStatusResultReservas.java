/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reservas;

import BESA.Kernell.Agent.*;
import BESA.Kernell.Agent.Event.EventBESA;
import Carro.EstadoCarro;
import Data.ClassElemento;
import Data.ClassObjetoCalle;
import Data.DataGetMundoStatusResult;
import Data.TipoElemento;
import Logging.*;
import java.awt.Point;
import java.util.ArrayList;
/**
 *
 * @author Mauricio
 */
public class GuardaGetMundoStatusResultReservas extends GuardBESA
{
    @Override
    public boolean funcEvalBool(StateBESA objEvalBool) 
    {
        return true;
    }
    
    @Override
    public void funcExecGuard(EventBESA ebesa) 
    {
        DataGetMundoStatusResult data = (DataGetMundoStatusResult)ebesa.getData();
        EstadoReservas estado = (EstadoReservas)this.getAgent().getState();
        ObtenerGrafo(data.listaElementos, estado.size);
    }
    
    public void ObtenerGrafo (ClassElemento[][] listaElementos, int size)
    {
        int [][] grafo = new int[size*size][10];
        
        for (int j = 0; j < size; j++)
        {
            for (int i = 0; i < size; i++)
            {
                int posGrafo = i + j * size;
                grafo[posGrafo][0] = i;
                grafo[posGrafo][1] = j;
                
                if (listaElementos[i*4][j*4].tipo == TipoElemento.Calle)
                {
                    ClassObjetoCalle objCalle = (ClassObjetoCalle)listaElementos[i*4][j*4].parametro;
                    if (objCalle.direccionCalle1 == EstadoCarro.Orientacion.arriba || objCalle.direccionCalle2 == EstadoCarro.Orientacion.arriba)
                        grafo[posGrafo][3] = 1; //Norte
                    if (objCalle.direccionCalle1 == EstadoCarro.Orientacion.abajo || objCalle.direccionCalle2 == EstadoCarro.Orientacion.abajo)
                        grafo[posGrafo][5] = 1; //Sur
                    if (objCalle.direccionCalle1 == EstadoCarro.Orientacion.derecha || objCalle.direccionCalle2 == EstadoCarro.Orientacion.derecha)
                        grafo[posGrafo][7] = 1; //Este
                    if (objCalle.direccionCalle1 == EstadoCarro.Orientacion.izquierda || objCalle.direccionCalle2 == EstadoCarro.Orientacion.izquierda)
                        grafo[posGrafo][9] = 9; //Oeste
                }
                else
                {
                    //No se consideran las bahias circundantes -- Termina!
                }
            }
        }
            
        ClassRoomMap roomMap = new ClassRoomMap(grafo, size, size);

        Point puntoInicial = new Point(11, 0);
        Point puntoFinal = new Point(11, 11);

        ClassLogger.LogMsg("Problema: ("+puntoInicial.x+","+puntoInicial.y+") -> ("+puntoFinal.x+","+puntoFinal.y+")");
        ClassTreeSearch treeSearch = new ClassTreeSearch();
        ArrayList ruta = treeSearch.Enrutar(puntoInicial.x, puntoInicial.y, puntoFinal.x, puntoFinal.y, roomMap, true);
        ArrayList solucion = treeSearch.getSolucion(); 
        DataView(ruta, solucion);
    }
    
    private void DataView(ArrayList Ruta, ArrayList Solucion)
    {
        ClassLogger.LogMsg("\nSolución: ");
        for(int id=0; id < Ruta.size(); id++)
        {
            ClassLogger.LogMsg(Ruta.get(id) + " ");
        }
        ClassLogger.LogMsg("");
        
        
        ClassLogger.LogMsg("\nA: ");
        for(int id=0; id < Solucion.size(); id++)
        {
            ClassLogger.LogMsg(((ClassStep)Solucion.get(id)).getA() + " ");
        }
        
        ClassLogger.LogMsg("");
        ClassLogger.LogMsg("I: ");
        for(int id=0; id < Solucion.size(); id++)
        {
            System.out.print(((ClassStep)Solucion.get(id)).getI() + " ");
        }
        
        ClassLogger.LogMsg("");
        ClassLogger.LogMsg("J: ");
        for(int id = 0; id < Solucion.size(); id++)
        {
            System.out.print(((ClassStep)Solucion.get(id)).getJ() + " ");
        }
        
        ClassLogger.LogMsg("");
    }
    
    
}
