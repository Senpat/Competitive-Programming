//make sure to make new file!
import java.io.*;
import java.util.*;

public class P3C{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      int[] array = new int[2*n];
      int[][] ranges = new int[n+1][2];         //0 -> start, 1 -> end
      boolean[] isend = new boolean[2*n];
      for(int k = 0; k <= n; k++){
         ranges[k][0] = -1;
         ranges[k][1] = -1;
      }
      
      for(int k = 0; k < 2*n; k++){
         array[k] = Integer.parseInt(st.nextToken());
         
         if(ranges[array[k]][0] != -1){
            ranges[array[k]][1] = k;
            isend[k] = true;
         } else {
            ranges[array[k]][0] = k;
         }
      }
      
      Segtree segtree = new Segtree(2*n);
      int[] answer = new int[n+1];
      
      for(int k = 0; k < 2*n; k++){
         if(isend[k]){
            answer[array[k]] = segtree.sum(0,0,2*n-1,ranges[array[k]][0],ranges[array[k]][1]);
            segtree.set(0,0,2*n-1,ranges[array[k]][0],1);
         }
      }
      
      StringJoiner sj = new StringJoiner(" ");
      for(int k = 1; k <= n; k++){
         sj.add("" + answer[k]);
      }
      out.println(sj.toString());
      
      
      
      
      
      
      
      
      
      out.close();
   }
   
   //point update, range sum query
   //to call, v = 0, l = 0, r = n-1
   //l,r and ql,qr are inclusive
   public static class Segtree{
      
      private int n;
      private int[] a;
      
      public Segtree(int size){
         n = size;
         a = new int[4*size];
      }
      
      public void build(int v, int l, int r, int[] array){
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
      
      public void set(int v, int l, int r, int i, int x){
         //i is always in the range
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
            
            a[v] = a[2*v+1]+a[2*v+2];
         }
      }
      
      public int sum(int v, int l, int r, int ql, int qr){
         if(l >= ql && r <= qr){
            //fully within range
            return a[v];
         } else if(r < ql || l > qr){
            //outside range
            return 0;
         } else {
            int mid = (l+r)/2;
            int lsum = sum(2*v+1,l,mid,ql,qr);
            int rsum = sum(2*v+2,mid+1,r,ql,qr);
            return lsum + rsum;
         }
      }
      
   }

      
}