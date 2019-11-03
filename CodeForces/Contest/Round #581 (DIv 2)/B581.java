//make sure to make new file!
import java.io.*;
import java.util.*;

public class B581{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int[] pow2 = new int[23];
      pow2[0] = 1;
      for(int k = 1; k < pow2.length; k++){
         pow2[k] = pow2[k-1]*2;
      }
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int l = Integer.parseInt(st.nextToken());
      int r = Integer.parseInt(st.nextToken());
      
      int min = n-l + pow2[l]-1;
      int max = pow2[r]-1 + (n-r)*pow2[r-1];

      out.println(min + " " + max);
      
      
      
      
      out.close();
   }
   
      
}