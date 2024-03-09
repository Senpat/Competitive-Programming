//make sure to make new file!
import java.io.*;
import java.util.*;

public class P1081{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int N = 1000005;
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      int[] freq = new int[N];
      for(int k = 0; k < n; k++){
         int i = Integer.parseInt(st.nextToken());
         freq[i]++;
      }
      
      int ans = 1;
      for(int k = 2; k < N; k++){
         int sum = 0;
         for(int j = k; j < N; j += k){
            sum += freq[j];
         }
         if(sum >= 2) ans = k;
      }
      
      out.println(ans);
      
      
      
      
      
      
      
      out.close();
   }
   
      
}