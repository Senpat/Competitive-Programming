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
      
      Segtree segtree = new Segtree(n);
      for(int t = 0; t < q; t++){
         st = new StringTokenizer(f.readLine());
         
         int qt = Integer.parseInt(st.nextToken());
         if(qt == 1){
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken())-1;
            int x = Integer.parseInt(st.nextToken());
            
            segtree.max(0,0,n-1,l,r,x);
         } else {
            int i = Integer.parseInt(st.nextToken());
            
            out.println(segtree.query(0,0,n-1,i));
         }
         
      }
      

      
      out.close();
   }
   
   public static class Segtree{
   
      private int[] ops;
      
      public Segtree(int size){
         ops = new int[4*size];
      }
      
      public void max(int v, int l, int r, int ql, int qr, int x){
         if(l >= ql && r <= qr){
            ops[v] = Math.max(ops[v],x);
         } else if(r < ql || l > qr){
            return;
         } else {
            int mid = (l+r)/2;
            
            max(2*v+1,l,mid,ql,qr,x);
            max(2*v+2,mid+1,r,ql,qr,x);
         }
      }
      
      public int query(int v, int l, int r, int i){
         if(l == r){
            return ops[v];
         } else {
            int mid = (l+r)/2;
            
            if(i <= mid){
               return Math.max(ops[v],query(2*v+1,l,mid,i));
            } else {
               return Math.max(ops[v],query(2*v+2,mid+1,r,i));
            }
         }
      }
   }
   
      
}