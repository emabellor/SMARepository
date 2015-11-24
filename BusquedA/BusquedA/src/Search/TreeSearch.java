package Search;

import java.util.ArrayList;

/**
 *
 * @author DANTE STERPIN
 */
public class TreeSearch
{
    private boolean meta;
    private ArrayList Ruta;
    private ArrayList Arbol;
    private int[][] mapaExplorado;
    private RoomMap roomMap = new RoomMap();
    private int[] Nodo = new int[10];
    
    public void Enrutar(int Ai,int Aj,int Mi,int Mj)
    {
        meta = false;
        Arbol = new ArrayList();
        mapaExplorado = new int[5][5];
        TreeNode nodoRaiz = new TreeNode();
        nodoRaiz.setPadre(0); nodoRaiz.setOperador(0);
        nodoRaiz.setNodoI(Ai); nodoRaiz.setNodoJ(Aj); nodoRaiz.setProfundidad(1);
        nodoRaiz.setHijo1(-1); nodoRaiz.setHijo2(-1); nodoRaiz.setHijo3(-1); nodoRaiz.setHijo4(-1);
        nodoRaiz.setHeuristica(Mi, Mj); nodoRaiz.setMarcador(0);
        Arbol.add(nodoRaiz); meta = ((TreeNode)Arbol.get(0)).getMeta(Mi, Mj);
        
        out:
        while(!meta)
        {
            int Np = 0; int mH = (5+5)+(6*6);
            for(int id=0; id<Arbol.size(); id++)
            {
                if(((TreeNode)Arbol.get(id)).getHeuristica() < mH &&
                   ((TreeNode)Arbol.get(id)).getMarcador() == 0)
                {
                    Np = id; mH = ((TreeNode)Arbol.get(id)).getHeuristica();
                }
            }
            ((TreeNode)Arbol.get(Np)).setMarcador(1);
            mapaExplorado[((TreeNode)Arbol.get(Np)).getNodoI()][((TreeNode)Arbol.get(Np)).getNodoJ()] = 1;
            Nodo = roomMap.BuscarNodo(((TreeNode)Arbol.get(Np)).getNodoI(), ((TreeNode)Arbol.get(Np)).getNodoJ());
            
            for(int n=0; n<4; n++)
            {
                int Ei = Nodo[2+(2*n)]; int Ej = Nodo[3+(2*n)];
                if(mapaExplorado[Ei][Ej] == 0)
                {
                    TreeNode nuevoNodo = new TreeNode();
                    nuevoNodo.setPadre(Np); nuevoNodo.setOperador(n+1);
                    nuevoNodo.setNodoI(Ei); nuevoNodo.setNodoJ(Ej);
                    nuevoNodo.setProfundidad(((TreeNode)Arbol.get(Np)).getProfundidad()+1);
                    nuevoNodo.setHijo1(-1); nuevoNodo.setHijo2(-1); nuevoNodo.setHijo3(-1); nuevoNodo.setHijo4(-1);
                    nuevoNodo.setHeuristica(Mi, Mj); nuevoNodo.setMarcador(0);
                    Arbol.add(nuevoNodo); switch(n)
                    {
                        case(0): ((TreeNode)Arbol.get(Np)).setHijo1(Arbol.size()-1); break;
                        case(1): ((TreeNode)Arbol.get(Np)).setHijo2(Arbol.size()-1); break;
                        case(2): ((TreeNode)Arbol.get(Np)).setHijo3(Arbol.size()-1); break;
                        case(3): ((TreeNode)Arbol.get(Np)).setHijo4(Arbol.size()-1); break;
                    }
                    meta = ((TreeNode)Arbol.get(Arbol.size()-1)).getMeta(Mi, Mj);
                    if(meta) break out;
                }
            }
        }
        
        Ruta = new ArrayList();
        Ruta.add(definirAccion(((TreeNode)Arbol.get(Arbol.size()-1)).getOperador()));
        int Np = ((TreeNode)Arbol.get(Arbol.size()-1)).getPadre();
        while(Np!=0)
        {
            Ruta.add(0, definirAccion(((TreeNode)Arbol.get(Np)).getOperador()));
            Np = ((TreeNode)Arbol.get(Np)).getPadre();
        }
        
        for(int id=0; id<Arbol.size(); id++)
        {
            int[] nodoArbol = new int[11];
            System.out.print(id+": ");
            nodoArbol = ((TreeNode)Arbol.get(id)).getNodoArbol();
            for(int a=0; a<11; a++)
            {
                System.out.print(nodoArbol[a]+" ");
            }
            System.out.println("");
        }
        System.out.println("Solución: ");
        for(int id=0; id<Ruta.size(); id++)
        {
            System.out.print(Ruta.get(id)+", ");
        }
        System.out.println("");
    }
    
    private String definirAccion(int Op)
    {
        String Accion = "";
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
}
