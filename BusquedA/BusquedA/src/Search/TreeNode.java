package Search;

/**
 *
 * @author DANTE STERPIN
 */
public class TreeNode
{
    private int[] nodoArbol;
    private boolean meta = false;
    
    public TreeNode()
    {
        nodoArbol = new int[11];
    }
    
    public void setPadre(int Np)
    {
        this.nodoArbol[0] = Np;
    }
    
    public void setOperador(int Op)
    {
        this.nodoArbol[1] = Op;
    }
    
    public void setNodoI(int Ai)
    {
        this.nodoArbol[2] = Ai;
    }
    
    public void setNodoJ(int Aj)
    {
        this.nodoArbol[3] = Aj;
    }
    
    public void setProfundidad(int Pb)
    {
        this.nodoArbol[4] = Pb;
    }
    
    public void setHijo1(int Nh)
    {
        this.nodoArbol[5] = Nh;
    }
    
    public void setHijo2(int Nh)
    {
        this.nodoArbol[6] = Nh;
    }
    
    public void setHijo3(int Nh)
    {
        this.nodoArbol[7] = Nh;
    }
    
    public void setHijo4(int Nh)
    {
        this.nodoArbol[8] = Nh;
    }
    
    public void setHeuristica(int Mi,int Mj)
    {
        int h = (Math.abs(Mi-this.nodoArbol[2]) + Math.abs(Mj-this.nodoArbol[3]));
        int f = h + (this.nodoArbol[4]-1);
        this.nodoArbol[9] = f;
    }
    
    public void setMarcador(int Sm)
    {
        this.nodoArbol[10] = Sm;
    }
    
    public boolean getMeta(int Mi,int Mj)
    {
        meta = false;
        if(Mi == this.nodoArbol[2] && Mj == this.nodoArbol[3]) meta = true;
        return meta;
    }
    
    public int[] getNodoArbol()
    {
        return nodoArbol;
    }
    
    public int getPadre()
    {
        return nodoArbol[0];
    }
    
    public int getOperador()
    {
        return nodoArbol[1];
    }
    
    public int getNodoI()
    {
        return nodoArbol[2];
    }
    
    public int getNodoJ()
    {
        return nodoArbol[3];
    }
    
    public int getProfundidad()
    {
        return nodoArbol[4];
    }
    
    public int getHijo1()
    {
        return nodoArbol[5];
    }
    
    public int getHijo2()
    {
        return nodoArbol[6];
    }
    
    public int getHijo3()
    {
        return nodoArbol[7];
    }
    
    public int getHijo4()
    {
        return nodoArbol[8];
    }
    
    public int getHeuristica()
    {
        return nodoArbol[9];
    }
    
    public int getMarcador()
    {
        return nodoArbol[10];
    }
}
