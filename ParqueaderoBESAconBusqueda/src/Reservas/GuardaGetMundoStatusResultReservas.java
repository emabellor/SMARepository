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
        
        for (int i = 0; i < size; i++)
        {
            for (int j = 0; j < size; j++)
            {
                int posGrafo = i + j * size;
                
                //Pos Actual
                grafo[posGrafo][0] = j; //Fila
                grafo[posGrafo][1] = i; //Columna
                
                //Pos Norte
                grafo[posGrafo][2] = j;
                grafo[posGrafo][3] = i;
                
                //Pos Sur
                grafo[posGrafo][4] = j;
                grafo[posGrafo][5] = i;
                
                //Pos Este
                grafo[posGrafo][6] = j;
                grafo[posGrafo][7] = i;
                
                //Pos Oeste
                grafo[posGrafo][8] = j;
                grafo[posGrafo][9] = i;
                
                
                if (listaElementos[i*4][j*4].tipo == TipoElemento.Calle)
                {
                    ClassObjetoCalle objCalle = (ClassObjetoCalle)listaElementos[i*4][j*4].parametro;
                    
                    //Norte
                    if (objCalle.direccionCalle1 == EstadoCarro.Orientacion.arriba || objCalle.direccionCalle2 == EstadoCarro.Orientacion.arriba)
                        if((grafo[posGrafo][2] - 1) > 0) grafo[posGrafo][2] = grafo[posGrafo][2] - 1; else grafo[posGrafo][2] = 0;
                    
                    //Sur
                    if (objCalle.direccionCalle1 == EstadoCarro.Orientacion.abajo || objCalle.direccionCalle2 == EstadoCarro.Orientacion.abajo)
                        if((grafo[posGrafo][4] + 1) < size) grafo[posGrafo][4] = grafo[posGrafo][4] + 1; else grafo[posGrafo][4] = size;
                    
                    //Este
                    if (objCalle.direccionCalle1 == EstadoCarro.Orientacion.derecha || objCalle.direccionCalle2 == EstadoCarro.Orientacion.derecha)
                        if((grafo[posGrafo][7] + 1) < size) grafo[posGrafo][7] = grafo[posGrafo][7] + 1; else grafo[posGrafo][7] = size;
                    
                    //Oeste
                    if (objCalle.direccionCalle1 == EstadoCarro.Orientacion.izquierda || objCalle.direccionCalle2 == EstadoCarro.Orientacion.izquierda)
                        if((grafo[posGrafo][9] - 1) > 0) grafo[posGrafo][9] = grafo[posGrafo][9] - 1; else grafo[posGrafo][9] = 0;
                }
                else
                {
                    //Solo se considera calles por el momento
                }
            }
        }
            
        ClassRoomMap roomMap = new ClassRoomMap(grafo, size, size);
        
        System.out.println("Grafo:");
        for (int i = 0; i < size*size; i++)
        {
            for (int j = 0; j < 10; j++)
            {
                System.out.print("["+i+"]["+j+"]: "+String.valueOf(grafo[i][j])+" ");
            }
            System.out.println("");
        }
        System.out.println("");
        
        Point puntoInicial = new Point(11, 0);
        Point puntoFinal = new Point(2, 9);
        
        System.out.println("\nProblema: ("+puntoInicial.x+", "+puntoInicial.y+") -> ("+puntoFinal.x+", "+puntoFinal.y+")");
        ClassTreeSearch treeSearch = new ClassTreeSearch();
        ArrayList ruta = treeSearch.Enrutar(puntoInicial.x, puntoInicial.y, puntoFinal.x, puntoFinal.y, roomMap, true);
        ArrayList solucion = treeSearch.getSolucion(); 
        DataView(ruta, solucion);
    }
    
    private void DataView(ArrayList Ruta, ArrayList Solucion)
    {
        System.out.print("\nSolución: ");
        for(int id=0; id < Ruta.size(); id++)
        {
            System.out.print(Ruta.get(id) + " ");
        }
        System.out.println("");
        
        System.out.print("\nA: ");
        for(int id=0; id < Solucion.size(); id++)
        {
            if(((ClassStep)Solucion.get(id)).getI()>=10 || ((ClassStep)Solucion.get(id)).getJ()>=10)
            System.out.print(((ClassStep)Solucion.get(id)).getA() + "  ");
            else System.out.print(((ClassStep)Solucion.get(id)).getA() + " ");
        }
        System.out.println("");
        
        System.out.print("I: ");
        for(int id=0; id < Solucion.size(); id++)
        {
            if(((ClassStep)Solucion.get(id)).getI()<10 && ((ClassStep)Solucion.get(id)).getJ()>=10)
            System.out.print(((ClassStep)Solucion.get(id)).getI() + "  ");
            else System.out.print(((ClassStep)Solucion.get(id)).getI() + " ");
        }
        System.out.println("");
        
        System.out.print("J: ");
        for(int id = 0; id < Solucion.size(); id++)
        {
            if(((ClassStep)Solucion.get(id)).getJ()<10 && ((ClassStep)Solucion.get(id)).getI()>=10)
            System.out.print(((ClassStep)Solucion.get(id)).getJ() + "  ");
            else System.out.print(((ClassStep)Solucion.get(id)).getJ() + " ");
        }
        System.out.println("");
    }
    
    
}
