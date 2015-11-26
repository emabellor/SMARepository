package Reservas;

import java.util.ArrayList;

/**
 *
 * @author DANTE STERPIN
 */
public class ClassArbitrage
{
    private ArrayList Ruta1 = new ArrayList();
    private ArrayList Ruta2 = new ArrayList();
    private ArrayList Solucion1 = new ArrayList();
    private ArrayList Solucion2 = new ArrayList();
    
    public void solve()
    {
        if (Solucion1.size() > 1 && Solucion2.size() > 1)
        {
            int Id = 0, Rs = 0;
            if (Solucion1.size() < Solucion2.size())
            {
                Id = Solucion1.size(); Rs = 1;
            }
            else
            {
                Id = Solucion2.size(); Rs = 2;
            }
            
            for(int id=0; id<Id; id++)
            {
                if((((ClassStep)Solucion1.get(id)).getI() == ((ClassStep)Solucion2.get(id)).getI()) &&
                   (((ClassStep)Solucion1.get(id)).getJ() == ((ClassStep)Solucion2.get(id)).getJ()))
                {
                    if(Rs == 1)
                    {
                        int Operador = 5; // Esperar al otro
                        int EstadoI = ((ClassStep)Solucion1.get(id-1)).getI();
                        int EstadoJ = ((ClassStep)Solucion1.get(id-1)).getJ();
                        Solucion1.add(id, new ClassStep(Operador, EstadoI, EstadoJ));
                        Ruta1.add(id, "Wait");
                    }
                    else
                    {
                        int Operador = 5; // Esperar al otro
                        int EstadoI = ((ClassStep)Solucion2.get(id-1)).getI();
                        int EstadoJ = ((ClassStep)Solucion2.get(id-1)).getJ();
                        Solucion2.add(id, new ClassStep(Operador, EstadoI, EstadoJ));
                        Ruta2.add(id, "Wait");
                    }
                }
            }
        }
    }
    
    public void setRuta1(ArrayList Ruta1)
    {
        this.Ruta1 = Ruta1;
    }
    
    public void setRuta2(ArrayList Ruta2)
    {
        this.Ruta2 = Ruta2;
    }
    
    public void setSolucion1(ArrayList Solucion1)
    {
        this.Solucion1 = Solucion1;
    }
    
    public void setSolucion2(ArrayList Solucion2)
    {
        this.Solucion2 = Solucion2;
    }
    
    public ArrayList getRuta1()
    {
        return Ruta1;
    }
    
    public ArrayList getRuta2()
    {
        return Ruta2;
    }
    
    public ArrayList getSolucion1()
    {
        return Solucion1;
    }
    
    public ArrayList getSolucion2()
    {
        return Solucion2;
    }
}
