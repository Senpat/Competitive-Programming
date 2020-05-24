//make sure to make new file!
import java.io.*;
import java.util.*;

public class U{
   
   
   public static int[] bits;
   public static int n;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int[] array = new int[n];
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(st.nextToken());
      }
      
      int[] answer = new int[n];
      
      bits = new int[n+1];
   
      for(int k = 0; k < n; k++){
         answer[k] = psum(n) - psum(array[k]);
         update(array[k],1);
      }
      
      for(int k = 0; k < n; k++){
         out.println(answer[k]);
      }
      
      

      
      
      
      
      
      out.close();
   }
   
      
   public static void update(int i, int x){
      for(; i <= n; i += i&-i){
         //System.out.println(i);
         bits[i]+=x;
      }
   
   }
   
   public static int psum(int i){
      int sum = 0;
      for(; i > 0; i -= i&-i){
         //System.out.println(i);
         sum += bits[i];
      }
      return sum;
   
   }

   
      
}