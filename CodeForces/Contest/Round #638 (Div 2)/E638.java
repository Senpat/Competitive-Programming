//make sure to make new file!
import java.io.*;
import java.util.*;

public class E638{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      long m = Long.parseLong(st.nextToken());
      
      long[] r = new long[n];
      long[] b = new long[n];
      
      long sumr = 0L;
      long sumb = 0L;
      
      for(int k = 0; k < n; k++){
         st = new StringTokenizer(f.readLine());
         r[k] = Long.parseLong(st.nextToken());
         b[k] = Long.parseLong(st.nextToken());
         
         sumr += r[k];
         sumb += b[k];
      }
      
      long answer = 0L;
      
      answer += sumr/m;
      answer += sumb/m;
      
      long rr = sumr%m;
      long br = sumb%m;
      
      for(int k = 0; k < n; k++){
         if(Math.min(r[k],rr) + Math.min(b[k],br) >= m){
            answer++;
            break;
         }
      }
      
      out.println(answer);
      
      
      
      
      
      
      
      
      out.close();
   }
   
      
}