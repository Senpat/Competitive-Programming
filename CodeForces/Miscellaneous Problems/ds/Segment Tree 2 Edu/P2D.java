//make sure to make new file!
import java.io.*;
import java.util.*;

public class P2D{
   
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
            
            segtree.add(0,0,n-1,l,r,x);
         } else {
            out.println(segtree.sum(0,0,n-1,l,r));
         }
      }
      
      
      
      
      
      
      
      out.close();
   }
   
   public static class Segtree{
   
      private long[] ops;
      private long[] a;
      public Segtree(int size){
         ops = new long[4*size];
         a = new long[4*size];
      }
      
      public void add(int v, int l, int r, int ql, int qr, long x){
         if(l >= ql && r <= qr){
            ops[v] += x;
            a[v] += (long)(r-l+1)*x;
         } else if(r < ql || l > qr){
            return;
         } else {
            int mid = (l+r)/2;
            
            add(2*v+1,l,mid,ql,qr,x);
            add(2*v+2,mid+1,r,ql,qr,x);
            
            a[v] = ops[v]*(long)(r-l+1) + a[2*v+1] + a[2*v+2];
         }
      }
      
      public long sum(int v, int l, int r, int ql, int qr){
         if(l >= ql && r <= qr){
            return a[v];
         } else if(r < ql || l > qr){
            return 0;
         } else{
            int mid = (l+r)/2;
            
            long suml = sum(2*v+1,l,mid,ql,qr);
            long sumr = sum(2*v+2,mid+1,r,ql,qr);
            
            long len = (long)(Math.min(r,qr) - Math.max(l,ql) + 1);
            return ops[v]*len + suml + sumr;
         }
      }
      
   }
   
   
}