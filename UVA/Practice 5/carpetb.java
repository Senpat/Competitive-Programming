//make sure to make new file!
import java.io.*;
import java.util.*;

public class carpetb{
   
   public static double E = 0.000000001;
   public static double pi2 = 2.0*Math.PI;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      double a = Double.parseDouble(st.nextToken());
      double b = Double.parseDouble(st.nextToken());
      double c = Double.parseDouble(st.nextToken());
      
      double low = Math.max(Math.abs(a-b),Math.max(Math.abs(b-c),Math.abs(a-c)));
      double high = Math.min(a+b,Math.min(b+c,a+c));
      
      double l = low;
      double r = high;
      double ans = -1.0;
      
      for(int k = 0; k < 10000 && l < r; k++){
         double x = l + (r-l)/2.0;
         
         double a1 = angle(a,b,x);
         double a2 = angle(b,c,x);
         double a3 = angle(a,c,x);
         
         double angle = a1+a2+a3;
         //out.println(Math.abs(angle-pi2));
         if(angle < pi2-E){
            l = x;
         } else if(angle > pi2+E){
            r = x;
         } else {
            ans = x;
            break;
         }
            
      }
      
      if(ans == -1.0){
         out.println(-1);
      } else {
         double answer = ans*ans*0.43301270189221932338186158537646809;
         out.println(answer);
      }  
      
      
      
      
      
      
      
      
      out.close();
   }
   
   public static double angle(double a, double b, double c){
      return Math.acos((a*a+b*b-c*c)/(2.0*a*b));
   }
   
      
}