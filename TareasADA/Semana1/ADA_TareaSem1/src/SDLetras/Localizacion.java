
package SDLetras;

/**
 *
 * @author salda
 */
public class Localizacion 
{
    private int f;
    private int c;

    public Localizacion(int fila, int columna) 
    {
        f=fila;
        c=columna;
    }

    public int getFila() 
    {
        return f;
    }

    public int getColumna() 
    {
        return c;
    }

    @Override
    public String toString() 
    {
        return "("+(f+1)+","+(c+1)+")";
    }
}
