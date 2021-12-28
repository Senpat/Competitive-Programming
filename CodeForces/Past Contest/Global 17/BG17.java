//make sure to make new file!
import java.io.*;
import java.util.*;

public class BG17{
   
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
         
         int a = -1;
         int b = -1;
         
         for(int k = 0; k < n/2; k++){
            if(array[k] != array[n-k-1]){
               a = array[k];
               b = array[n-k-1];
               break;
            }
         }
         
         if(a == -1 && b == -1){
            out.println("YES");
            continue;
         }
         
         if(check(array,a) || check(array,b)){
            out.println("YES");
         } else {
            out.println("NO");
         }
      

      }
      
      
      
      
      out.close();
   }
   
   public static boolean check(int[] array, int x){
      int l = 0;
      int r = array.length-1;
      
      
      while(l < r){
         if(array[l] == x) l++;
         else if(array[r] == x) r--;
         else if(array[l] != array[r]) return false;
         else{
            l++;
            r--;
         }
      }
      
      return true;
   }
         
   
      
}