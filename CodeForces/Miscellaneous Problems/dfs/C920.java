//Swap Adjacent Elements
import java.io.*;
import java.util.*;

public class C920{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int[] array = new int[n+1];
      
      for(int k = 1; k <= n; k++) array[k] = Integer.parseInt(st.nextToken());
      
      int[] bi = new int[n];
      int[] pbi = new int[n];
      
      String s = f.readLine();
      for(int k = 1; k < n; k++){
         bi[k] = Character.getNumericValue(s.charAt(k-1));
         pbi[k] = bi[k]+pbi[k-1];
      }
      
      for(int k = 1; k <= n; k++){
         if(array[k] > k){
            if(pbi[array[k]-1] - pbi[k-1] < array[k] - k){
               //System.out.println(array[array[k]-1] - array[k-1] + " " + (array[k] - k));
               out.println("NO");
               out.close();
               System.exit(0);
            }
         } else if(array[k] < k){
            if(pbi[k-1] - pbi[array[k]-1] < k - array[k]){
               out.println("NO");
               out.close();
               System.exit(0);
            }
         }
      }
      
      out.println("YES");
      
      out.close();
   }
   
}