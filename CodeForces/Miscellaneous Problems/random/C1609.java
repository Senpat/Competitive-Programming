//Complex Market Analysis
import java.io.*;
import java.util.*;

public class C1609{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int N = 1000005;
      boolean[] isprime = new boolean[N];
      Arrays.fill(isprime,true);
      for(int k = 2; k < N; k++){
         if(isprime[k]){
            for(int j = 2*k; j < N; j += k){
               isprime[j] = false;
            }
         }
      }
      isprime[0] = false;
      isprime[1] = false;
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int e = Integer.parseInt(st.nextToken());
         
         st = new StringTokenizer(f.readLine());
         int[] array = new int[n];
         for(int k = 0; k < n; k++){
            array[k] = Integer.parseInt(st.nextToken());
         }
         
         int[] left = new int[n];
         int[] right = new int[n];
         
         Arrays.fill(left,-1);
         Arrays.fill(right,-1);
         
         for(int k = 0; k < e; k++){
            int curleft = -1;  
            for(int j = k; j < n; j += e){
               left[j] = curleft;
               if(array[j] == 1){
                  if(curleft == -1){
                     curleft = j;
                  }
               } else {
                  curleft = -1;
               }
               
            }
            
            int curright = -1;
            for(int j = n-k-1; j >= 0; j -= e){
               right[j] = curright;
               if(array[j] == 1){
                  if(curright == -1){
                     curright = j;
                  }
               } else {
                  curright = -1;
               }
            }
               
         }  
         
         long answer = 0L;
         for(int k = 0; k < n; k++){
            if(!isprime[array[k]]) continue;
            
            long onleft = left[k] == -1 ? 1L : ((long)((k-left[k])/e)+1);
            long onright = right[k] == -1 ? 1L : ((long)((right[k]-k)/e)+1);
            
            answer += onleft*onright-1;
         }
         
         out.println(answer);
      }
      
      
      
      
      out.close();
   }
   
      
}