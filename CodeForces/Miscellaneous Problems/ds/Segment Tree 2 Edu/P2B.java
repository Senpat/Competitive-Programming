//make sure to make new file!
import java.io.*;
import java.util.*;

public class P2B{
   
   public static long MOD = 1000000007L;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int q = Integer.parseInt(st.nextToken());
      
      Segtree segtree = new Segtree(n);
      segtree.fill(0,0,n-1);
      
      for(int t = 0; t < q; t++){
         st = new StringTokenizer(f.readLine());
         
         int qt = Integer.parseInt(st.nextToken());
         int l = Integer.parseInt(st.nextToken());
         int r = Integer.parseInt(st.nextToken())-1;
         
         if(qt == 1){
            long x = Long.parseLong(st.nextToken());
            
            segtree.mul(0,0,n-1,l,r,x);
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
         a = new long [4*size];
      }
      
      public void fill(int v, int l, int r){
         if(l == r){
            a[v] = 1L;
            ops[v] = 1L;
         } else {
            int mid = (l+r)/2;
            
            fill(2*v+1,l,mid);
            fill(2*v+2,mid+1,r);
            
            ops[v] = 1L;
            a[v] = a[2*v+1] + a[2*v+2];
         }
      }
      
      public void mul(int v, int l, int r, int ql, int qr, long x){
         if(l >= ql && r <= qr){
            ops[v] = (ops[v] * x + MOD)%MOD;
            a[v] = (a[v] * x + MOD)%MOD;
         } else if(r < ql || l > qr){
            return;
         } else {
            int mid = (l+r)/2;
            
            mul(2*v+1,l,mid,ql,qr,x);
            mul(2*v+2,mid+1,r,ql,qr,x);
            
            a[v] = (ops[v] * (a[2*v+1] + a[2*v+2]) + MOD)%MOD;
         }
      }
      
      public long sum(int v, int l, int r, int ql, int qr){
         if(l >= ql && r <= qr){
            return a[v];
         } else if(r < ql || l > qr){
            return 0L;
         } else {
            int mid = (l+r)/2;
            
            return (ops[v] * (sum(2*v+1,l,mid,ql,qr) + sum(2*v+2,mid+1,r,ql,qr)) + MOD)%MOD;
         }
      }
      
   }
      
}