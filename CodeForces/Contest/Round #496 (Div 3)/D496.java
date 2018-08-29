//Polycap and Div3
//tutorial
import java.io.*;
import java.util.*;

public class D496{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      String s = f.readLine();
      
      int n = s.length();
      
      int[] fin = new int[3];
      int[] z = new int[n+1];
      
      Arrays.fill(fin,-1);
      
      fin[0] = 0;
      
      int r = 0;
      
      for(int k = 1; k <= n; k++){
         r = (r + s.charAt(k-1) - '0')%3;
         
         z[k] = z[k-1];
         
         if(fin[r]!=-1){
            z[k] = Math.max(z[k],z[fin[r]]+1);
         }
         
         fin[r] = k;
      }
      
      out.println(z[n]);
      
      
      
      
      
      out.close();
   }
   
}