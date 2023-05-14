//make sure to make new file!
import java.io.*;
import java.util.*;

public class C853{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
      
         int[] num = new int[n+m+1];            //number of times that number appears in an array
         int[] array = new int[n+1];              //curent state of array
         int[] last = new int[n+1];               //when that value in the array got set
         st = new StringTokenizer(f.readLine());
         for(int k = 1; k <= n; k++){
            array[k] = Integer.parseInt(st.nextToken());
            last[k] = 0;
         }
         
         for(int k = 1; k <= m; k++){
            st = new StringTokenizer(f.readLine());
            
            int i = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            
            num[array[i]] += (k-last[i]);
            last[i] = k;
            array[i] = x;
         }
         
         for(int k = 1; k <= n; k++){
            num[array[k]] += (m-last[k]+1);
         }
         
         long answer = 0L;
         
         long c2m1 = c2((long)(m+1));
         for(int k = 1; k <= n+m; k++){
            answer += c2m1 - c2((long)(m+1-num[k]));
         }
         
         out.println(answer);

      }
      
      
      
      
      out.close();
   }
   
   public static long c2(long x){
      return x*(x-1)/2;
   }
      
}