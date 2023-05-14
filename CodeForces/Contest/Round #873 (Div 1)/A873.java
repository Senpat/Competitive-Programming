//make sure to make new file!
import java.io.*;
import java.util.*;

public class A873{

   public static long MOD = 1000000007L;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         
         StringTokenizer st1 = new StringTokenizer(f.readLine());
         Long[] a = new Long[n];
         StringTokenizer st2 = new StringTokenizer(f.readLine());
         Long[] b = new Long[n];
         
         for(int k = 0; k < n; k++){
            a[k] = Long.parseLong(st1.nextToken());
            b[k] = Long.parseLong(st2.nextToken());
         }
         
         Arrays.sort(a);
         Arrays.sort(b);
         
         long answer = 1L;
         
         int bi = 0;
         for(int k = 0; k < n; k++){
            while(bi < n && b[bi] < a[k]){
               bi++;
            }
            bi--;
            
            if(bi < k){
               answer = 0L;
               break;
            }
            
            answer = (answer * (long)(bi-k+1) + MOD)%MOD;
         }
         
         out.println(answer);

      }
      
      
      
      
      out.close();
   }
   
      
}