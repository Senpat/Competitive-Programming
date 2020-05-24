//make sure to make new file!
import java.io.*;
import java.util.*;

public class B579{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         int n = Integer.parseInt(f.readLine());
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int[] array = new int[4*n];
         for(int k = 0; k < 4*n; k++){
            array[k] = Integer.parseInt(st.nextToken());
         }
         Arrays.sort(array);
         
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
   
      int l = 0;
      int r = n-1;
      
      if(!(array[l] == array[l+1] && array[r] == array[r-1])){
         return false;
      }
      
      int area = array[l]*array[r];
      l+=2;
      r-=2;
      while(l < r){
         if(!(array[l] == array[l+1] && array[r] == array[r-1]) || array[l]*array[r] != area){
            return false;
         }
         l+=2;
         r-=2;
      }
      
      return true;
      
      
      
      
   }
}