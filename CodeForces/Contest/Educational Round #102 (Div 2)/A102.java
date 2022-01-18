//make sure to make new file!
import java.io.*;
import java.util.*;

public class A102{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
         
         st = new StringTokenizer(f.readLine());
         int[] array = new int[n];
         for(int k = 0; k < n; k++){
            array[k] = Integer.parseInt(st.nextToken());
         }
         
         if(check(array,m)){
            out.println("YES");
         } else {
            out.println("NO");
         }
      

      }
      
      
      
      
      out.close();
   }
   
   public static boolean check(int[] array, int m){
      int n = array.length;
      Arrays.sort(array);
      
      if(array[n-1] <= m || array[0] + array[1] <= m) return true;
      return false;
   }
   
      
}