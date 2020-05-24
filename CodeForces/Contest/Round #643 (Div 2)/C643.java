//make sure to make new file!
import java.io.*;
import java.util.*;

public class C643{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      int c = Integer.parseInt(st.nextToken());
      int d = Integer.parseInt(st.nextToken());
      
      int MAXN = 1000005;
      
      int[] freq = new int[MAXN];
      
      int low = a+b;
      int high = b+c;
      
      int peak = Math.min(b-a+1,c-b+1);
      
      for(int k = low; k < low+peak; k++){
         freq[k] = k-low+1;
      }
      
      for(int k = high; k > high-peak; k--){
         if(freq[k] == 0 || freq[k] > high-k+1)
         freq[k] = high-k+1;
      }
      
      for(int k = low; k <= high; k++){
         if(freq[k] != 0) continue;
         freq[k] = peak;
      }
      
      long answer = 0L;
      
      for(int k = low; k <= high; k++){
         long add = (long)freq[k]*(long)Math.max(0,Math.min(k-c,d-c+1));
         answer += add;
      }
      
      out.println(answer);
      
      
      
      
      
      
      
      out.close();
   }
   
      
}