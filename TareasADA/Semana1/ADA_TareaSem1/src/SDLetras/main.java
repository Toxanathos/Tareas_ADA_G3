
package SDLetras;

import java.util.*;
/**
 *
 * @author salda
 */
public class main 
{
    public static void main(String[] args) 
    {
        char[][] letras= 
        {
            {'E', 'S', 'T', 'O'},
            {'S', 'T', 'T', 'M'},
            {'E', 'A', 'S', 'A'},
            {'P', 'R', 'N', 'E'}
        };

        List<String> palabras=Arrays.asList("esto", "ese", "pato", "este", "asa");

        Recuadro T=new Recuadro(letras);
        Identificador id=new Identificador(T);
        
        mostrarRECUADRO(letras);

        System.out.println("\nRESULTADOS:\n");
        Map<String, List<Localizacion>> respuestas=id.encontrarX(palabras);

        for (String palabra:respuestas.keySet()) 
        {
            List<Localizacion> ubi=respuestas.get(palabra);
            if (!ubi.isEmpty()) 
            {
                System.out.println("La palabra *"+palabra+"* fue encontrada de "+ubi.get(0)+" a "+ubi.get(1));
            } 
            else 
            {
                System.out.println("La palabra *"+palabra+"* no se encontró.");
            }
        }
    }
    
    public static void mostrarRECUADRO(char[][] letras) 
    {
        System.out.print("    ");
        for (int c=0; c<letras[0].length; c++) 
        {
            System.out.print((c+1)+"   ");
        }
        System.out.println();
        for (int f=0; f<letras.length; f++) 
        {
            System.out.print((f+1)+" | ");
            for (int c=0; c<letras[0].length; c++) 
            {
                System.out.print(" "+letras[f][c]+" ");
            }
            System.out.println();
        }
    }
}