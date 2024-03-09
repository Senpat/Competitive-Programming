//make sure to make new file!
import java.io.*;
import java.util.*;

public class Gluckyshirt{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int i = Integer.parseInt(st.nextToken());
      int x = Integer.parseInt(st.nextToken());
      
      double nd = (double)n;
      double id = (double)i;
      
      double answer = 0.0;
      double prev = 0.0;
      for(int max = 1; max <= n; max++){
         double d = (double)max;
         double frac = d/nd;
         double prod = exp(frac,x) - prev;
         prev += prod;
         if(max < i){
            answer += prod * id;
         } else {
            answer += (d + 1.0)/2.0 * prod;
         }
      }
      
      out.println(answer);
      
      
      
      
      
      
      
      out.close();
   }
   
      
   public static double exp(double base, int pow){
      if(pow == 0) return 1.0;
      if(pow == 1) return base;
      double ret = exp(base,pow/2);
      ret = ret*ret;
      if(pow%2 == 1) ret = ret*base;
      return ret;
   }
}