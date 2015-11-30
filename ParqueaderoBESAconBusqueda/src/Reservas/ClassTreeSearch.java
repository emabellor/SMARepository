package Reservas;

import java.util.ArrayList;

/**
 *
 * @author DANTE STERPIN
 */
public class ClassTreeSearch
{
    private boolean meta;
    private ArrayList Ruta;
    private ArrayList Arbol;
    private int[][] mapaAux;
    private int[] Nodo = new int[10];
    private ArrayList Solucion;
    
    public ArrayList Enrutar(int Ai,int Aj,int Mi,int Mj,ClassRoomMap roomMap,boolean podar)
    {
        meta = false;
        Arbol = new ArrayList();
        mapaAux = new int[roomMap.getnF()][roomMap.getnC()];
        
        double ini = System.nanoTime();
        
        ClassTreeNode nodoRaiz = new ClassTreeNode();
        nodoRaiz.setPadre(0); nodoRaiz.setOperador(0);
        nodoRaiz.setEstadoI(Ai); nodoRaiz.setEstadoJ(Aj); nodoRaiz.setProfundidad(1);
        nodoRaiz.setHijo1(-1); nodoRaiz.setHijo2(-1); nodoRaiz.setHijo3(-1); nodoRaiz.setHijo4(-1);
        nodoRaiz.setHeuristica(Mi, Mj); nodoRaiz.setMarcador(0);
        Arbol.add(nodoRaiz); meta = ((ClassTreeNode)Arbol.get(0)).getMeta(Mi, Mj);
        
        out:
        while(!meta)
        {
            int Np = 0;
            int mH = (roomMap.getnF()+roomMap.getnC())+((roomMap.getnF()+1)*(roomMap.getnC()+1));
            for(int id=0; id<Arbol.size(); id++)
            {
                if(((ClassTreeNode)Arbol.get(id)).getHeuristica() < mH &&
                   ((ClassTreeNode)Arbol.get(id)).getMarcador() == 0)
                {
                    Np = id; mH = ((ClassTreeNode)Arbol.get(id)).getHeuristica();
                }
            }
            ((ClassTreeNode)Arbol.get(Np)).setMarcador(1);
            mapaAux[((ClassTreeNode)Arbol.get(Np)).getEstadoI()][((ClassTreeNode)Arbol.get(Np)).getEstadoJ()] = 1;
            Nodo = roomMap.BuscarNodo(((ClassTreeNode)Arbol.get(Np)).getEstadoI(), ((ClassTreeNode)Arbol.get(Np)).getEstadoJ());
            
            for(int n=0; n<4; n++)
            {
                int Ei = Nodo[2+(2*n)]; int Ej = Nodo[3+(2*n)];
                if(!podar || mapaAux[Ei][Ej] == 0)
                {
                    ClassTreeNode nuevoNodo = new ClassTreeNode();
                    nuevoNodo.setPadre(Np); nuevoNodo.setOperador(n+1);
                    nuevoNodo.setEstadoI(Ei); nuevoNodo.setEstadoJ(Ej);
                    nuevoNodo.setProfundidad(((ClassTreeNode)Arbol.get(Np)).getProfundidad()+1);
                    nuevoNodo.setHijo1(-1); nuevoNodo.setHijo2(-1); nuevoNodo.setHijo3(-1); nuevoNodo.setHijo4(-1);
                    nuevoNodo.setHeuristica(Mi, Mj); nuevoNodo.setMarcador(0);
                    Arbol.add(nuevoNodo); switch(n)
                    {
                        case(0): ((ClassTreeNode)Arbol.get(Np)).setHijo1(Arbol.size()-1); break;
                        case(1): ((ClassTreeNode)Arbol.get(Np)).setHijo2(Arbol.size()-1); break;
                        case(2): ((ClassTreeNode)Arbol.get(Np)).setHijo3(Arbol.size()-1); break;
                        case(3): ((ClassTreeNode)Arbol.get(Np)).setHijo4(Arbol.size()-1); break;
                    }
                    meta = ((ClassTreeNode)Arbol.get(Arbol.size()-1)).getMeta(Mi, Mj);
                    if(meta) break out;
                }
            }
        }
        
        Ruta = new ArrayList();
        Ruta.add(definirAccion(((ClassTreeNode)Arbol.get(Arbol.size()-1)).getOperador()));
        int Np = ((ClassTreeNode)Arbol.get(Arbol.size()-1)).getPadre();
        while(Np!=0)
        {
            Ruta.add(0, definirAccion(((ClassTreeNode)Arbol.get(Np)).getOperador()));
            Np = ((ClassTreeNode)Arbol.get(Np)).getPadre();
        }
        Solucion = new ArrayList();
        Np = (Arbol.size()-1);
        while(Np!=0)
        {
            int Operador = ((ClassTreeNode)Arbol.get(Np)).getOperador();
            int EstadoI = ((ClassTreeNode)Arbol.get(Np)).getEstadoI();
            int EstadoJ = ((ClassTreeNode)Arbol.get(Np)).getEstadoJ();
            Solucion.add(0, new ClassStep(Operador, EstadoI, EstadoJ));
            Np = ((ClassTreeNode)Arbol.get(Np)).getPadre();
        }
        int Operador = ((ClassTreeNode)Arbol.get(0)).getOperador();
        int EstadoI = ((ClassTreeNode)Arbol.get(0)).getEstadoI();
        int EstadoJ = ((ClassTreeNode)Arbol.get(0)).getEstadoJ();
        Solucion.add(0, new ClassStep(Operador, EstadoI, EstadoJ));
        
        double end = System.nanoTime();
        
        System.out.println("\nÁrbol de Búsqueda: ");
        for(int id=0; id<Arbol.size(); id++)
        {
            int[] nodoArbol = new int[11];
            System.out.print("["+id+"]:\t");
            nodoArbol = ((ClassTreeNode)Arbol.get(id)).getNodoArbol();
            System.out.print("Np: "+nodoArbol[0]+"\t");
            System.out.print("Op: "+nodoArbol[1]+"\t");
            System.out.print("Ei: "+nodoArbol[2]+"\t");
            System.out.print("Ej: "+nodoArbol[3]+"\t");
            System.out.print("Pb: "+nodoArbol[4]+"\t");
            System.out.print("Ha: "+nodoArbol[5]+"\t");
            System.out.print("Hb: "+nodoArbol[6]+"\t");
            System.out.print("Hc: "+nodoArbol[7]+"\t");
            System.out.print("Hd: "+nodoArbol[8]+"\t");
            System.out.print("Fh: "+nodoArbol[9]+"\t");
            System.out.print("Sm: "+nodoArbol[10]);
            System.out.println("");
        }
        System.out.println("Tiempo de Búsqueda: "+((end-ini)/1000000)+" milisegundos");
        
        return Ruta;
    }
    
    private String definirAccion(int Op)
    {
        String Accion = "None";
        switch(Op)
        {
            case(1): Accion = "North"; break;
            case(2): Accion = "South"; break;
            case(3): Accion = "East"; break;
            case(4): Accion = "West"; break;
        }
        return Accion;
    }
    
    public ArrayList getRuta()
    {
        return Ruta;
    }
    
    public ArrayList getSolucion()
    {
        return Solucion;
    }
}
