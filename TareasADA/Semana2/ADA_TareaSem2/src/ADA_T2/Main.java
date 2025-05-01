
package ADA_T2;

import java.util.Random;
import java.util.Scanner;
/**
 *
 * @author salda
 */
public class Main 
{
    public static void main(String[] args) 
    {
        Scanner scanner=new Scanner(System.in);
        Random mix=new Random();
        SubBuscador buscador=new SubBuscador();

        System.out.print("Ingrese la cantidad de números:\n");
        int n=scanner.nextInt();
        int[] valores=new int[n];

        System.out.println("\nValores:");
        for (int i=0; i<n; i++) 
        {
            valores[i]=mix.nextInt(61)-10;
            System.out.print(valores[i]+" ");
        }
        System.out.println();
        SubsecMaxRESULT resp=buscador.BuscarMaxSubsec(valores);
        resp.mostrar();
    }
}