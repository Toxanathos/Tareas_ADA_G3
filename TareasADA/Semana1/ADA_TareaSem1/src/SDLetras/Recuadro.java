
package SDLetras;

/**
 *
 * @author salda
 */
public class Recuadro 
{
    private char[][] L;

    public Recuadro(char[][] letras) 
    {
        L=letras;
    }

    public char obtenerletra(int fila, int columna) 
    {
        return L[fila][columna];
    }

    public int filas() 
    {
        return L.length;
    }

    public int columnas() 
    {
        return L[0].length;
    }

    public boolean localválida(int fila, int columna) 
    {
        return fila>=0 && fila<filas() && columna>=0 && columna<columnas();
    }
}
