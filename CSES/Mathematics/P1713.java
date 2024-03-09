//make sure to make new file!
import java.io.*;
import java.util.*;

public class P1713{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int N = 1000005;
      int[] div = new int[N];
      for(int k = 1; k < N; k++){
         for(int j = k; j < N; j += k){
            div[j]++;
         }
      }
      
      int q = Integer.parseInt(f.readLine());
      for(int t = 0; t < q; t++){
         int i = Integer.parseInt(f.readLine());
         out.println(div[i]);
      }
      
      
      
      
      
      
      
      
      
      
      out.close();
   }
   
      
}