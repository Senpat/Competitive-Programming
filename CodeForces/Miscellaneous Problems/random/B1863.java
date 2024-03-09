//make sure to make new file!
import java.io.*;
import java.util.*;

public class B1863{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         StringTokenizer st = new StringTokenizer(f.readLine());
         int[] array = new int[n];
         int[] indexof = new int[n+1];
         for(int k = 0; k < n; k++){
            array[k] = Integer.parseInt(st.nextToken());
            indexof[array[k]] = k;
         }
         
         int num = 0;
         for(int k = 2; k <= n; k++){
            if(indexof[k] < indexof[k-1]) num++;
         }
         
         out.println(num);

      }
      
      
      
      
      out.close();
   }
   
      
}