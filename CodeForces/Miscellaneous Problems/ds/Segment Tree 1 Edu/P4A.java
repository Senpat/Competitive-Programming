//make sure to make new file!
import java.io.*;
import java.util.*;

public class P4A{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      int[] array = new int[n];
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(st.nextToken());
      }
      
      Segtree segtree = new Segtree(n);
      segtree.build(0,0,n-1,array);
      
      int q = Integer.parseInt(f.readLine());
      for(int t = 0; t < q; t++){
         st = new StringTokenizer(f.readLine());
         
         int i = Integer.parseInt(st.nextToken());
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         
         if(i == 0){
            a--;
            segtree.set(0,0,n-1,a,b);
         } else {
            a--;
            b--;
            out.println(segtree.query(0,0,n-1,a,b));
         }
      }
      
      
      
      
      
      
      
      
      
      
      out.close();
   }
   
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
            
            build(2*v+1,l,mid,array);
            build(2*v+2,mid+1,r,array);
            
            a[v] = a[2*v+1];
            if((mid-l+1)%2 == 0) a[v] += a[2*v+2];
            else a[v] -= a[2*v+2];
         }
         
      }
      
      
      public void set(int v, int l, int r, int i, int x){
         if(l == r){
            a[v] = x;
         } else {
            int mid = (l+r)/2;
            
            if(i <= mid){
               set(2*v+1,l,mid,i,x);
            } else {
               set(2*v+2,mid+1,r,i,x);
            }
            
            a[v] = a[2*v+1];
            if((mid-l+1)%2 == 0) a[v] += a[2*v+2];
            else a[v] -= a[2*v+2];
         }
      }
      
      public int query(int v, int l, int r, int ql, int qr){
         if(l >= ql && r <= qr){
            return a[v];
         } else if(r < ql || l > qr){
            return 0;
         } else {
            int mid = (l+r)/2;
            
            int left = query(2*v+1,l,mid,ql,qr);
            int right = query(2*v+2,mid+1,r,ql,qr);
            
            int leftsize = 0;
            if(mid >= ql){
               leftsize = mid - Math.max(l,ql) + 1;
            }
            
            int ret = left;
            if(leftsize % 2 == 0) ret += right;
            else ret -= right;
            return ret;
         }
      }
      
   }
   
      
}