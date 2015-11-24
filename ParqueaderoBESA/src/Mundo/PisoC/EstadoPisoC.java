/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mundo.PisoC;

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
public class EstadoPisoC extends EstadoPiso
{
    public EstadoPisoC(int _size)
    {
        super(_size);
    }
    
    @Override
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
        listaElementosTemporal[3][1] = new ClassElemento("", TipoElemento.Calle, objCalleArriba);  // Para salir hacia Piso-
        listaElementosTemporal[4][1] = new ClassElemento("", TipoElemento.Bahia, objBahiaAbajo);
        listaElementosTemporal[5][1] = new ClassElemento("", TipoElemento.Bahia, objBahiaAbajo);
        listaElementosTemporal[6][1] = new ClassElemento("", TipoElemento.Bahia, objBahiaAbajo);
        listaElementosTemporal[7][1] = new ClassElemento("", TipoElemento.Bahia, objBahiaAbajo);
        listaElementosTemporal[8][1] = new ClassElemento("", TipoElemento.Bahia, objBahiaAbajo);
        listaElementosTemporal[9][1] = new ClassElemento("", TipoElemento.Bahia, objBahiaAbajo);
        listaElementosTemporal[10][1] = new ClassElemento("", TipoElemento.Calle, objCalleAbajo);  // Para entrar desde Piso-
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
}
