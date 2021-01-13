//make sure to make new file!
import java.io.*;
import java.util.*;

public class A680{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int qq = 1; qq <= t; qq++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         long p = Long.parseLong(st.nextToken());
         long q = Long.parseLong(st.nextToken());
         
         if(p > q && p%q != 0) out.println(p);
         else if(p < q) out.println(p);
         else{
            //try different prime factors of q
            long answer = 1L;
            long x = q;
            long i = 2L;
            while(i*i <= x){
               if(x % i == 0){
                  long curp = p;
                  while(curp % q == 0){
                     curp/=i;
                  }
                  answer = Math.max(answer,curp);
               }
               while(x % i == 0) x/=i;
               i++;
            }
            
            if(x > 1){
               long curp = p;
                  while(curp % q == 0){
                     curp/=x;
                  }
                  answer = Math.max(answer,curp);
            }
            
            out.println(answer);
         }
      }
      
      
      
      
      out.close();
   }
   
      
}