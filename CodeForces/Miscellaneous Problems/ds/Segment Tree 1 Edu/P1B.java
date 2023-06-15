//make sure to make new file!
import java.io.*;
import java.util.*;

public class P1B{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int q = Integer.parseInt(st.nextToken());
      
      st = new StringTokenizer(f.readLine());
      int[] array = new int[n];
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
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken())-1;
            
            int answer = segtree.min(0,0,n-1,l,r);
            out.println(answer);
         }
      }
      
      
      
      
      
      
      
      out.close();
   }
   
   //point update, range min query
   //to call, v = 0, l = 0, r = n-1
   //l,r and ql,qr are inclusive
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
            //left
            build(2*v+1,l,mid,array);
            //right
            build(2*v+2,mid+1,r,array);
            
            a[v] = Math.min(a[2*v+1],a[2*v+2]);
         }
      }
      
      public void set(int v, int l, int r, int i, int x){
         if(l == r){
            a[v] = x;
         } else {
            int mid = (l+r)/2;
            if(i <= mid){
               //go left
               set(2*v+1,l,mid,i,x);
            } else {
               //go right
               set(2*v+2,mid+1,r,i,x);
            }
            
            a[v] = Math.min(a[2*v+1],a[2*v+2]);
         }
      }
      
      public int min(int v, int l, int r, int ql, int qr){
         if(l >= ql && r <= qr){
            return a[v];
         } else if(r < ql || l > qr){
            return Integer.MAX_VALUE;
         } else {
            int mid = (l+r)/2;
            //left
            int lmin = min(2*v+1,l,mid,ql,qr);
            //right
            int rmin = min(2*v+2,mid+1,r,ql,qr);
            return Math.min(lmin,rmin);
         }
      }
      
   }
   
      
}