//make sure to make new file!
import java.io.*;
import java.util.*;

public class D631{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         long d = Long.parseLong(st.nextToken());
         long MOD = Long.parseLong(st.nextToken());
         
         if(MOD == 1){
            out.println("0");
            continue;
         }
         if(d == 1){
            out.println("1");
            continue;
         }
         if(d == 2){
            out.println((3L+MOD)%MOD);
            continue;
         }
         
         long[] pow2 = new long[32];
         pow2[0] = 1L;
         for(int k = 1; k < 32; k++){
            pow2[k] = (pow2[k-1]*2+MOD)%MOD;
         }
         
         
         
         long p = 2L;
         int i = 1;
         long answer = 2L;
         while(p*2 <= d){
            
            answer = (answer*(pow2[i]+1) + MOD)%MOD;
            
            i++;
            p*=2;
         }
         
         //i*2 >= d
         answer = (answer*(d-p+2)-1 + MOD)%MOD;
         
         out.println(answer);
         

      }
      
      
      
      
      out.close();
   }
   
      
}