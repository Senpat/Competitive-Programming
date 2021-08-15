//make sure to make new file!
import java.io.*;
import java.util.*;

public class B727{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int q = Integer.parseInt(st.nextToken());
      
      char[] input = f.readLine().toCharArray();
      int[] array = new int[n];
      for(int k = 0; k < n; k++){
         array[k] = (input[k]-'a'+1);
      }
      
      int[] psum = new int[n+1];
      psum[0] = 0;
      for(int k = 1; k <= n; k++){
         psum[k] = psum[k-1] + array[k-1];
      }
      
      for(int k = 0; k < q; k++){
         st = new StringTokenizer(f.readLine());
      
         int l = Integer.parseInt(st.nextToken());
         int r = Integer.parseInt(st.nextToken());
         
         int answer = psum[r]-psum[l-1];
         out.println(answer);
      }
      
      
      
      
      
      
      out.close();
   }
   
      
}