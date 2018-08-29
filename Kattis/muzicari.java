
import java.io.*;
import java.util.*;

public class muzicari{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      st = new StringTokenizer(f.readLine());
      
      int[] array = new int[n];
      
      for(int k = 0; k < m; k++){
         array[k] = Integer.parseInt(st.nextToken());
      }
      
      int rest1 = 0;
      int rest2 = 0;
      
      for(int k = 0; k < m; k++){
         if(rest1+array[k] <= n){
            out.print(rest1 + " ");
            rest1+=array[k];
         } else {
            out.print(rest2 + " ");
            rest2+=array[k];
         }
      }
      
      out.close();
   }
   
}