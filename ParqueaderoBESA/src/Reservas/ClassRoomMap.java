package Reservas;

import Logging.*;

/**
 *
 * @author DANTE STERPIN
 */
public class ClassRoomMap
{
    private int[][] Grafo;
    private int nF = 5,nC = 5; // Tamaño del Mapa
    private int[] Nodo = new int[10];
    
    public ClassRoomMap()
    {
        Grafo = new int[nF*nC][10];
        
        Grafo[0][0] = 0; Grafo[0][1] = 0; // 0,0
        Grafo[0][2] = 0; Grafo[0][3] = 0; // North
        Grafo[0][4] = 0; Grafo[0][5] = 0; // South
        Grafo[0][6] = 0; Grafo[0][7] = 1; // East
        Grafo[0][8] = 0; Grafo[0][9] = 0; // West
        
        Grafo[1][0] = 0; Grafo[1][1] = 1; // 0,1
        Grafo[1][2] = 0; Grafo[1][3] = 1; // North
        Grafo[1][4] = 1; Grafo[1][5] = 1; // South
        Grafo[1][6] = 0; Grafo[1][7] = 2; // East
        Grafo[1][8] = 0; Grafo[1][9] = 0; // West
        
        Grafo[2][0] = 0; Grafo[2][1] = 2; // 0,2
        Grafo[2][2] = 0; Grafo[2][3] = 2; // North
        Grafo[2][4] = 0; Grafo[2][5] = 2; // South
        Grafo[2][6] = 0; Grafo[2][7] = 3; // East
        Grafo[2][8] = 0; Grafo[2][9] = 1; // West
        
        Grafo[3][0] = 0; Grafo[3][1] = 3; // 0,3
        Grafo[3][2] = 0; Grafo[3][3] = 3; // North
        Grafo[3][4] = 1; Grafo[3][5] = 3; // South
        Grafo[3][6] = 0; Grafo[3][7] = 4; // East
        Grafo[3][8] = 0; Grafo[3][9] = 2; // West
        
        Grafo[4][0] = 0; Grafo[4][1] = 4; // 0,4
        Grafo[4][2] = 0; Grafo[4][3] = 4; // North
        Grafo[4][4] = 0; Grafo[4][5] = 4; // South
        Grafo[4][6] = 0; Grafo[4][7] = 4; // East
        Grafo[4][8] = 0; Grafo[4][9] = 3; // West
        
        Grafo[5][0] = 1; Grafo[5][1] = 0; // 1,0
        Grafo[5][2] = 1; Grafo[5][3] = 0; // North
        Grafo[5][4] = 2; Grafo[5][5] = 0; // South
        Grafo[5][6] = 1; Grafo[5][7] = 1; // East
        Grafo[5][8] = 1; Grafo[5][9] = 0; // West
        
        Grafo[6][0] = 1; Grafo[6][1] = 1; // 1,1
        Grafo[6][2] = 0; Grafo[6][3] = 1; // North
        Grafo[6][4] = 1; Grafo[6][5] = 1; // South
        Grafo[6][6] = 1; Grafo[6][7] = 2; // East
        Grafo[6][8] = 1; Grafo[6][9] = 0; // West
        
        Grafo[7][0] = 1; Grafo[7][1] = 2; // 1,2
        Grafo[7][2] = 1; Grafo[7][3] = 2; // North
        Grafo[7][4] = 2; Grafo[7][5] = 2; // South
        Grafo[7][6] = 1; Grafo[7][7] = 3; // East
        Grafo[7][8] = 1; Grafo[7][9] = 1; // West
        
        Grafo[8][0] = 1; Grafo[8][1] = 3; // 1,3
        Grafo[8][2] = 0; Grafo[8][3] = 3; // North
        Grafo[8][4] = 1; Grafo[8][5] = 3; // South
        Grafo[8][6] = 1; Grafo[8][7] = 4; // East
        Grafo[8][8] = 1; Grafo[8][9] = 2; // West
        
        Grafo[9][0] = 1; Grafo[9][1] = 4; // 1,4
        Grafo[9][2] = 1; Grafo[9][3] = 4; // North
        Grafo[9][4] = 2; Grafo[9][5] = 4; // South
        Grafo[9][6] = 1; Grafo[9][7] = 4; // East
        Grafo[9][8] = 1; Grafo[9][9] = 3; // West
        
        Grafo[10][0] = 2; Grafo[10][1] = 0; // 2,0
        Grafo[10][2] = 1; Grafo[10][3] = 0; // North
        Grafo[10][4] = 3; Grafo[10][5] = 0; // South
        Grafo[10][6] = 2; Grafo[10][7] = 1; // East
        Grafo[10][8] = 2; Grafo[10][9] = 0; // West
        
        Grafo[11][0] = 2; Grafo[11][1] = 1; // 2,1
        Grafo[11][2] = 2; Grafo[11][3] = 1; // North
        Grafo[11][4] = 2; Grafo[11][5] = 1; // South
        Grafo[11][6] = 2; Grafo[11][7] = 2; // East
        Grafo[11][8] = 2; Grafo[11][9] = 0; // West
        
        Grafo[12][0] = 2; Grafo[12][1] = 2; // 2,2
        Grafo[12][2] = 1; Grafo[12][3] = 2; // North
        Grafo[12][4] = 3; Grafo[12][5] = 2; // South
        Grafo[12][6] = 2; Grafo[12][7] = 3; // East
        Grafo[12][8] = 2; Grafo[12][9] = 1; // West
        
        Grafo[13][0] = 2; Grafo[13][1] = 3; // 2,3
        Grafo[13][2] = 2; Grafo[13][3] = 3; // North
        Grafo[13][4] = 2; Grafo[13][5] = 3; // South
        Grafo[13][6] = 2; Grafo[13][7] = 4; // East
        Grafo[13][8] = 2; Grafo[13][9] = 2; // West
        
        Grafo[14][0] = 2; Grafo[14][1] = 4; // 2,4
        Grafo[14][2] = 1; Grafo[14][3] = 4; // North
        Grafo[14][4] = 3; Grafo[14][5] = 4; // South
        Grafo[14][6] = 2; Grafo[14][7] = 4; // East
        Grafo[14][8] = 2; Grafo[14][9] = 3; // West
        
        Grafo[15][0] = 3; Grafo[15][1] = 0; // 3,0
        Grafo[15][2] = 2; Grafo[15][3] = 0; // North
        Grafo[15][4] = 3; Grafo[15][5] = 0; // South
        Grafo[15][6] = 3; Grafo[15][7] = 1; // East
        Grafo[15][8] = 3; Grafo[15][9] = 0; // West
        
        Grafo[16][0] = 3; Grafo[16][1] = 1; // 3,1
        Grafo[16][2] = 3; Grafo[16][3] = 1; // North
        Grafo[16][4] = 4; Grafo[16][5] = 1; // South
        Grafo[16][6] = 3; Grafo[16][7] = 2; // East
        Grafo[16][8] = 3; Grafo[16][9] = 0; // West
        
        Grafo[17][0] = 3; Grafo[17][1] = 2; // 3,2
        Grafo[17][2] = 2; Grafo[17][3] = 2; // North
        Grafo[17][4] = 3; Grafo[17][5] = 2; // South
        Grafo[17][6] = 3; Grafo[17][7] = 3; // East
        Grafo[17][8] = 3; Grafo[17][9] = 1; // West
        
        Grafo[18][0] = 3; Grafo[18][1] = 3; // 3,3
        Grafo[18][2] = 3; Grafo[18][3] = 3; // North
        Grafo[18][4] = 4; Grafo[18][5] = 3; // South
        Grafo[18][6] = 3; Grafo[18][7] = 4; // East
        Grafo[18][8] = 3; Grafo[18][9] = 2; // West
        
        Grafo[19][0] = 3; Grafo[19][1] = 4; // 3,4
        Grafo[19][2] = 2; Grafo[19][3] = 4; // North
        Grafo[19][4] = 3; Grafo[19][5] = 4; // South
        Grafo[19][6] = 3; Grafo[19][7] = 4; // East
        Grafo[19][8] = 3; Grafo[19][9] = 3; // West
        
        Grafo[20][0] = 4; Grafo[20][1] = 0; // 4,0
        Grafo[20][2] = 4; Grafo[20][3] = 0; // North
        Grafo[20][4] = 4; Grafo[20][5] = 0; // South
        Grafo[20][6] = 4; Grafo[20][7] = 1; // East
        Grafo[20][8] = 4; Grafo[20][9] = 0; // West
        
        Grafo[21][0] = 4; Grafo[21][1] = 1; // 4,1
        Grafo[21][2] = 3; Grafo[21][3] = 1; // North
        Grafo[21][4] = 4; Grafo[21][5] = 1; // South
        Grafo[21][6] = 4; Grafo[21][7] = 2; // East
        Grafo[21][8] = 4; Grafo[21][9] = 0; // West
        
        Grafo[22][0] = 4; Grafo[22][1] = 2; // 4,2
        Grafo[22][2] = 4; Grafo[22][3] = 2; // North
        Grafo[22][4] = 4; Grafo[22][5] = 2; // South
        Grafo[22][6] = 4; Grafo[22][7] = 3; // East
        Grafo[22][8] = 4; Grafo[22][9] = 1; // West
        
        Grafo[23][0] = 4; Grafo[23][1] = 3; // 4,3
        Grafo[23][2] = 3; Grafo[23][3] = 3; // North
        Grafo[23][4] = 4; Grafo[23][5] = 3; // South
        Grafo[23][6] = 4; Grafo[23][7] = 4; // East
        Grafo[23][8] = 4; Grafo[23][9] = 2; // West
        
        Grafo[24][0] = 4; Grafo[24][1] = 4; // 4,4
        Grafo[24][2] = 4; Grafo[24][3] = 4; // North
        Grafo[24][4] = 4; Grafo[24][5] = 4; // South
        Grafo[24][6] = 4; Grafo[24][7] = 4; // East
        Grafo[24][8] = 4; Grafo[24][9] = 3; // West
    }
    
    public ClassRoomMap (int [][] _grafo, int _nF, int _nC)
    {
        ClassLogger.LogMsg("Inicializando variables del grafo dentro del sistema");
        Grafo = _grafo;
        nF = _nF;
        nC = _nC;
    }
    
    public int[] BuscarNodo(int Pi,int Pj)
    {
        for(int i=0; i<(nF*nC); i++)
        {
            if(Grafo[i][0] == Pi && Grafo[i][1] == Pj)
            {
                for(int j=0; j<10; j++)
                {
                    Nodo[j] = Grafo[i][j];
                }
            }
        }
        
        return Nodo;
    }
    
    public int getnF()
    {
        return nF;
    }
    
    public int getnC()
    {
        return nC;
    }
}
