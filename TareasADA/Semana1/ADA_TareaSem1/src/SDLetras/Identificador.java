
package SDLetras;

import java.util.*;
/**
 *
 * @author salda
 */
public class Identificador 
{
    private static final int[][] Posiciones= 
    {
        {-1, -1}, {-1, 0}, {-1, 1},
        { 0, -1},          { 0, 1},
        { 1, -1}, { 1, 0}, { 1, 1}
    };

    private Recuadro T;
    public Identificador(Recuadro recuadro) 
    {
        T=recuadro;
    }

    public Map<String, List<Localizacion>> encontrarX(List<String> listaPalabras) 
    {
        Map<String, List<Localizacion>> resultado=new LinkedHashMap<>();
        for (String palabra:listaPalabras) 
        {
            List<Localizacion> ubicaciones=encontrarPalabras(palabra.toUpperCase());
            resultado.put(palabra, ubicaciones);
        }
        return resultado;
    }

    private List<Localizacion> encontrarPalabras(String palabra) 
    {
        for (int fila=0; fila<T.filas(); fila++) 
        {
            for (int columna=0; columna<T.columnas(); columna++) 
            {
                for (int[] dir:Posiciones) 
                {
                    if (concuerda(palabra, fila, columna, dir[0], dir[1])) 
                    {
                        List<Localizacion> ubicaciones = new ArrayList<>();
                        ubicaciones.add(new Localizacion(fila, columna));
                        ubicaciones.add(new Localizacion(fila+dir[0]*(palabra.length()-1), columna+dir[1]*(palabra.length()-1)));
                        return ubicaciones;
                    }
                }
            }
        }
        return new ArrayList<>();
    }

    private boolean concuerda(String palabra, int filaInicio, int colInicio, int dirFila, int dirCol) 
    {
        for (int i=0; i<palabra.length(); i++) 
        {
            int f=filaInicio+i*dirFila;
            int c=colInicio+i*dirCol;
            if (!T.localválida(f, c)) return false;
            char letraT=T.obtenerletra(f, c);
            if (Character.toUpperCase(letraT)!= palabra.charAt(i)) return false;
        }
        return true;
    }
}
