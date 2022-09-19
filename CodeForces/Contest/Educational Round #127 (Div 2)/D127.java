//make sure to make new file!
import java.io.*;
import java.util.*;

public class D127{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         long x = Long.parseLong(st.nextToken());
         
         long[] array = new long[n];
         st = new StringTokenizer(f.readLine());
         long min = 300000L;
         long max = -1L;
         long sumdif = 0L;
         for(int k = 0; k < n; k++){
            array[k] = Long.parseLong(st.nextToken());
            if(k > 0){
               sumdif += Math.abs(array[k-1]-array[k]);
            }
            min = Math.min(min,array[k]);
            max = Math.max(max,array[k]);
         }
         
         long ll,lr,lm;
         long hl,hr,hm;
         
         if(x <= max){
            hl = 0L;
            hr = 0L;
            hm = 0L;
         } else {
            hl = x-array[0];
            hr = x-array[n-1];
            hm = 2*(x-max);
         }
         
         if(1 >= min){
            ll = 0L;
            lr = 0L;
            lm = 0L;
         } else {
            ll = array[0]-1L;
            lr = array[n-1]-1L;
            lm = 2*(min-1);
         }
         
         long minadd = Math.min(ll+hr,lr+hl);
         minadd = Math.min(minadd,ll+hm);
         minadd = Math.min(minadd,lr+hm);
         minadd = Math.min(minadd,lm+hl);
         minadd = Math.min(minadd,lm+hr);
         minadd = Math.min(minadd,lm+hm);
         
         long answer = sumdif + minadd;
         out.println(answer);
         
         
         
         
      

      }
      
      
      
      
      out.close();
   }
   
      
}