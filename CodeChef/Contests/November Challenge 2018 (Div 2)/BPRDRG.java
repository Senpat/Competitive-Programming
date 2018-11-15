//make sure to make new file!
import java.io.*;
import java.util.*;

public class BPRDRG{
   
   public static void main(String[] args)throws java.lang.Exception{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      
      int[] numer = new int[26];
      
      numer[1] = 1;
      for(int k = 2; k <= 25; k++){
         if(k%2==0){
            numer[k] = numer[k-1] * 2 - 1;
         } else {
            numer[k] = numer[k-1] * 2 + 1;
         }
      }
      
      for(int k = 0; k < n; k++){
         int c = Integer.parseInt(st.nextToken());
         
         out.print(numer[c] + " " + (int)Math.pow(2,c));
         if(k < n-1) out.print(" ");
      }
      
      out.println();
      
      out.close();
   }
   
}