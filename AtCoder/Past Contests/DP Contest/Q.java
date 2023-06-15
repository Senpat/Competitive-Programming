//make sure to make new file!
import java.io.*;
import java.util.*;

public class Q{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st1 = new StringTokenizer(f.readLine());
      StringTokenizer st2 = new StringTokenizer(f.readLine());
      
      int[] h = new int[n];
      long[] array = new long[n];
      for(int k = 0; k < n; k++){
         h[k] = Integer.parseInt(st1.nextToken());
         array[k] = Long.parseLong(st2.nextToken());
      }
      
      Segtree segtree = new Segtree(n+1);
      long answer = 0L;
      for(int k = 0; k < n; k++){
         long cur = segtree.max(0,0,n,0,h[k]-1) + array[k];
         answer = Math.max(answer,cur);
         
         segtree.set(0,0,n,h[k],cur);
      }
      
      out.println(answer);
      
      
      
      
      
      out.close();
   }
   
   //point update, range max query
   //to call, v = 0, l = 0, r = n-1
   //l,r and ql,qr are inclusive
   public static class Segtree{
      
      private long[] a;
      
      public Segtree(int size){
         a = new long[4*size];
      }
      
      public void build(int v, int l, int r, long[] array){
         if(l == r){
            a[v] = array[l];
         } else {
            int mid = (l+r)/2;
            //left
            build(2*v+1,l,mid,array);
            //right
            build(2*v+2,mid+1,r,array);
            
            a[v] = Math.max(a[2*v+1],a[2*v+2]);
         }
      }
      
      public void set(int v, int l, int r, int i, long x){
         if(l == r){
            a[v] = Math.max(a[v],x);
         } else {
            int mid = (l+r)/2;
            if(i <= mid){
               //go left
               set(2*v+1,l,mid,i,x);
            } else {
               //go right
               set(2*v+2,mid+1,r,i,x);
            }
            
            a[v] = Math.max(a[2*v+1],a[2*v+2]);
         }
      }
      
      public long max(int v, int l, int r, int ql, int qr){
         if(l >= ql && r <= qr){
            return a[v];
         } else if(r < ql || l > qr){
            return 0L;
         } else {
            int mid = (l+r)/2;
            //left
            long lmax = max(2*v+1,l,mid,ql,qr);
            //right
            long rmax = max(2*v+2,mid+1,r,ql,qr);
            return Math.max(lmax,rmax);
         }
      }
      
   }
      
}