//make sure to make new file!
import java.io.*;
import java.util.*;

public class D1542{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      int[] mind = new int[n+1];
      int[] freq = new int[n+1];
      
      Arrays.fill(mind,n+5);
      
      for(int k = 0; k < m; k++){
         st = new StringTokenizer(f.readLine());
      
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
      
         freq[a]++;
         if(b > a){
            mind[a] = Math.min(mind[a],b-a);
         } else {
            mind[a] = Math.min(mind[a],n-a+b);
         }
      }
      
      int[] totald = new int[n+1];
      
      for(int k = 1; k <= n; k++){
         if(freq[k] == 0){
            totald[k] = 0;
            continue;
         }
         totald[k] = (freq[k]-1)*n + mind[k];
      }
      
      for(int k = 1; k <= n; k++){
         int cur = totald[k];
         for(int j = 1; j < n; j++){
            int i = k+j;
            if(i > n) i-= n;
            cur = Math.max(cur, j+totald[i]);
         }
         out.print(cur + " ");
      }
         
         
      
      
      
      out.close();
   }
   
      
}