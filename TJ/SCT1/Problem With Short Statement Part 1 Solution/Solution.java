//make sure to make new file!
import java.io.*;
import java.util.*;

public class Solution{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      char[] array = f.readLine().toCharArray();
      
      int[] numones = new int[n+1];
      numones[0] = 0;
      for(int k = 1; k <= n; k++){
         numones[k] = numones[k-1] + (array[k-1] == '1' ? 1 : 0);
      }
      
      for(int k = 1; k <= n; k++){
         int answer = numones[k] - (numones[k]-numones[k-numones[k]]);
         out.println(answer);
      }
      
         

      
      
      
      
      
      out.close();
   }
   
      
}