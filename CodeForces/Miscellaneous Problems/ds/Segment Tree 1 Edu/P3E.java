//make sure to make new file!
import java.io.*;
import java.util.*;

public class P3E{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int q = Integer.parseInt(st.nextToken());
      
      Segtree segtree = new Segtree(n);
      
      for(int t = 0; t < q; t++){
         st = new StringTokenizer(f.readLine());
         int i = Integer.parseInt(st.nextToken());
         
         if(i == 1){
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            
            segtree.update(0,0,n-1,l,v);
            if(r < n) segtree.update(0,0,n-1,r,-v);
         } else {
            int index = Integer.parseInt(st.nextToken());
            out.println(segtree.sum(0,0,n-1,0,index));
         }
      }
      
      
      
      
      
      
      
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
      
      public void update(int v, int l, int r, int i, long x){
         //i is always in the range
         if(l == r){
            a[v] += x;
         } else {
            int mid = (l+r)/2;
            if(i <= mid){
               //go left
               update(2*v+1,l,mid,i,x);
            } else {
               //go right
               update(2*v+2,mid+1,r,i,x);
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

   
      
}