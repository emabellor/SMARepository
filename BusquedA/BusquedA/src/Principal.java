
import Search.TreeSearch;
import java.util.ArrayList;

/**
 *
 * @author DANTE STERPIN
 */
public class Principal
{
    private String[][] MapaSala = new String[5][5];
    private TreeSearch busqueda = new TreeSearch();
    private ArrayList Ruta = new ArrayList();
    private int[] Nodo = new int[10];
    
    public void Ejecutar()
    {
        //Los parametros son las coordenadas de i, j de inicio; i,j de llegada
        busqueda.Enrutar(4, 2, 0, 4);
    }
    
    public static void main(String[] args)
    {
        new Principal().Ejecutar();
    }
    
}
