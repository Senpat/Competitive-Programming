//make sure to make new file!
import java.io.*;
import java.util.*;

public class H{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      char[] a = f.readLine().toCharArray();
      char[] b = f.readLine().toCharArray();
      
      int[] diff = new int[n];
      for(int k = 0; k < n; k++){
         diff[k] = ((a[k]-'a') - (b[k]-'a') + 26) % 26;
      }
      
      int prev = 0;
      int answer = 0;
      for(int k = 0; k < n; k++){
         if(diff[k] != prev) answer++;
         prev = diff[k];
      }
      
      out.println(answer);
      
      
      
      
      
      out.close();
   }
   
      
}