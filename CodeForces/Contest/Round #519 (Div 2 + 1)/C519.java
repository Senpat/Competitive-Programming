//make sure to make new file!
import java.io.*;
import java.util.*;

public class C519{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      char[] array = f.readLine().toCharArray();
      int n = array.length;
      
      int[] ans = new int[array.length];
      
      char cur = array[0];
      
      for(int k = 1; k < n-1; k++){
         if(cur == 'a' && array[k] == 'b' && array[k+1] == 'a'){
            ans[k] = 1;
            cur = 'b';
         } else if(cur == 'b' && array[k] == 'a' && array[k+1] == 'b'){
            ans[k] = 1;
            cur = 'a';
         }
      }
      
      
      if(cur == 'b'){
         ans[n-1] = 1;
      }
      
      
      for(int k = 0; k < n; k++){
         out.print(ans[k] + " ");
      }
      
      
      
      out.close();
   }
   
}