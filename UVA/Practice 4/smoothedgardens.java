//make sure to make new file!
import java.io.*;
import java.util.*;
//COMPLETELY WRONG (misread problem)
public class smoothedgardens{
   
   public static double a;
   public static double b;
   public static double c;
   
   public static double circumr;
   public static double triarea;
   
   public static double E;
   public static double REP;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      E = 0.00001;
      REP = 200;
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int useless = Integer.parseInt(st.nextToken());
         double bx = Double.parseDouble(st.nextToken());
         double cx = Double.parseDouble(st.nextToken());
         double cy = Double.parseDouble(st.nextToken());
         double L  = Double.parseDouble(st.nextToken());
         
         triarea = cy*bx/2.0;
         //sides
         double[] s = new double[3];
         s[0] = dist(0.0,0.0,bx,0.0);
         s[1] = dist(0.0,0.0,cx,cy);
         s[2] = dist(bx,0.0,cx,cy);
         
         Arrays.sort(s);
         
         a = s[0];
         b = s[1];
         c = s[2];
         
         circumr = a*b*c/Math.sqrt((a+b+c)*(b+c-a)*(c+a-b)*(a+b-c));
         
         //if you can just make a circle (length > circumcircle circumference)
         if(circumr * Math.PI * 2.0 < L + E){
            //make a circle, A = L^2/(4*pi)
            double answer = L*L/(4.0*Math.PI);
            out.println("CIRCLE");
            out.println(q + " " + answer);
            continue;
         }
         
         //if you can make a thing that only hits the ends of the side c
         double check2 = check2(L);
         if(check2 > -1.0){
            out.println("TWO");
            out.println(q + " " + check2);
            continue;
         }
         
         
         double answer = tsearch1(L);
         out.println("THREE");
         out.println(q + " " + answer);
      
      }
      
      
      
      
      out.close();
   }
   
   //returns answer
   public static double tsearch1(double L){
      double l = 0.0;
      double r = L;
      double ans = 100000000.0;
      
      for(int ts = 0; ts < REP; ts++){
         double m1 = l + (r-l)/3.0;
         double m2 = l + 2.0*(r-l)/3.0;
         
         double[] ret1 = calc(c,m1);
         double[] ret2 = calc(c,m2);
         
         double ts1 = tsearch2(L-ret1[0]);
         double ts2 = tsearch2(L-ret2[0]);
        
         double result1 = ret1[1] + ts1;
         double result2 = ret2[1] + ts2;
         if(result1 < result2 || ts2 == -1.0){
            r = m2;
            ans = result1;
         } else {
            l = m1;
            ans = result2;
         }
      }
      
      return ans;
      
   
   
   }
   
   //inputs the length remaining to do a and b
   //outputs the area minus the area of the a part
   public static double tsearch2(double L){
      if(L < a+b+E) 
         return -1.0;
      
      double l = 0.0;
      double r = L;
      double ans = 100000000.0;
      for(int ts = 0; ts < REP; ts++){
         double m1 = l + (r-l)/3.0;
         double m2 = l + 2.0*(r-l)/3.0;
         
         double[] ret1 = calc(b,m1);
         double[] ret2 = calc(b,m2);
         
         double result1 = calcans2(ret1,L-ret1[0]);
         double result2 = calcans2(ret2,L-ret2[0]);
         
         if(result1 < result2 || result2 == -1.0){
            ans = result1;
            r = m2;
         } else {
            ans = result2;
            l = m1;
         }
         
         
      }
      
      return ans;
      
   }
   
   //calculates answer for tsearch2
   //ret is arc length and area for side b
   //L is string left for side a
   public static double calcans2(double[] ret, double L){
      if(L < a+E) 
         return -1.0;
       
      double area = ret[1] + triarea;
       
       //get area for string of length L on the side a
       //binary search on chord height
      double height = calchac(L,a);

      double[] ret3 = calc(a,height);
      return area+ret3[1];
   }
         
   
   //[0] is arclength, [1] is area
   public static double[] calc(double chord, double height){
      double[] ret = new double[2];
      
      //arclength
      double x = chord/2.0;
      double d = x*x/height + height;
      double r = d/2.0;
      double circum = Math.PI * d;
      double angle = Math.abs(Math.asin(x/r));
      if(height > r){
         angle = Math.PI-angle;
      }
      ret[0] = circum * angle / Math.PI;
      
      //area
      double fullarea = Math.PI*r*r;
      double slice = fullarea * angle / Math.PI;
      double triarea = Math.abs(r-height)*x;
      ret[1] = slice-triarea;
      
      return ret;
   }
   
   //returns -2 if it is impossible, otherwise returns the answer
   public static double check2(double L){
      double L2 = L/2.0;
      
      //see if you can make a circle without intersecting 3rd point
      //you do this by seeing if the arc of circum circle is < L2
      double angle = Math.abs(Math.asin((c/2.0)/circumr));
      double circumcircum = circumr*Math.PI*2.0;                        //circumference of circumcircle
      double circumarc = circumcircum * angle / Math.PI;
      
      if(circumarc < L2 + E){
         //you can just make a semicircle
         //return area of this slice
         double slicearea = calc(c,calchac(L2,c))[1];
         return slicearea*2.0;
         
      } else {
         return -2.0;
      }
   }
   
   //calculate chord height from arc and chord using binary search
   public static double calchac(double arc, double chord){
       //get area for string of length L or the side a
       //binary search on chord height
      double l = E;
      double r = arc;
      double ans = 100000000.0;
       
      for(int bs = 0; bs < REP; bs++){
         double mid = l + (r-l)/2.0;
         ans = mid;
         double[] retb = calc(chord,mid);
         if(Math.abs(retb[0]-arc) < E/10.0){
            break;
         }
         
         if(retb[0] < arc){
            l = mid;
         } else {
            r = mid;
         }
            
      }
      
      return ans;
   }
   
   public static double dist(double x1, double y1, double x2, double y2){
      return (Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2)));
   }
      
}