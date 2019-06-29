//make sure to make new file!
import java.io.*;
import java.util.*;

public class C571{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      char[] a = f.readLine().toCharArray();
      char[] b = f.readLine().toCharArray();
      
      int answer = 0;
      boolean last = false;
            
      int n = a.length;
      int m = b.length;
      
      int count = 0;
      for(int k = 0; k < m; k++){
         if(a[k] != b[k]){
            count++;
         }
      }
      
      if(count%2 == 0){
         answer++;
         last = true;
      }
      
      for(int k = 0; k < n-m; k++){
         int diff = 0;
         
      
         if(a[k] == a[k+m]){
            if(last) answer++;
         } else {
            if(!last)answer++;
            last = !last;
         }
      }
      
      out.println(answer);
         
      
      
      

      
      
      
      
      out.close();
   }
   
      
}