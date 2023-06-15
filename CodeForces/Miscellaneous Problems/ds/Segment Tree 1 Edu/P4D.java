//make sure to make new file!
import java.io.*;
import java.util.*;

public class P4D{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int q = Integer.parseInt(st.nextToken());
      
      int[] array = new int[n];
      st = new StringTokenizer(f.readLine());
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(st.nextToken());
      }
      
      Segtree segtree = new Segtree(n);
      segtree.build(0,0,n-1,array);
      
      for(int t = 0; t < q; t++){
         st = new StringTokenizer(f.readLine());
         
         int i = Integer.parseInt(st.nextToken());
         if(i == 1){
            int l = Integer.parseInt(st.nextToken())-1;
            int r = Integer.parseInt(st.nextToken())-1;
            
            out.println(Long.bitCount(segtree.query(0,0,n-1,l,r)));
         } else {
            int index = Integer.parseInt(st.nextToken())-1;
            int x = Integer.parseInt(st.nextToken());
            
            segtree.set(0,0,n-1,index,x);
         }
      }
      
      
      
      
      
      
      
      out.close();
   }
   
   public static class Segtree{
   
      //mask that stores if that segment contains that number
      //answer is # of bits in the long
      private long[] a;
      
      public Segtree(int size){
         a = new long[4*size];
      }
      
      public void build(int v, int l, int r, int[] array){
         if(l == r){
            a[v] = (1L << array[l]);
         } else {
            int mid = (l+r)/2;
            
            build(2*v+1,l,mid,array);
            build(2*v+2,mid+1,r,array);
            
            a[v] = a[2*v+1] | a[2*v+2];
         }
      }
      
      public void set(int v, int l, int r, int i, int x){
         if(l == r){
            a[v] = (1L << x);
         } else {
            int mid = (l+r)/2;
            
            if(i <= mid){
               set(2*v+1,l,mid,i,x);
            } else {
               set(2*v+2,mid+1,r,i,x);
            }
            
            a[v] = a[2*v+1] | a[2*v+2];
         }
      }
      
      public long query(int v, int l, int r, int ql, int qr){
         if(l >= ql && r <= qr){
            return a[v];
         } else if(r < ql || l > qr){
            return 0L;
         } else {
            int mid = (l+r)/2;
            
            long left = query(2*v+1,l,mid,ql,qr);
            long right = query(2*v+2,mid+1,r,ql,qr);
            
            return left | right;
         }
         
      }
   
   }
   
      
}