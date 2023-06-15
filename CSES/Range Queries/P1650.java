//make sure to make new file!
import java.io.*;
import java.util.*;

public class P1650{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int q = Integer.parseInt(st.nextToken());
      
      int[] array = new int[n];
      int[] pxor = new int[n+1];
      pxor[0] = 0;
      st = new StringTokenizer(f.readLine());
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(st.nextToken());
         pxor[k+1] = pxor[k] ^ array[k];
      }
      
      for(int t = 0; t < q; t++){
         st = new StringTokenizer(f.readLine());
         
         int l = Integer.parseInt(st.nextToken())-1;
         int r = Integer.parseInt(st.nextToken())-1;
         
         out.println(pxor[r+1] ^ pxor[l]);
      }
      
      
      
      
      
      
      
      out.close();
   }
   
      
}