//make sure to make new file!
import java.io.*;
import java.util.*;
//uses frac class
public class Hb{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      Frac a = new Frac(-89388783685L,35451069L);
      Frac b = new Frac(719198608989690L,56568089101L);
      Frac c = new Frac(194597221373L,17147789L);
      Frac d = new Frac(1164256456275797L,116759295301L);
      
      Point p1 = new Point(a,b);
      Point p2 = new Point(c,d);
      
      out.println(dist(p1,p2));
      
      /*
      int n = Integer.parseInt(f.readLine());
      
      Line[] lines = new Line[n];
      for(int k = 0; k < n; k++){
         StringTokenizer st = new StringTokenizer(f.readLine());
         
         long x1 = Long.parseLong(st.nextToken())+10000L;
         long y1 = Long.parseLong(st.nextToken())+10000L;
         long x2 = Long.parseLong(st.nextToken())+10000L;
         long y2 = Long.parseLong(st.nextToken())+10000L;
         
         lines[k] = new Line(x1,y1,x2,y2);
      }
      
      counter = 0;
      
      double answer = 0.0;
      
      for(int k = 0; k < n; k++){
         for(int j = k+1; j < n; j++){
            for(int h = j+1; h < n; h++){
               if(lines[k].check(lines[j]) && lines[j].check(lines[h]) && lines[h].check(lines[k])){
                  Point a = lines[k].getintersection(lines[j]);
                  Point b = lines[j].getintersection(lines[h]);
                  Point c = lines[h].getintersection(lines[k]);
                  
                  answer = Math.max(answer,dist(a,b) + dist(b,c) + dist(c,a));
               }
            }
         }
      }
      
      
      if(answer <= 0.00000001) out.println("no triangle");
      else out.println(answer);
      
      
      out.println(counter);
      
      
      */
      
      
      
      out.close();
   }

   
   public static double dist(Point a, Point b){
      return Math.sqrt((a.x.subtract(b.x)).multiply(a.x.subtract(b.x)).eval() + a.y.subtract(b.y).multiply(a.y.subtract(b.y)).eval());
   }
   
      
   public static class Line{
      boolean vertical;
      Frac x;         //if it's vertical
      Frac m;
      Frac b;
      
      public Line(long x1, long y1, long x2, long y2){
         if(x1 == x2){
            x = new Frac(x1,1);
            vertical = true;
         } else {
            //slope
            m = new Frac((y2-y1),(x2-x1));
            b = (new Frac(y1,1)).subtract(m.multiply(new Frac(x1,1)));
            
         }
      }
      
      //checks if intersection is possible
      public boolean check(Line l){
         if(vertical && l.vertical) 
            return false;
         if(vertical || l.vertical) 
            return true;
         return !m.equals(l.m);
      }
      
      public Point getintersection(Line l){
         if(vertical){
            return new Point(x,l.m.multiply(x).add(l.b));
         } else if(l.vertical){
            return new Point(l.x,m.multiply(l.x).add(b));
         } else {
            Frac retx = b.subtract(l.b).divide(l.m.subtract(m));
            Frac rety = m.multiply(retx).add(b);
            
            return new Point(retx,rety);
         }
      }
      
      public String toString(){
         if(vertical) return "x = " + x;
         else return "y = " + m + " * x + " + b;
      }
        
   }
    
   public static class Point{
      Frac x;
      Frac y;
      public Point(Frac a, Frac b){
         x = a;
         y = b;
      }
      
   }
   
   public static long gcd(long a, long b){
      if(a == 0) 
         return b;
      if(b == 0) 
         return a;
      if(a > b){
         return gcd(b,a%b);
      } else {
         return gcd(a,b%a);
      }
   }
   
   public static class Frac{
      long numer;
      long denom;
      
      public Frac(long a, long b){
         numer = a;
         denom = b;
         
         reduce();
      }
      
      public void reduce(){
         if(numer == 0) denom = 1L;
         else if(denom == 0) numer = 1L;
         else{
         //get gcd 
            boolean neg = (numer > 0L) != (denom > 0L);
            
            long gcd = gcd(Math.abs(numer),Math.abs(denom));
            if(gcd == 0){
               System.out.println(numer + " " + denom);
            }
            numer = Math.abs(numer)/gcd;
            denom = Math.abs(denom)/gcd;
            if(neg) numer *= -1L;
         }
      }
      
      public Frac add(Frac fr){
         Frac fr1 = new Frac(numer,denom);
         Frac fr2 = new Frac(fr.numer,fr.denom);
         
         //get lcm of denominators
         
         long lcm = denom*fr.denom/gcd(denom,fr.denom);
         
         Frac ret = new Frac(fr1.numer*lcm/fr1.denom + fr2.numer*lcm/fr2.denom,lcm);
         ret.reduce();
         
         return ret;
      }
      
      public Frac subtract(Frac fr){
         return add(new Frac(fr.numer*-1L,fr.denom));
      }
      
      public Frac multiply(Frac fr){
         Frac ret = new Frac(numer*fr.numer,denom*fr.denom);
         ret.reduce();
         
         return ret;
      }
      
      public Frac divide(Frac fr){
         Frac ret = new Frac(numer*fr.denom,denom*fr.numer);
         ret.reduce();
         
         return ret;
      }
      
      public double eval(){
         return (double)numer/(double)denom;
      }
      
      public boolean equals(Object o){
         Frac other = (Frac)o;
         return (numer == other.numer && denom == other.denom);
      }
      
      public String toString(){
         return "" + numer + "/" + denom;
      }
   }
}