
package ADA_T2;

/**
 *
 * @author salda
 */
public class SubBuscador 
{
    public SubsecMaxRESULT BuscarMaxSubsec(int[] array) 
    {
        int maxS=0;
        int actualS=0;
        int iterador=0;
        int initLast=-1;
        int finalLast=-1;

        for (int i=0; i<array.length; i++) 
        {
            actualS+=array[i];

            if (actualS>maxS) 
            {
                maxS=actualS;
                initLast=iterador;
                finalLast=i;
            }
            if (actualS<0) 
            {
                actualS=0;
                iterador=i+1;
            }
        }
        
        if (initLast==-1) 
        {
            return new SubsecMaxRESULT(-1, -1, 0);
        }

        return new SubsecMaxRESULT(initLast, finalLast, maxS);
    }
}
