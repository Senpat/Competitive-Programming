//make sure to make new file!
import java.io.*;
import java.util.*;
//my implementions based on cf segment tree edu
public class segtrees{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      
      
      
      
      
      
      
      
      out.close();
   }
   
   //point update, range sum query
   //to call, v = 0, l = 0, r = size-1
   //l,r and ql,qr are inclusive
   public static class Segtree{
      
      private int n;
      private long[] a;
      
      public Segtree(int size){
         n = size;
         a = new long[4*size];
      }
      
      public void build(int v, int l, int r, long[] array){
         if(l == r){
            a[v] = array[l];
         } else {
            int mid = (l+r)/2;
            //build left
            build(2*v+1,l,mid,array);
            //build right
            build(2*v+2,mid+1,r,array);
            
            a[v] = a[2*v+1] + a[2*v+2];
         }
      }
      
      public void set(int v, int l, int r, int i, long x){
         //i is always in the range
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
            
            a[v] = a[2*v+1]+a[2*v+2];
         }
      }
      
      public long sum(int v, int l, int r, int ql, int qr){
         if(l >= ql && r <= qr){
            //fully within range
            return a[v];
         } else if(r < ql || l > qr){
            //outside range
            return 0;
         } else {
            int mid = (l+r)/2;
            long lsum = sum(2*v+1,l,mid,ql,qr);
            long rsum = sum(2*v+2,mid+1,r,ql,qr);
            return lsum + rsum;
         }
      }
      
   }
   
   //point update, range min query
   //to call, v = 0, l = 0, r = size-1
   //l,r and ql,qr are inclusive
   public static class Segtree{
      
      private int[] a;
      
      public Segtree(int size){
         a = new int[4*size];
      }
      
      public void build(int v, int l, int r, int[] array){
         if(l == r){
            a[v] = array[l];
         } else {
            int mid = (l+r)/2;
            //left
            build(2*v+1,l,mid,array);
            //right
            build(2*v+2,mid+1,r,array);
            
            a[v] = Math.min(a[2*v+1],a[2*v+2]);
         }
      }
      
      public void set(int v, int l, int r, int i, int x){
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
      
      public int min(int v, int l, int r, int ql, int qr){
         if(l >= ql && r <= qr){
            return a[v];
         } else if(r < ql || l > qr){
            return Integer.MAX_VALUE;
         } else {
            int mid = (l+r)/2;
            //left
            int lmin = min(2*v+1,l,mid,ql,qr);
            //right
            int rmin = min(2*v+2,mid+1,r,ql,qr);
            return Math.min(lmin,rmin);
         }
      }
      
   }
   
   
   
   //range assign, range sum query
   //to call, v = 0, l = 0, r = size-1
   //l,r and ql,qr are inclusive
   public static class Segtree{
      
      private final long NOOP = Long.MAX_VALUE;
      private long[] ops;
      private long[] a;
      
      public Segtree(int size){
         ops = new long[4*size];
         Arrays.fill(ops,NOOP);
         a = new long[4*size];
      }
      
      private void propagate(int v, long len1, long len2){
         if(ops[v] == NOOP) return;
         ops[2*v+1] = ops[v];
         a[2*v+1] = len1 * ops[v];
         ops[2*v+2] = ops[v];
         a[2*v+2] = len2 * ops[v];
         ops[v] = NOOP;
      }
      
      public void assign(int v, int l, int r, int ql, int qr, long x){
         if(l >= ql && r <= qr){
            ops[v] = x;
            long len = (long)(r-l+1);
            a[v] = len * x;
         } else if(r < ql || l > qr){
            return;
         } else {
            int mid = (l+r)/2;
            
            propagate(v,(long)(mid-l+1),(long)(r-(mid+1)+1));
            
            assign(2*v+1,l,mid,ql,qr,x);
            assign(2*v+2,mid+1,r,ql,qr,x);
            
            a[v] = a[2*v+1] + a[2*v+2];
         }
      }
      
      public long sum(int v, int l, int r, int ql, int qr){
         if(l >= ql && r <= qr){
            return a[v];
         } else if(r < ql || l > qr){
            return 0L;
         } else {
            int mid = (l+r)/2;
            propagate(v,(long)(mid-l+1),(long)(r-(mid+1)+1));

            return sum(2*v+1,l,mid,ql,qr) + sum(2*v+2,mid+1,r,ql,qr);
            
         }
      }
      
      
   }
   
      

      
}