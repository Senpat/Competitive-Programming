//make sure to make new file!
import java.io.*;
import java.util.*;

public class P1C{
   
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
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken())-1;
            
            Pair answer = segtree.query(0,0,n-1,l,r);
            out.println(answer);
         }
      }
      
      
      
      
      
      
      
      
      out.close();
   }
   
   
   public static class Segtree{
      
      //0 -> stores min, 1 -> stores # of occurances
      private int[][] a;
      
      public Segtree(int n){
         a = new int[4*n][2];
      }
      
      private void update(int v){
         int lmin = a[2*v+1][0];
         int lnum = a[2*v+1][1];
         int rmin = a[2*v+2][0];
         int rnum = a[2*v+2][1];
         
         if(lmin < rmin){
            a[v][0] = lmin;
            a[v][1] = lnum;
         } else if(lmin > rmin){
            a[v][0] = rmin;
            a[v][1] = rnum;
         } else {
            a[v][0] = lmin;
            a[v][1] = lnum+rnum;
         }
      }
      
      public void build(int v, int l, int r, int[] array){
         if(l == r){
            a[v][0] = array[l];
            a[v][1] = 1;
         } else {
            int mid = (l+r)/2;
            
            build(2*v+1,l,mid,array);
            build(2*v+2,mid+1,r,array);
            
            update(v);
         }
                  
      }
      
      public void set(int v, int l, int r, int i, int x){
         if(l == r){
            a[v][0] = x;
            a[v][1] = 1;
         } else {
            int mid = (l+r)/2;
            
            if(i <= mid){
               set(2*v+1,l,mid,i,x);
            } else {
               set(2*v+2,mid+1,r,i,x);
            }
            
            update(v);
         }
         
      }
      
      public Pair query(int v, int l, int r, int ql, int qr){
         if(l >= ql && r <= qr){
            return new Pair(a[v][0],a[v][1]);
         } else if(r < ql || l > qr){
            return new Pair(Integer.MAX_VALUE,0);
         } else {
            int mid = (l+r)/2;
            Pair pl = query(2*v+1,l,mid,ql,qr);
            Pair pr = query(2*v+2,mid+1,r,ql,qr);
            
            if(pl.min < pr.min){
               return pl;
            } else if(pr.min < pl.min){
               return pr;
            } else {
               return new Pair(pl.min,pl.num + pr.num);
            }
            
         }
      }
      
   }
   
   public static class Pair{
      int min;
      int num;
      public Pair(int a, int b){
         min = a;
         num = b;
      }
      public String toString(){
         return min + " " + num;
      }
   }
      
}