//make sure to make new file!
import java.io.*;
import java.util.*;

public class P2F{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int q = Integer.parseInt(st.nextToken());
      
      Segtree segtree = new Segtree(n);
      
      for(int t = 0; t < q; t++){
         st = new StringTokenizer(f.readLine());
         
         int qt = Integer.parseInt(st.nextToken());
         int l = Integer.parseInt(st.nextToken());
         int r = Integer.parseInt(st.nextToken())-1;
         
         if(qt == 1){
            long x = Long.parseLong(st.nextToken());
            
            segtree.assign(0,0,n-1,l,r,x);
         } else {
            out.println(segtree.sum(0,0,n-1,l,r));
         }
      }
      
      
      
      
      
      out.close();
   }
   
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