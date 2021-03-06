import java.io.*;
import java.util.*;
//cht with Li-Chao Tree
public class lichaotree{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      //start Li-Chao Tree
      lines = new Line[4*MAXN];
      
      //add default values
      Arrays.fill(lines,new Line(0L,Long.MIN_VALUE));
      
      
      out.close();
   }
   
   //translated from cp-algorithms
   //Li-Chao Tree
   public static int MAXN = __;
   
   public static Line[] lines;
   
   //to call: line is the line, v = 1, l = 0, r = MAXN
   public static void addline(Line line, int v, int l, int r){
      int m = (l+r)/2;
      boolean lef = line.eval(l) > lines[v].eval(l);
      boolean mid = line.eval(m) > lines[v].eval(m);
      
      if(mid){
         Line temp = line;
         line = lines[v];
         lines[v] = temp;
      }
      
      if(r-l==1) 
         return;
      else if(lef != mid){
         addline(line, 2*v, l, m);
      } else {
         addline(line,2*v+1, m, r);
      }
   }
   
   //to call: x is the x coordinate, v = 1, l = 0, r = MAXN
   public static long get(long x, int v, int l, int r){
      int m = (l+r)/2;
      if(r-l==1){
         return lines[v].eval(x);
      } else if(x < m){
         return Math.max(lines[v].eval(x),get(x,2*v, l, m));
      } else {
         return Math.max(lines[v].eval(x),get(x,2*v+1, m ,r));
      }
   }
   
   
   
   
   public static class Line{
      //y=mx+c
      long m;
      long c;
      public Line(long a, long b){
         m = a;
         c = b;
      }
      public long eval(long x){
         return m*x+c;
      }
      //returns x value of intersection (doesn't handle parallel)
      public double intersectionX(Line l){
         return ((double)(l.c-c))/((double)(m-l.m));
      }
      public String toString(){
         return m + " " + c;
      }
         
   }
}