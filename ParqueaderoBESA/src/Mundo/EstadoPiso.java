/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mundo;
import GUI.MapPanel;
import BESA.Kernell.Agent.StateBESA;
import Carro.*;
import Data.*;
import Logging.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.Point;

public class EstadoPiso extends StateBESA {

    public static final Point puntoEntrada = new Point(0 * 4, 11 * 4);
    public static final Point puntoSalida = new Point(0 * 4, 10 * 4);
    public static final Point puntoSalidaPeatones = new Point (14 * 4 + 3, 0);
    public static final Point puntoEntradaPeatones = new Point (14 * 4 + 3, 14 * 4 + 3);
    
    public int size;
    public int tiempoAparicionCarroMs = 2000;
    public long ultimaAparicionCarro = System.currentTimeMillis();
    public int tiempoAparicionPeatonMs = 10000;
    public long ultimaAparicionPeaton = System.currentTimeMillis();

    protected ClassElemento [][] listaElementos;
    protected List<ClassElemento> listaAgentes;
    private final Object keyLock;

    public EstadoPiso(int _size) 
    {
        //Escala del mundo - 15 * 15
        //El modelo del mapa esta escalado * 4   
        size = 15 * 4;
        keyLock = new Object();
       
        InicializarMapa();
        
        ultimaAparicionCarro = ultimaAparicionCarro - tiempoAparicionCarroMs; //Se realiza con el objetivo de que el primer auto aparezca de forma instantanea
        ultimaAparicionPeaton = ultimaAparicionPeaton - tiempoAparicionPeatonMs;
    }   

    protected void InicializarMapa() 
    {
        List<ClassElemento> nuevaListaAgentes;
        ClassElemento [][] listaElementosTemporal = new ClassElemento[size/4][size/4];
        listaAgentes = new ArrayList<>();

        ClassObjetoCalle objCalleDerecha = new ClassObjetoCalle(EstadoCarro.Orientacion.derecha);
        ClassObjetoCalle objCalleIzquierda = new ClassObjetoCalle(EstadoCarro.Orientacion.izquierda);
        ClassObjetoCalle objCalleAbajo = new ClassObjetoCalle(EstadoCarro.Orientacion.abajo);
        ClassObjetoCalle objCalleArriba = new ClassObjetoCalle(EstadoCarro.Orientacion.arriba);
        ClassObjetoCalle objCalleDerechaAbajo = new ClassObjetoCalle(EstadoCarro.Orientacion.derecha);
        ClassObjetoCalle objCalleDerechaArriba = new ClassObjetoCalle(EstadoCarro.Orientacion.derecha, EstadoCarro.Orientacion.arriba);
        ClassObjetoCalle objCalleIzquierdaAbajo = new ClassObjetoCalle(EstadoCarro.Orientacion.izquierda, EstadoCarro.Orientacion.abajo);
        ClassObjetoCalle objCalleIzquierdaArriba = new ClassObjetoCalle(EstadoCarro.Orientacion.izquierda, EstadoCarro.Orientacion.arriba);
        
        ClassObjetoBahia objBahiaArriba = new ClassObjetoBahia(EstadoCarro.Orientacion.arriba);
        ClassObjetoBahia objBahiaAbajo = new ClassObjetoBahia(EstadoCarro.Orientacion.abajo);
        ClassObjetoBahia objBahiaDerecha = new ClassObjetoBahia(EstadoCarro.Orientacion.derecha);
        ClassObjetoBahia objBahiaIzquierda = new ClassObjetoBahia(EstadoCarro.Orientacion.izquierda);

        
        //La lista de elementos se realiza sobre una escala de 4 x 4 
        listaElementosTemporal[0][0] = new ClassElemento("", TipoElemento.Anden, null);
        listaElementosTemporal[1][0] = new ClassElemento("", TipoElemento.Anden, null);
        listaElementosTemporal[2][0] = new ClassElemento("", TipoElemento.Anden, null);
        listaElementosTemporal[3][0] = new ClassElemento("", TipoElemento.Anden, null);
        listaElementosTemporal[4][0] = new ClassElemento("", TipoElemento.Anden, null);
        listaElementosTemporal[5][0] = new ClassElemento("", TipoElemento.Anden, null);
        listaElementosTemporal[6][0] = new ClassElemento("", TipoElemento.Anden, null);
        listaElementosTemporal[7][0] = new ClassElemento("", TipoElemento.Anden, null);
        listaElementosTemporal[8][0] = new ClassElemento("", TipoElemento.Anden, null);
        listaElementosTemporal[9][0] = new ClassElemento("", TipoElemento.Anden, null);
        listaElementosTemporal[10][0] = new ClassElemento("", TipoElemento.Anden, null);
        listaElementosTemporal[11][0] = new ClassElemento("", TipoElemento.Anden, null);
        listaElementosTemporal[12][0] = new ClassElemento("", TipoElemento.Anden, null);
        listaElementosTemporal[13][0] = new ClassElemento("", TipoElemento.Anden, null);
        listaElementosTemporal[14][0] = new ClassElemento("", TipoElemento.Anden, null);


        listaElementosTemporal[0][1] = new ClassElemento("", TipoElemento.Anden, null);
        listaElementosTemporal[1][1] = new ClassElemento("", TipoElemento.Anden, null);
        listaElementosTemporal[2][1] = new ClassElemento("", TipoElemento.Bahia, objBahiaAbajo);
        listaElementosTemporal[3][1] = new ClassElemento("", TipoElemento.Bahia, objBahiaAbajo);
        listaElementosTemporal[4][1] = new ClassElemento("", TipoElemento.Bahia, objBahiaAbajo);
        listaElementosTemporal[5][1] = new ClassElemento("", TipoElemento.Bahia, objBahiaAbajo);
        listaElementosTemporal[6][1] = new ClassElemento("", TipoElemento.Bahia, objBahiaAbajo);
        listaElementosTemporal[7][1] = new ClassElemento("", TipoElemento.Bahia, objBahiaAbajo);
        listaElementosTemporal[8][1] = new ClassElemento("", TipoElemento.Bahia, objBahiaAbajo);
        listaElementosTemporal[9][1] = new ClassElemento("", TipoElemento.Bahia, objBahiaAbajo);
        listaElementosTemporal[10][1] = new ClassElemento("", TipoElemento.Bahia, objBahiaAbajo);
        listaElementosTemporal[11][1] = new ClassElemento("", TipoElemento.Bahia, objBahiaAbajo);
        listaElementosTemporal[12][1] = new ClassElemento("", TipoElemento.Anden, null);
        listaElementosTemporal[13][1] = new ClassElemento("", TipoElemento.Anden, null);
        listaElementosTemporal[14][1] = new ClassElemento("", TipoElemento.Anden, null);

        listaElementosTemporal[0][2] = new ClassElemento("", TipoElemento.Anden, null);
        listaElementosTemporal[1][2] = new ClassElemento("", TipoElemento.Bahia, objBahiaDerecha);
        listaElementosTemporal[2][2] = new ClassElemento("", TipoElemento.Calle, objCalleAbajo);
        listaElementosTemporal[3][2] = new ClassElemento("", TipoElemento.Calle, objCalleIzquierda);
        listaElementosTemporal[4][2] = new ClassElemento("", TipoElemento.Calle, objCalleIzquierda);
        listaElementosTemporal[5][2] = new ClassElemento("", TipoElemento.Calle, objCalleIzquierda);
        listaElementosTemporal[6][2] = new ClassElemento("", TipoElemento.Calle, objCalleIzquierdaAbajo);
        listaElementosTemporal[8][2] = new ClassElemento("", TipoElemento.Calle, objCalleIzquierda);
        listaElementosTemporal[7][2] = new ClassElemento("", TipoElemento.Calle, objCalleIzquierda);
        listaElementosTemporal[9][2] = new ClassElemento("", TipoElemento.Calle, objCalleIzquierda);
        listaElementosTemporal[10][2] = new ClassElemento("", TipoElemento.Calle, objCalleIzquierda);
        listaElementosTemporal[11][2] = new ClassElemento("", TipoElemento.Calle, objCalleIzquierda);
        listaElementosTemporal[12][2] = new ClassElemento("", TipoElemento.Bahia, objBahiaIzquierda);
        listaElementosTemporal[13][2] = new ClassElemento("", TipoElemento.Anden, null);
        listaElementosTemporal[14][2] = new ClassElemento("", TipoElemento.Anden, null);

        listaElementosTemporal[0][3] = new ClassElemento("", TipoElemento.Anden, null);
        listaElementosTemporal[1][3] = new ClassElemento("", TipoElemento.Bahia, objBahiaDerecha);
        listaElementosTemporal[2][3] = new ClassElemento("", TipoElemento.Calle, objCalleAbajo);
        listaElementosTemporal[3][3] = new ClassElemento("", TipoElemento.Calle, objCalleDerecha);
        listaElementosTemporal[4][3] = new ClassElemento("", TipoElemento.Calle, objCalleDerecha);
        listaElementosTemporal[5][3] = new ClassElemento("", TipoElemento.Calle, objCalleDerecha);
        listaElementosTemporal[6][3] = new ClassElemento("", TipoElemento.Calle, objCalleDerechaAbajo);
        listaElementosTemporal[7][3] = new ClassElemento("", TipoElemento.Calle, objCalleDerechaArriba);
        listaElementosTemporal[8][3] = new ClassElemento("", TipoElemento.Calle, objCalleDerecha);
        listaElementosTemporal[9][3] = new ClassElemento("", TipoElemento.Calle, objCalleDerecha);
        listaElementosTemporal[10][3] = new ClassElemento("", TipoElemento.Calle, objCalleAbajo);
        listaElementosTemporal[11][3] = new ClassElemento("", TipoElemento.Calle, objCalleArriba);
        listaElementosTemporal[12][3] = new ClassElemento("", TipoElemento.Bahia, objBahiaIzquierda);
        listaElementosTemporal[13][3] = new ClassElemento("", TipoElemento.Anden, null);
        listaElementosTemporal[14][3] = new ClassElemento("", TipoElemento.Anden, null);

        listaElementosTemporal[0][4] = new ClassElemento("", TipoElemento.Anden, null);
        listaElementosTemporal[1][4] = new ClassElemento("", TipoElemento.Bahia, objBahiaDerecha);
        listaElementosTemporal[2][4] = new ClassElemento("", TipoElemento.Calle, objCalleAbajo);
        listaElementosTemporal[3][4] = new ClassElemento("", TipoElemento.Calle, objCalleArriba);
        listaElementosTemporal[4][4] = new ClassElemento("", TipoElemento.Bahia, objBahiaIzquierda);
        listaElementosTemporal[5][4] = new ClassElemento("", TipoElemento.Bahia, objBahiaDerecha);
        listaElementosTemporal[6][4] = new ClassElemento("", TipoElemento.Calle, objCalleAbajo);
        listaElementosTemporal[7][4] = new ClassElemento("", TipoElemento.Calle, objCalleArriba);
        listaElementosTemporal[8][4] = new ClassElemento("", TipoElemento.Bahia, objBahiaIzquierda);
        listaElementosTemporal[9][4] = new ClassElemento("", TipoElemento.Bahia, objBahiaDerecha);
        listaElementosTemporal[10][4] = new ClassElemento("", TipoElemento.Calle, objCalleAbajo);
        listaElementosTemporal[11][4] = new ClassElemento("", TipoElemento.Calle, objCalleArriba);
        listaElementosTemporal[12][4] = new ClassElemento("", TipoElemento.Bahia, objBahiaIzquierda);
        listaElementosTemporal[13][4] = new ClassElemento("", TipoElemento.Anden, null);
        listaElementosTemporal[14][4] = new ClassElemento("", TipoElemento.Anden, null);

        listaElementosTemporal[0][5] = new ClassElemento("", TipoElemento.Anden, null);
        listaElementosTemporal[1][5] = new ClassElemento("", TipoElemento.Bahia, objBahiaDerecha);
        listaElementosTemporal[2][5] = new ClassElemento("", TipoElemento.Calle, objCalleAbajo);
        listaElementosTemporal[3][5] = new ClassElemento("", TipoElemento.Calle, objCalleArriba);
        listaElementosTemporal[4][5] = new ClassElemento("", TipoElemento.Bahia, objBahiaIzquierda);
        listaElementosTemporal[5][5] = new ClassElemento("", TipoElemento.Bahia, objBahiaDerecha);
        listaElementosTemporal[6][5] = new ClassElemento("", TipoElemento.Calle, objCalleAbajo);
        listaElementosTemporal[7][5] = new ClassElemento("", TipoElemento.Calle, objCalleArriba);
        listaElementosTemporal[8][5] = new ClassElemento("", TipoElemento.Bahia, objBahiaIzquierda);
        listaElementosTemporal[9][5] = new ClassElemento("", TipoElemento.Bahia, objBahiaDerecha);
        listaElementosTemporal[10][5] = new ClassElemento("", TipoElemento.Calle, objCalleAbajo);
        listaElementosTemporal[11][5] = new ClassElemento("", TipoElemento.Calle, objCalleArriba);
        listaElementosTemporal[12][5] = new ClassElemento("", TipoElemento.Bahia, objBahiaIzquierda);
        listaElementosTemporal[13][5] = new ClassElemento("", TipoElemento.Anden, null);
        listaElementosTemporal[14][5] = new ClassElemento("", TipoElemento.Anden, null);

        listaElementosTemporal[0][6] = new ClassElemento("", TipoElemento.Anden, null);
        listaElementosTemporal[1][6] = new ClassElemento("", TipoElemento.Bahia, objBahiaDerecha);
        listaElementosTemporal[2][6] = new ClassElemento("", TipoElemento.Calle, objCalleAbajo);
        listaElementosTemporal[3][6] = new ClassElemento("", TipoElemento.Calle, objCalleArriba);
        listaElementosTemporal[4][6] = new ClassElemento("", TipoElemento.Bahia, objBahiaIzquierda);
        listaElementosTemporal[5][6] = new ClassElemento("", TipoElemento.Bahia, objBahiaDerecha);
        listaElementosTemporal[6][6] = new ClassElemento("", TipoElemento.Calle, objCalleAbajo);
        listaElementosTemporal[7][6] = new ClassElemento("", TipoElemento.Calle, objCalleArriba);
        listaElementosTemporal[8][6] = new ClassElemento("", TipoElemento.Bahia, objBahiaIzquierda);
        listaElementosTemporal[9][6] = new ClassElemento("", TipoElemento.Bahia, objBahiaDerecha);
        listaElementosTemporal[10][6] = new ClassElemento("", TipoElemento.Calle, objCalleAbajo);
        listaElementosTemporal[11][6] = new ClassElemento("", TipoElemento.Calle, objCalleArriba);
        listaElementosTemporal[12][6] = new ClassElemento("", TipoElemento.Bahia, objBahiaIzquierda);
        listaElementosTemporal[13][6] = new ClassElemento("", TipoElemento.Anden, null);
        listaElementosTemporal[14][6] = new ClassElemento("", TipoElemento.Anden, null);

        listaElementosTemporal[0][7] = new ClassElemento("", TipoElemento.Anden, null);
        listaElementosTemporal[1][7] = new ClassElemento("", TipoElemento.Bahia, objBahiaDerecha);
        listaElementosTemporal[2][7] = new ClassElemento("", TipoElemento.Calle, objCalleAbajo);
        listaElementosTemporal[3][7] = new ClassElemento("", TipoElemento.Calle, objCalleArriba);
        listaElementosTemporal[4][7] = new ClassElemento("", TipoElemento.Bahia, objBahiaIzquierda);
        listaElementosTemporal[5][7] = new ClassElemento("", TipoElemento.Bahia, objBahiaDerecha);
        listaElementosTemporal[6][7] = new ClassElemento("", TipoElemento.Calle, objCalleAbajo);
        listaElementosTemporal[7][7] = new ClassElemento("", TipoElemento.Calle, objCalleArriba);
        listaElementosTemporal[8][7] = new ClassElemento("", TipoElemento.Bahia, objBahiaIzquierda);
        listaElementosTemporal[9][7] = new ClassElemento("", TipoElemento.Bahia, objBahiaDerecha);
        listaElementosTemporal[10][7] = new ClassElemento("", TipoElemento.Calle, objCalleAbajo);
        listaElementosTemporal[11][7] = new ClassElemento("", TipoElemento.Calle, objCalleArriba);
        listaElementosTemporal[12][7] = new ClassElemento("", TipoElemento.Bahia, objBahiaIzquierda);
        listaElementosTemporal[13][7] = new ClassElemento("", TipoElemento.Anden, null);
        listaElementosTemporal[14][7] = new ClassElemento("", TipoElemento.Anden, null);

        listaElementosTemporal[0][8] = new ClassElemento("", TipoElemento.Anden, null);
        listaElementosTemporal[1][8] = new ClassElemento("", TipoElemento.Bahia, objBahiaDerecha);
        listaElementosTemporal[2][8] = new ClassElemento("", TipoElemento.Calle, objCalleAbajo);
        listaElementosTemporal[3][8] = new ClassElemento("", TipoElemento.Calle, objCalleArriba);
        listaElementosTemporal[4][8] = new ClassElemento("", TipoElemento.Bahia, objBahiaIzquierda);
        listaElementosTemporal[5][8] = new ClassElemento("", TipoElemento.Bahia, objBahiaDerecha);
        listaElementosTemporal[6][8] = new ClassElemento("", TipoElemento.Calle, objCalleAbajo);
        listaElementosTemporal[7][8] = new ClassElemento("", TipoElemento.Calle, objCalleArriba);
        listaElementosTemporal[8][8] = new ClassElemento("", TipoElemento.Bahia, objBahiaIzquierda);
        listaElementosTemporal[9][8] = new ClassElemento("", TipoElemento.Bahia, objBahiaDerecha);
        listaElementosTemporal[10][8] = new ClassElemento("", TipoElemento.Calle, objCalleAbajo);
        listaElementosTemporal[11][8] = new ClassElemento("", TipoElemento.Calle, objCalleArriba);
        listaElementosTemporal[12][8] = new ClassElemento("", TipoElemento.Bahia, objBahiaIzquierda);
        listaElementosTemporal[13][8] = new ClassElemento("", TipoElemento.Anden, null);
        listaElementosTemporal[14][8] = new ClassElemento("", TipoElemento.Anden, null);

        listaElementosTemporal[0][9] = new ClassElemento("", TipoElemento.Anden, null);
        listaElementosTemporal[1][9] = new ClassElemento("", TipoElemento.Bahia, objBahiaDerecha);
        listaElementosTemporal[2][9] = new ClassElemento("", TipoElemento.Calle, objCalleAbajo);
        listaElementosTemporal[3][9] = new ClassElemento("", TipoElemento.Calle, objCalleArriba);
        listaElementosTemporal[4][9] = new ClassElemento("", TipoElemento.Bahia, objBahiaIzquierda);
        listaElementosTemporal[5][9] = new ClassElemento("", TipoElemento.Bahia, objBahiaDerecha);
        listaElementosTemporal[6][9] = new ClassElemento("", TipoElemento.Calle, objCalleAbajo);
        listaElementosTemporal[7][9] = new ClassElemento("", TipoElemento.Calle, objCalleArriba);
        listaElementosTemporal[8][9] = new ClassElemento("", TipoElemento.Bahia, objBahiaIzquierda);
        listaElementosTemporal[9][9] = new ClassElemento("", TipoElemento.Bahia, objBahiaDerecha);
        listaElementosTemporal[10][9] = new ClassElemento("", TipoElemento.Calle, objCalleAbajo);
        listaElementosTemporal[11][9] = new ClassElemento("", TipoElemento.Calle, objCalleArriba);
        listaElementosTemporal[12][9] = new ClassElemento("", TipoElemento.Bahia, objBahiaIzquierda);
        listaElementosTemporal[13][9] = new ClassElemento("", TipoElemento.Anden, null);
        listaElementosTemporal[14][9] = new ClassElemento("", TipoElemento.Anden, null);

        listaElementosTemporal[0][10] = new ClassElemento("", TipoElemento.Calle, objCalleAbajo);
        listaElementosTemporal[1][10] = new ClassElemento("", TipoElemento.Calle, objCalleIzquierda);
        listaElementosTemporal[2][10] = new ClassElemento("", TipoElemento.Calle, objCalleIzquierdaAbajo);
        listaElementosTemporal[3][10] = new ClassElemento("", TipoElemento.Calle, objCalleIzquierdaArriba);
        listaElementosTemporal[4][10] = new ClassElemento("", TipoElemento.Calle, objCalleIzquierda);
        listaElementosTemporal[5][10] = new ClassElemento("", TipoElemento.Calle, objCalleIzquierda);
        listaElementosTemporal[6][10] = new ClassElemento("", TipoElemento.Calle, objCalleIzquierdaAbajo);
        listaElementosTemporal[7][10] = new ClassElemento("", TipoElemento.Calle, objCalleIzquierdaArriba);
        listaElementosTemporal[8][10] = new ClassElemento("", TipoElemento.Calle, objCalleIzquierda);
        listaElementosTemporal[9][10] = new ClassElemento("", TipoElemento.Calle, objCalleIzquierda);
        listaElementosTemporal[10][10] = new ClassElemento("", TipoElemento.Calle, objCalleIzquierda);
        listaElementosTemporal[11][10] = new ClassElemento("", TipoElemento.Calle, objCalleArriba);
        listaElementosTemporal[12][10] = new ClassElemento("", TipoElemento.Bahia, objBahiaIzquierda);
        listaElementosTemporal[13][10] = new ClassElemento("", TipoElemento.Anden, null);
        listaElementosTemporal[14][10] = new ClassElemento("", TipoElemento.Anden, null);

        listaElementosTemporal[0][11] = new ClassElemento("", TipoElemento.Calle, objCalleDerecha);
        listaElementosTemporal[1][11] = new ClassElemento("", TipoElemento.Calle, objCalleDerecha);
        listaElementosTemporal[2][11] = new ClassElemento("", TipoElemento.Calle, objCalleDerecha);
        listaElementosTemporal[3][11] = new ClassElemento("", TipoElemento.Calle, objCalleDerechaArriba);
        listaElementosTemporal[4][11] = new ClassElemento("", TipoElemento.Calle, objCalleDerecha);
        listaElementosTemporal[5][11] = new ClassElemento("", TipoElemento.Calle, objCalleDerecha);
        listaElementosTemporal[6][11] = new ClassElemento("", TipoElemento.Calle, objCalleDerecha);
        listaElementosTemporal[7][11] = new ClassElemento("", TipoElemento.Calle, objCalleDerechaArriba);
        listaElementosTemporal[8][11] = new ClassElemento("", TipoElemento.Calle, objCalleDerecha);
        listaElementosTemporal[9][11] = new ClassElemento("", TipoElemento.Calle, objCalleDerecha);
        listaElementosTemporal[10][11] = new ClassElemento("", TipoElemento.Calle, objCalleDerecha);
        listaElementosTemporal[11][11] = new ClassElemento("", TipoElemento.Calle, objCalleArriba);
        listaElementosTemporal[12][11] = new ClassElemento("", TipoElemento.Bahia, objBahiaIzquierda);
        listaElementosTemporal[13][11] = new ClassElemento("", TipoElemento.Anden, null);
        listaElementosTemporal[14][11] = new ClassElemento("", TipoElemento.Anden, null);

        listaElementosTemporal[0][12] = new ClassElemento("", TipoElemento.Anden, null);
        listaElementosTemporal[1][12] = new ClassElemento("", TipoElemento.Anden, null);
        listaElementosTemporal[2][12] = new ClassElemento("", TipoElemento.Bahia, objBahiaArriba);
        listaElementosTemporal[3][12] = new ClassElemento("", TipoElemento.Bahia, objBahiaArriba);
        listaElementosTemporal[4][12] = new ClassElemento("", TipoElemento.Bahia, objBahiaArriba);
        listaElementosTemporal[5][12] = new ClassElemento("", TipoElemento.Bahia, objBahiaArriba);
        listaElementosTemporal[6][12] = new ClassElemento("", TipoElemento.Bahia, objBahiaArriba);
        listaElementosTemporal[7][12] = new ClassElemento("", TipoElemento.Bahia, objBahiaArriba);
        listaElementosTemporal[8][12] = new ClassElemento("", TipoElemento.Bahia, objBahiaArriba);
        listaElementosTemporal[9][12] = new ClassElemento("", TipoElemento.Bahia, objBahiaArriba);
        listaElementosTemporal[10][12] = new ClassElemento("", TipoElemento.Bahia, objBahiaArriba);
        listaElementosTemporal[11][12] = new ClassElemento("", TipoElemento.Bahia, objBahiaArriba);
        listaElementosTemporal[12][12] = new ClassElemento("", TipoElemento.Anden, null);
        listaElementosTemporal[13][12] = new ClassElemento("", TipoElemento.Anden, null);
        listaElementosTemporal[14][12] = new ClassElemento("", TipoElemento.Anden, null);

        listaElementosTemporal[0][13] = new ClassElemento("", TipoElemento.Anden, null);
        listaElementosTemporal[1][13] = new ClassElemento("", TipoElemento.Anden, null);
        listaElementosTemporal[2][13] = new ClassElemento("", TipoElemento.Anden, null);
        listaElementosTemporal[3][13] = new ClassElemento("", TipoElemento.Anden, null);
        listaElementosTemporal[4][13] = new ClassElemento("", TipoElemento.Anden, null);
        listaElementosTemporal[5][13] = new ClassElemento("", TipoElemento.Anden, null);
        listaElementosTemporal[6][13] = new ClassElemento("", TipoElemento.Anden, null);
        listaElementosTemporal[7][13] = new ClassElemento("", TipoElemento.Anden, null);
        listaElementosTemporal[8][13] = new ClassElemento("", TipoElemento.Anden, null);
        listaElementosTemporal[9][13] = new ClassElemento("", TipoElemento.Anden, null);
        listaElementosTemporal[10][13] = new ClassElemento("", TipoElemento.Anden, null);
        listaElementosTemporal[11][13] = new ClassElemento("", TipoElemento.Anden, null);
        listaElementosTemporal[12][13] = new ClassElemento("", TipoElemento.Anden, null);
        listaElementosTemporal[13][13] = new ClassElemento("", TipoElemento.Anden, null);
        listaElementosTemporal[14][13] = new ClassElemento("", TipoElemento.Anden, null);

        listaElementosTemporal[0][14] = new ClassElemento("", TipoElemento.Anden, null);
        listaElementosTemporal[1][14] = new ClassElemento("", TipoElemento.Anden, null);
        listaElementosTemporal[2][14] = new ClassElemento("", TipoElemento.Anden, null);
        listaElementosTemporal[3][14] = new ClassElemento("", TipoElemento.Anden, null);
        listaElementosTemporal[4][14] = new ClassElemento("", TipoElemento.Anden, null);
        listaElementosTemporal[5][14] = new ClassElemento("", TipoElemento.Anden, null);
        listaElementosTemporal[6][14] = new ClassElemento("", TipoElemento.Anden, null);
        listaElementosTemporal[7][14] = new ClassElemento("", TipoElemento.Anden, null);
        listaElementosTemporal[8][14] = new ClassElemento("", TipoElemento.Anden, null);
        listaElementosTemporal[9][14] = new ClassElemento("", TipoElemento.Anden, null);
        listaElementosTemporal[10][14] = new ClassElemento("", TipoElemento.Anden, null);
        listaElementosTemporal[11][14] = new ClassElemento("", TipoElemento.Anden, null);
        listaElementosTemporal[12][14] = new ClassElemento("", TipoElemento.Anden, null);
        listaElementosTemporal[13][14] = new ClassElemento("", TipoElemento.Anden, null);
        listaElementosTemporal[14][14] = new ClassElemento("", TipoElemento.Anden, null);


        listaElementos = new ClassElemento[size][size];
        for (int i = 0; i < size; i+=4)
        {
            for (int j = 0; j < size; j+=4)
            {

                for (int k = i; k < i + 4; k++)
                {
                    for (int l = j; l < j + 4; l++)
                    {
                        listaElementos[k][l] = new ClassElemento(listaElementosTemporal[i/4][j/4]);
                    }
                }
            }
        }
    }
    
    public ClassElemento[][] GetElementos()
    {
        ClassElemento[][] nuevaListaElementos = new ClassElemento[size][size];
        for (int i = 0; i < size; i++)
        {
            for (int j = 0; j < size; j++)
            {
                nuevaListaElementos[i][j] = listaElementos[i][j];
            }
        }
        
        return nuevaListaElementos;
    }
    
    public List<ClassElemento> GetAgentes()
    {
        List<ClassElemento> nuevaListaAgentes = new ArrayList<>();
        for(ClassElemento agente : listaAgentes)
        {
            nuevaListaAgentes.add(new ClassElemento(agente));
        }
        return nuevaListaAgentes;
    }
    
    private ClassElemento[][] ObtenerMapaCompleto(String alias)
    {
        ClassElemento[][] nuevaListaElementos = new ClassElemento[size][size];
        for (int i = 0; i < size; i++)
        {
            for (int j = 0; j < size; j++)
            {
                nuevaListaElementos[i][j] = listaElementos[i][j];
            }
        }

        for(ClassElemento agente : listaAgentes)
        {
            int agenteX = agente.posX;
            int agenteY = agente.posY;    

            if (agente.label.equals(alias))
            {
                continue;
                //Se ignora el agente para poder calcular la posicion
            }
            else
            {            
                switch (agente.tipo)
                {
                    case Auto:
                    {
                        ClassObjetoCarro objCarro = (ClassObjetoCarro)agente.parametro;
                        if (objCarro.orientacion == EstadoCarro.Orientacion.derecha || objCarro.orientacion == EstadoCarro.Orientacion.izquierda)
                        {
                            for (int i = 0; i < 4; i++)
                            {
                                for (int j = 1; j < 3; j++)
                                {
                                    nuevaListaElementos[i+agenteX][j+agenteY] = new ClassElemento(agente);
                                }
                            }
                        }
                        else
                        {
                            for (int i = 1; i < 3; i++)
                            {
                                for (int j = 0; j < 4; j++)
                                {
                                    nuevaListaElementos[i+agenteX][j+agenteY] = new ClassElemento(agente);
                                }
                            }
                        }
                    }
                    break;
                    default:
                    {
                        nuevaListaElementos[agenteX][agenteY] = agente;
                    }
                    break;
                }   
            }
        }
        
        return nuevaListaElementos;
    }

    public boolean ObtenerPosicionAgente (String label, Point posicion)
    {
        boolean result = false;

        for (ClassElemento agente : listaAgentes)
        {
            if (agente.label.equals(label))
            {
                result = true;
                posicion.x = agente.posX;
                posicion.y = agente.posY;
                break;
            }
        }
        
        return result;
    }
    
    public ClassElemento ObtenerAgente (String label)
    {     
        for (ClassElemento agente : listaAgentes)
        {
            if (agente.label.equals(label))
            {
                return agente;
            }
        }
        
        return new ClassElemento ("", TipoElemento.Ninguno, null);
    }
    
    public boolean ActualizarAgente (String label, Point posicion, Object parametro)
    {
        boolean result = false;

        for (ClassElemento agente : listaAgentes)
        {
            if (agente.label.equals(label))
            {
                result = true;
                agente.posX = posicion.x;
                agente.posY = posicion.y;
                agente.parametro = parametro;
                break;
            }
        }
        
        return result;
    }
    
    public ClassElemento[][] ObtenerEstadoMapa (int x, int y)
    {
        return ObtenerEstadoMapa(x, y, "");
    }
        
    public boolean VerificarCasillaDisponiblePeaton(int x, int y, String alias)
    {
        ClassElemento[][] mapaCompleto = ObtenerMapaCompleto(alias);
        boolean result = false;
        
        if (x >= 0 && y >= 0  && x < size && y < size)
        {
            if (TipoElemento.IsAgent(mapaCompleto[x][y].tipo) == false && mapaCompleto[x][y].tipo != TipoElemento.Ninguno)
                result = true;
            else
                result = false;
        }

        return result;
    }
            
    public ClassElemento [][] ObtenerEstadoMapaPeaton(int x, int y, String alias)
    {
        ClassElemento[][] estadoMapa = new ClassElemento[3][3];
        ClassElemento[][] mapaCompleto = ObtenerMapaCompleto(alias);
    
        for (int i = -1; i <= 1; i++)
        {
            for (int j = -1; j <= 1; j++)
            {
                if ( x + i < 0 || y + j < 0 || x + i >= size || y + j >= size)
                {
                    estadoMapa [i + 1][j + 1] = new ClassElemento("", TipoElemento.Ninguno, null);
                }
                else
                {
                    estadoMapa[i + 1][j + 1] = new ClassElemento(mapaCompleto[x+i][y+j]); 
                }
            }
        }
        
        return estadoMapa;
    }
    
    public ClassElemento ObtenerEstadoCasilla (int x, int y, String alias)
    {
        ClassElemento[][] mapaCompleto = ObtenerMapaCompleto(alias);
        
        if ( x < 0 || y < 0 || x >= size || y >= size)
        {
            return new ClassElemento("", TipoElemento.Ninguno, null);
        }
        else
        {
            return new ClassElemento(mapaCompleto[x][y]); 
        }
    }
    
    
    public ClassElemento[][] ObtenerEstadoMapa (int x, int y, String alias)
    {
        ClassElemento[][] estadoMapa = new ClassElemento[4][4];
        ClassElemento[][] mapaCompleto = ObtenerMapaCompleto(alias);
        
        for (int i = 0; i < 4; i++)
        {
            for (int j = 0; j < 4; j++)
            {
                if ( x + i < 0 || y + j < 0 || x + i >= size || y + j >= size)
                {
                    estadoMapa [i][j] = new ClassElemento("", TipoElemento.Ninguno, null);
                }
                else
                {
                    estadoMapa[i][j] = new ClassElemento(mapaCompleto[x+i][y+j]); 
                }
            }
        }
        
        return estadoMapa;
    }
    
    public void AdicionarAgente(ClassElemento agente)
    {
        listaAgentes.add(agente);
    }
    
    
    public boolean ObtenerEstadoVehiculo(String label, DataGetCarroStatusResult carroStatus)
    {
        Point posicionAgente = new Point();
        boolean result = ObtenerPosicionAgente(label, posicionAgente);
        if (result == false)
        {
            ClassLogger.LogMsg("Error obteniendo la posicion del agente " + label, LogLevel.ERROR);
            return result;
        }
        
        ClassElemento[][] mapaCompleto = ObtenerMapaCompleto (label);
        ClassElemento agente = ObtenerAgente(label);
            
        if (agente.posX % 4 == 0 && agente.posY % 4 == 0)
        {
            //Cuadro de toma de decisiones
            carroStatus.tipoBahia = listaElementos[agente.posX][agente.posY].tipo;
            if (carroStatus.tipoBahia == TipoElemento.Calle)
            {
                ClassObjetoCalle objCalle = (ClassObjetoCalle)listaElementos[agente.posX][agente.posY].parametro;
                carroStatus.direccionCalle1 = objCalle.direccionCalle1;
                carroStatus.direccionCalle2 = objCalle.direccionCalle2;
            }
            else if (carroStatus.tipoBahia == TipoElemento.Bahia)
            {
                ClassObjetoBahia objBahia = (ClassObjetoBahia)listaElementos[agente.posX][agente.posY].parametro;
                carroStatus.direccionCalle1 = objBahia.sentido;
                carroStatus.direccionCalle2 = EstadoCarro.Orientacion.ninguno;
            }
            else
            {
                carroStatus.direccionCalle1 = EstadoCarro.Orientacion.ninguno;
                carroStatus.direccionCalle2 = EstadoCarro.Orientacion.ninguno;
            }
            
            carroStatus.estadoBahiaDerecha = false;
            carroStatus.estadoBahiaIzquierda = false;
            carroStatus.estadoBahiaFrontal = false;
            carroStatus.estadoBahiaDerecha = false;
             
            ClassObjetoCarro objCarro = (ClassObjetoCarro)agente.parametro;
            int xIzq = agente.posX;
            int yIzq = agente.posY;
            
            int xDer = agente.posX;
            int yDer = agente.posY;
            
            int xArr = agente.posX;
            int yArr = agente.posY;
            
            int xAba = agente.posX;
            int yAba = agente.posY;
            
            boolean buscarNuevaIzquierda = true;
            boolean buscarNuevaDerecha = true;
            boolean buscarNuevaArriba = true;
            boolean buscarNuevaAbajo = true;
            
            if (agente.posX == 12 && agente.posY == 12)
                System.out.println("Hola Mundo");
            
        
            
            for (int i = 0; i < 2; i++)  //Realiza 2 intentos de busqueda
            {
                if (objCarro.orientacion == EstadoCarro.Orientacion.arriba)
                {
                    xIzq -= 4;
                    xDer += 4;
                    yArr -= 4;
                    yAba += 4;
                }
                else if (objCarro.orientacion == EstadoCarro.Orientacion.abajo)
                {
                    xIzq += 4;
                    xDer -= 4;
                    yArr += 4;
                    yAba -= 4;
                }
                else if (objCarro.orientacion == EstadoCarro.Orientacion.derecha)
                {
                    yIzq -= 4;
                    yDer += 4;
                    xArr += 4;
                    xAba -= 4;
                }
                else
                {
                    yIzq += 4;
                    yDer -= 4;
                    xArr -= 4;
                    xAba += 4;
                }

                if (buscarNuevaIzquierda == true)
                {
                    if (VerificarBahia(xIzq, yIzq, EstadoCarro.Orientacion.izquierda, objCarro.orientacion, mapaCompleto) == true)
                    {
                        buscarNuevaIzquierda = false;
                        carroStatus.estadoBahiaIzquierda = true;
                    }
                    else
                    {
                        if (EsBahia(xIzq, yIzq) == true)
                            buscarNuevaIzquierda = false;
                    }
                        
                }
                
                if (buscarNuevaDerecha == true)
                {
                    if (VerificarBahia(xDer, yDer, EstadoCarro.Orientacion.derecha, objCarro.orientacion, mapaCompleto) == true)
                    {
                        buscarNuevaDerecha = false;
                        carroStatus.estadoBahiaDerecha = true;
                    }
                    else
                    {
                        if (EsBahia(xDer, yDer) == true)
                            buscarNuevaDerecha = false;
                    }
                        
                }
                
                if (buscarNuevaArriba == true)
                {
                    if (VerificarBahia(xArr, yArr, EstadoCarro.Orientacion.arriba, objCarro.orientacion, mapaCompleto) == true)
                    {
                        buscarNuevaArriba = false;
                        carroStatus.estadoBahiaFrontal = true;
                    }
                    else
                    {
                        if (EsBahia(xArr, yArr) == true)
                            buscarNuevaArriba = false;
                    }
                        
                }
                
                if (buscarNuevaAbajo == true)
                {
                    if (VerificarBahia(xAba, yAba, EstadoCarro.Orientacion.abajo, objCarro.orientacion, mapaCompleto) == true)
                    {
                        buscarNuevaAbajo = false;
                        carroStatus.estadoBahiaTrasera = true;
                    }
                    else
                    {
                        if (EsBahia(xAba, yAba) == true)
                            buscarNuevaAbajo = false;
                    }
                        
                }
                
                //Para evitar problemas de indecision, se escribe esta linea
                if (carroStatus.estadoBahiaDerecha == true || carroStatus.estadoBahiaFrontal == true 
                        || carroStatus.estadoBahiaTrasera == true || carroStatus.estadoBahiaIzquierda == true)
                    break;
            }
        }
        else
        {
            //No se pueden tomar decisiones, deja que el agente deambule
            carroStatus.tipoBahia = TipoElemento.Ninguno;
            carroStatus.direccionCalle1 = EstadoCarro.Orientacion.ninguno;
            carroStatus.direccionCalle2 = EstadoCarro.Orientacion.ninguno;
            carroStatus.estadoBahiaIzquierda = false;
            carroStatus.estadoBahiaDerecha = false;
            carroStatus.estadoBahiaFrontal = false;
            carroStatus.estadoBahiaTrasera = false;
        }

        return true;
    }
    
    private boolean VerificarBahia(int x, int y, EstadoCarro.Orientacion orientacionCarro, EstadoCarro.Orientacion orientacionBusqueda, ClassElemento[][] mapa)
    {
        boolean result = false;
        if (x > 0 && y > 0)
        {
            //El auto tiene bordes, hay segmentos de bahia que estan descubiertos
            if (mapa [x][y].tipo == TipoElemento.Bahia && mapa[x+2][y+2].tipo == TipoElemento.Bahia)
            {
                ClassObjetoBahia objetoBahia = (ClassObjetoBahia)mapa[x][y].parametro;
                if (BahiaValida(objetoBahia.sentido, orientacionCarro, orientacionBusqueda) == true)
                {
                    result = true;
                }
            }
        }
        
        return result;
    }
    
    private boolean EsBahia(int x, int y)
    {
        boolean result = false;
        if (x > 0 && y > 0)
        {
            if (listaElementos[x][y].tipo == TipoElemento.Bahia)
                result = true;
        }
        
        return result;
    }
    
    private boolean BahiaValida(EstadoCarro.Orientacion orientacionBahia, EstadoCarro.Orientacion orientacionCarro, EstadoCarro.Orientacion orientacionBusqueda)
    {
        boolean dirBusqueda = EsBahiaVertical(orientacionCarro);
        boolean dirBahia = EsBahiaVertical(orientacionBahia);
        
        if (orientacionBusqueda == EstadoCarro.Orientacion.derecha || orientacionBusqueda == EstadoCarro.Orientacion.izquierda)
            dirBusqueda = !dirBusqueda;
        
        if (dirBusqueda == dirBahia)
            return true;
        else
            return false;
    }
    
    public boolean RemoverAgente(String label)
    {
        boolean result = false;
        
        for (ClassElemento agente : listaAgentes)
        {
            if (agente.label.equals(label))
            {
                ClassLogger.LogMsg("Removiendo agente " + label + " de la lista");
                listaAgentes.remove(agente);
                result = true;
                break;
            }
        }

        return result;
    }
    
    private boolean EsBahiaVertical(EstadoCarro.Orientacion orientacion)
    {
        if (orientacion == EstadoCarro.Orientacion.arriba || orientacion == EstadoCarro.Orientacion.abajo)
            return true;
        else
            return false;
    }
}
