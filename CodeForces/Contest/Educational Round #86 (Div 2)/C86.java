//make sure to make new file!
import java.io.*;
import java.util.*;

public class C86{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         long a = Long.parseLong(st.nextToken());
         long b = Long.parseLong(st.nextToken());
         int q1 = Integer.parseInt(st.nextToken());
         
         //find lcm
         long lcm = a*b/gcd(a,b);
         
         long max = Math.max(a,b);
         
         StringJoiner sj = new StringJoiner(" ");
         for(int k = 0; k < q1; k++){
            
            long answer = 0L;
            
            st = new StringTokenizer(f.readLine());
      
            long l = Long.parseLong(st.nextToken());
            long r = Long.parseLong(st.nextToken());
         
            //find smallest multiple of lcm greater than l
            long lg = l/lcm+1;
            //find biggest mulitple < r
            long rl = r/lcm;
            
            long lr = l%lcm;
            long rr = r%lcm;
            
            if(rl < lg){
               answer = Math.min(r-l+1,Math.max(0L,max-lr));
            }
            if(rl >= lg){
               answer += Math.max(0L,max-lr);
               answer += Math.min(max,rr+1);
               answer += max*(rl-lg);
            }
            
            
            sj.add("" + (r-l+1-answer));
         }
         
         out.println(sj.toString());
            
            

      }
      
      
      
      
      out.close();
   }
   
   public static long gcd(long a, long b){ 
      if(a==b) return a;
      if(a > b){
         if(b == 0) return a;
         return gcd(a%b,b);
      } else {
         if(a == 0) return b;
         return gcd(b%a,a);
      }
   }
}