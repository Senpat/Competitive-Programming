//make sure to make new file!
import java.io.*;
import java.util.*;

public class P2A{
   
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
            out.println(segtree.min(0,0,n-1,l,r));
         }
      }
      
      
      
      
      
      out.close();
   }
   
   /*
   Supports range add update and range min query (associative, commutative, distributive) operations
   if update operation is uo and query operation is qo, then distributive means that:
      (a uo v) qo (b uo v) = (a qo b) uo v
   to call: v = 0, l = 0, r = size-1
   */
   public static class Segtree{
      
      private long[] ops;           //stores add operations
      private long[] a;             //stores min segtree,
                                    //incorporates operations from below or same level but not above
      
      public Segtree(int size){
         ops = new long[4*size];
         a = new long[4*size];
      }
      
      public void add(int v, int l, int r, int ql, int qr, long x){
         if(l >= ql && r <= qr){
            ops[v] += x;
            a[v] += x;
         } else if(r < ql || l > qr){
            return;
         } else {
            int mid = (l+r)/2;
            
            add(2*v+1,l,mid,ql,qr,x);
            add(2*v+2,mid+1,r,ql,qr,x);
            
            a[v] = ops[v] + Math.min(a[2*v+1],a[2*v+2]);
         }
      }
      
      public long min(int v, int l, int r, int ql, int qr){
         if(l >= ql && r <= qr){
            return a[v];
         } else if(r < ql || l > qr){
            return Long.MAX_VALUE;
         } else {
            int mid = (l+r)/2;
            
            return ops[v] + Math.min(min(2*v+1,l,mid,ql,qr),min(2*v+2,mid+1,r,ql,qr));
         }
      }
      
   }
      
}