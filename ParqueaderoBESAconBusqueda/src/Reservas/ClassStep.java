package Reservas;

/**
 *
 * @author Dante Sterpin
 */
public class ClassStep
{
    private int[] AIJ;

    public ClassStep(int a,int i,int j)
    {
        this.AIJ = new int[3];
        this.setIJ(a, i, j);
    }
    
    public void setIJ(int a,int i,int j)
    {
        this.AIJ[0] = a; this.AIJ[1] = i; this.AIJ[2] = j;
    }
    
    public int[] getIJ()
    {
        return AIJ;
    }
    
    public int getA()
    {
        return AIJ[0];
    }
    
    public int getI()
    {
        return AIJ[1];
    }
    
    public int getJ()
    {
        return AIJ[2];
    }
}
