//make sure to make new file!
import java.io.*;
import java.util.*;

public class P2B{
   
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
         int i2 = Integer.parseInt(st.nextToken());
         if(i == 1){
            segtree.set(0,0,n-1,i2);
         } else {
            out.println(segtree.xth(0,0,n-1,i2+1));
         }
      }
      
      
      
      
      
      
      
      out.close();
   }
   
   
   public static class Segtree{
      
      //stores # of 1s in that interval
      private int[] a;
      
      public Segtree(int n){
         a = new int[4*n];
      }
      
      public void build(int v, int l, int r, int[] array){
         if(l == r){
            a[v] = array[l];
         } else {
            int mid = (l+r)/2;
            build(2*v+1,l,mid,array);
            build(2*v+2,mid+1,r,array);
            
            a[v] = a[2*v+1] + a[2*v+2];
         }
      }
      
      public void set(int v, int l, int r, int i){
         if(l == r){
            a[v] = 1-a[v];
         } else {
            int mid = (l+r)/2;
            if(i <= mid){
               set(2*v+1,l,mid,i);
            } else {
               set(2*v+2,mid+1,r,i);
            }
            
            a[v] = a[2*v+1]+a[2*v+2];
         }
      }
      
      public int xth(int v, int l, int r, int x){
         if(l == r){
            return l;
         } else {
            int mid = (l+r)/2;
            if(x <= a[2*v+1]){
               return xth(2*v+1,l,mid,x);
            } else {
               return xth(2*v+2,mid+1,r,x-a[2*v+1]);
            }
         }
      }
      
   }
      
}