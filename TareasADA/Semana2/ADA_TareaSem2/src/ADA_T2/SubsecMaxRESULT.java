
package ADA_T2;

/**
 *
 * @author salda
 */
public class SubsecMaxRESULT 
{
    public int desde;
    public int hasta;
    public int total;

    public SubsecMaxRESULT(int desde, int hasta, int total) 
    {
        this.desde=desde;
        this.hasta=hasta;
        this.total=total;
    }

    public void mostrar()
    {
        if (desde==-1) 
        {
        System.out.println("Todos los números generados son negativos. La suma máxima consecutiva es 0");
        }
        else
        {
        System.out.println("La sumatoria máxima consecutiva es de la posicion "+(desde+1)+" hasta la posicion "+(hasta+1)+" con un valor de "+total);
        }
    }
}