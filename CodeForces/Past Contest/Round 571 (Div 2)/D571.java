//make sure to make new file!
import java.io.*;
import java.util.*;

public class D571{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      double[] array = new double[n];
      
      HashSet<Integer> bad = new HashSet<Integer>();

      int sum = 0;
      
      for(int k = 0; k < n; k++){
         array[k] = Double.parseDouble(f.readLine());
         if(array[k] == 1.0*Math.floor(array[k])) bad.add(k);
         sum += Math.ceil(array[k]);
      }
      int added = 0;
      for(int k = 0; k < n; k++){
         if(bad.contains(k)) out.println((int)Math.floor(array[k]));
         else if(added < sum){
            out.println((int)Math.floor(array[k]));
            added++;
         } else {
            out.println((int)Math.ceil(array[k]));
         }
      }
      
      
      
      
      

      
      
      
      
      out.close();
   }
   
      
}