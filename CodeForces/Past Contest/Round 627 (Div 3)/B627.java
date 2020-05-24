//make sure to make new file!
import java.io.*;
import java.util.*;

public class B627{
   
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
         
         if(check(array)){
            out.println("YES");
         } else {
            out.println("NO");
         }
      

      }
      
      
      
      
      out.close();
   }
   
   
   public static boolean check(int[] array){
      int n = array.length;
      int[] loc = new int[6000];
      Arrays.fill(loc,-1);
      for(int k = 0; k < n; k++){
         if(loc[array[k]] == -1){
            loc[array[k]] = k;
         } else if(loc[array[k]] < k-1){
            return true;
         }
      }
      
      return false;
   }
}