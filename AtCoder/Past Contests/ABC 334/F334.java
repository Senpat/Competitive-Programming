//make sure to make new file!
import java.io.*;
import java.util.*;
import java.text.*;

public class F334{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      st = new StringTokenizer(f.readLine());
      double sx = Double.parseDouble(st.nextToken());
      double sy = Double.parseDouble(st.nextToken());
      
      double[] x = new double[n];
      double[] y = new double[n];
      
      for(int k = 0; k < n; k++){
         st = new StringTokenizer(f.readLine());
         x[k] = Double.parseDouble(st.nextToken());
         y[k] = Double.parseDouble(st.nextToken());
      }
      
      double base = dist(sx,sy,x[0],y[0]) + dist(sx,sy,x[n-1],y[n-1]);
      double[] cost = new double[n-1];
      for(int k = 0; k < n-1; k++){
         double curbase = dist(x[k],y[k],x[k+1],y[k+1]);
         base += curbase;
         cost[k] = dist(sx,sy,x[k],y[k]) + dist(sx,sy,x[k+1],y[k+1]) - curbase;
      }
      
      //segtree at x is minimum val to get first x, and uses x
      Segtree segtree = new Segtree(n-1);
      int gap = m-1;
      for(int k = 0; k < n-1; k++){
         double add = 0.0;
         if(k > gap) add = segtree.min(0,0,n-2,k-gap-1,k-1);
         
         segtree.set(0,0,n-2,k,add+cost[k]);
      }
      
      double answer = base;
      if(n > 1){
         answer += segtree.min(0,0,n-2,n-2-gap,n-2);
      }
      //out.println(new DecimalFormat("#.##############################").format(answer));
      out.println(answer);
      
      
      
      
      out.close();
   }
   
   public static double dist(double x1, double y1, double x2, double y2){
      return Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2));
   }
   
   //point update, range min query
   //to call, v = 0, l = 0, r = size-1
   //l,r and ql,qr are inclusive
   public static class Segtree{
      
      private double[] a;
      
      public Segtree(int size){
         a = new double[4*size];
      }
      
      
      public void set(int v, int l, int r, int i, double x){
         if(l == r){
            a[v] = x;
         } else {
            int mid = (l+r)/2;
            if(i <= mid){
               //go left
               set(2*v+1,l,mid,i,x);
            } else {
               //go right
               set(2*v+2,mid+1,r,i,x);
            }
            
            a[v] = Math.min(a[2*v+1],a[2*v+2]);
         }
      }
      
      public double min(int v, int l, int r, int ql, int qr){
         if(l >= ql && r <= qr){
            return a[v];
         } else if(r < ql || l > qr){
            return Double.MAX_VALUE;
         } else {
            int mid = (l+r)/2;
            //left
            double lmin = min(2*v+1,l,mid,ql,qr);
            //right
            double rmin = min(2*v+2,mid+1,r,ql,qr);
            return Math.min(lmin,rmin);
         }
      }
      
   }
   
      
}