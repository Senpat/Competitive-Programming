//make sure to make new file!
import java.io.*;
import java.util.*;

public class P3B{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      int[] array = new int[n];
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(st.nextToken());
      }
      
      Segtree segtree = new Segtree(n+1);
      segtree.fill1(0,0,n);
      
      int[] answer = new int[n];
      for(int k = n-1; k >= 0; k--){
         answer[k] = segtree.xth(0,0,n,array[k]);
         segtree.set(0,0,n,answer[k],0);
      }
      
      StringJoiner sj = new StringJoiner(" ");
      for(int k = 0; k < n; k++){
         sj.add("" + answer[k]);
      }
      out.println(sj.toString());
      
      
      
      
      
      
      
      
      
      out.close();
   }
   
   //gets xth 1 from the right (0 indexed)
   public static class Segtree{
   
      //stores # of 1s in segment
      private int[] a;
      
      public Segtree(int n){
         a = new int[4*n];
      }
      
      //initially they are all 1
      public void fill1(int v, int l, int r){
         if(l == r){
            a[v] = 1;
         } else {
            int mid = (l+r)/2;
            
            fill1(2*v+1,l,mid);
            fill1(2*v+2,mid+1,r);
            
            a[v] = a[2*v+1] + a[2*v+2];
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
            
            a[v] = a[2*v+1] + a[2*v+2];
         }
      }
      
      public int xth(int v, int l, int r, int x){
         if(l == r){
            return l;
         } else {
            int mid = (l+r)/2;
            
            if(a[2*v+2] > x){
               return xth(2*v+2,mid+1,r,x);
            } else {
               return xth(2*v+1,l,mid,x-a[2*v+2]);
            }
         }
      }
         
      
   }
   
      
}