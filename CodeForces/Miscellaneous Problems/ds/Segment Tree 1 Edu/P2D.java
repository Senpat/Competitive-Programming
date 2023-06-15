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
            int index = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            
            segtree.set(0,0,n-1,index,x);
         } else {
            int x = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
            
            out.println(segtree.first(0,0,n-1,l,x));
         }
      }
      
      
      
      
      
      
      
      
      out.close();
   }
   
   
   
   public static class Segtree{
   
      //stores max value
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
            
            a[v] = Math.max(a[2*v+1],a[2*v+2]);
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
            
            a[v] = Math.max(a[2*v+1],a[2*v+2]);
         }
      }
      
      public int first(int v, int l, int r, int ql, int x){
         if(r < ql || a[v] < x){
            return -1;
         } else if(l == r){
            return l;
         } else {
            int mid = (l+r)/2;
            
            int left = first(2*v+1,l,mid,ql,x);
            if(left != -1) return left;
            
            return first(2*v+2,mid+1,r,ql,x);
         }
         
      }
   
   }
   
}