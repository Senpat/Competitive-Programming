//Array and Operations
import java.io.*;
import java.util.*;

public class D1618{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      int[] freq = new int[200005];
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
         
         st = new StringTokenizer(f.readLine());
         int[] array = new int[n];
         for(int k = 0; k < n; k++){
            array[k] = Integer.parseInt(st.nextToken());
         }
         
         Arrays.sort(array);
         
         int answer = 0;
         for(int k = 0; k < n-2*m; k++){
            answer += array[k];
         }
         
         int maxfreq = 0;
         for(int k = n-2*m; k < n; k++){
            freq[array[k]]++;
            maxfreq = Math.max(maxfreq,freq[array[k]]);
         }
         
         answer += Math.max(0,maxfreq-m);
         
         out.println(answer);  
         
         for(int k = n-2*m; k < n; k++) freq[array[k]] = 0;

      }
      
      
      
      
      out.close();
   }
   
      
}