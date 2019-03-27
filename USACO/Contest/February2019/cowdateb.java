/*
TASK: cowdate
LANG: JAVA
*/
import java.io.*;
import java.util.*;

//O(n^2)

public class cowdateb{

   public static double TEN6 = 1000000.0;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("cowdate.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cowdate.out")));
      
      int n = Integer.parseInt(f.readLine());
      
      int[] array = new int[n];
      double[] prob = new double[n];
      double[] iprob = new double[n];
      
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(f.readLine());
         prob[k] = array[k]/TEN6;
         iprob[k] = (1000000-array[k])/TEN6;
         
         
      }
      
      
      double max = 0.0;
      
      for(int k = 0; k < n; k++){
         double cursum = prob[k];
         double curprod = iprob[k];
         max = Math.max(max,cursum);
         for(int j = k+1; j < n; j++){
            cursum = cursum*iprob[j] + curprod*prob[j];
            curprod *= iprob[j];
            max = Math.max(max,cursum);
            //System.out.println(max);
         }
      }
      
      int answer = (int)(max*1000000.0);
      System.out.println(max);
      System.out.println(answer);
      out.println(answer);

      
      
      
      
      out.close();
   }
   
      
}