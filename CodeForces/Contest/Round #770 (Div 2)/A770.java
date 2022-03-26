//make sure to make new file!
import java.io.*;
import java.util.*;

public class A770{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
      
         char[] array = f.readLine().toCharArray();
         
         if(m == 0){
            out.println(1);
            continue;
         } 
         
         //check palindrome
         boolean ispalin = true;
         for(int k = 0; k < n; k++){
            if(array[k] != array[n-k-1]){
               ispalin = false;
               break;
            }
         }
         
         if(ispalin){
            out.println(1);
         } else {
            if(m >= 2){
               out.println(2);
            } else {
               out.println(1);
            }
         }
         
         
      }
      
      
      
      
      out.close();
   }
   
      
}