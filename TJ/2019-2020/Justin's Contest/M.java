//make sure to make new file!
import java.io.*;
import java.util.*;

public class M{

   
   public static int[] bits;
   public static int n;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      int c = Integer.parseInt(st.nextToken());
      
            
      bits = new int[n+1];
      
      st = new StringTokenizer(f.readLine());
      for(int k = 1; k <= n; k++){
         update(k,Integer.parseInt(st.nextToken()));
      }
      
      for(int k = 0; k < m; k++){
         st = new StringTokenizer(f.readLine());
         
         int l = Integer.parseInt(st.nextToken());
         int r = Integer.parseInt(st.nextToken());
         
         int answer = c*(psum(r)-psum(l-1));
         out.println(answer);
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