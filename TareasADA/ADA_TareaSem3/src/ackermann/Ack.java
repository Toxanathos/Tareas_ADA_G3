
package ackermann;
/**
 *
 * @author salda
 */
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class Ack {

    public static int ackIterativo(int x, int y) 
    {
        Deque<Integer> stack=new ArrayDeque<>();
        stack.push(x);

        while (!stack.isEmpty()) 
        {
            x=stack.pop();
            if (x==0) 
            {
                y=y+1;
            } 
            else if (y==0) 
              {
                stack.push(x-1);
                y=1;
              } 
              else 
                {
                 stack.push(x-1);
                 stack.push(x);
                 y=y-1;
                }
        }
        return y;
    }

    public static void main(String[] args) 
    {
        Scanner sc=new Scanner(System.in);
        System.out.print("Ingresar valor para m (>= 0): ");
        int m=sc.nextInt();
        System.out.print("Ingresar valor para n (>= 0:) ");
        int n=sc.nextInt();
        sc.close();

        if (m<0 || n<0) {
            System.err.println("Solo se permiten enteros no negativos");
            return;
        }
        int resultado=ackIterativo(m, n);
        System.out.printf("A(%d, %d) = %d%n", m, n, resultado);
    }
}