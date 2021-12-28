//make sure to make new file!
import java.io.*;
import java.util.*;

public class carpet{
   
   public static double MAX = 100000000.0;
   public static double E = 0.0001;
   public static double pi2 = 2.0*Math.PI;
   public static double step = 100000.0;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      double a = Double.parseDouble(st.nextToken());
      double b = Double.parseDouble(st.nextToken());
      double c = Double.parseDouble(st.nextToken());
      
      double mindif = MAX;
      double side = -1;
      
      //double low = 2.0*Math.max(Math.abs(a-b),Math.max(Math.abs(b-c),Math.abs(a-c)));
      double high = Math.min(a+b,Math.min(b+c,a+c));
      
      for(double xd = 1.0; xd <= high*step+E; xd += 1.0){
         double x = xd/step;
         
         double a1 = angle(a,b,x);
         double a2 = angle(b,c,x);
         double a3 = angle(a,c,x);
         
         double angle = a1+a2+a3;
         if(Math.abs(angle-pi2) < mindif){
            mindif = Math.abs(angle-pi2);
            side = x;
         }
            
      }
      
      //out.println(mindif);
      if(mindif > E){
         out.println(-1);
      } else {
         double answer = side*side*0.43301270189;
         out.println(answer);
      }  
      
      
      
      
      
      
      
      
      out.close();
   }
   
   public static double angle(double a, double b, double c){
      return Math.acos((a*a+b*b-c*c)/(2.0*a*b));
   }
   
      
}