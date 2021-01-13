//make sure to make new file!
import java.io.*;
import java.util.*;

public class AGB{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int[] array = new int[n];
         for(int k = 0; k < n; k++){
            array[k] = Integer.parseInt(st.nextToken());
         }
         
         HashSet<Integer> hset = new HashSet<Integer>();
         for(int k = 0; k < n; k++){
            for(int j = k+1; j < n; j++){
               hset.add(array[j]-array[k]);
            }
         }
         
         out.println(hset.size());
      
      

      }
      
      
      
      
      out.close();
   }
   
      
}