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
      
      Segtree segtree = new Segtree(n);
      for(int t = 0; t < q; t++){
         st = new StringTokenizer(f.readLine());
         
         int qt = Integer.parseInt(st.nextToken());
         if(qt == 1){
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken())-1;
            int x = Integer.parseInt(st.nextToken());
            
            segtree.assign(0,0,n-1,l,r,x);
         } else {
            int i = Integer.parseInt(st.nextToken());
            
            int answer = segtree.query(0,0,n-1,i);
            if(answer == segtree.NOOP) answer = 0;
            out.println(answer);
         }
         
      }
      
      
      out.close();
   }
   
   
   public static class Segtree{
   
      final int NOOP = Integer.MAX_VALUE;
      private int[] ops;
      
      public Segtree(int size){
         ops = new int[4*size];
         Arrays.fill(ops,NOOP);
      }
      
      private void propagate(int v){
         if(ops[v] != NOOP){
            ops[2*v+1] = ops[v];
            ops[2*v+2] = ops[v];
            ops[v] = NOOP;
         }
      }
      
      public void assign(int v, int l, int r, int ql, int qr, int x){
         if(l >= ql && r <= qr){
            ops[v] = x;
         } else if(r < ql || l > qr){
            return;
         } else {
            propagate(v);
            
            int mid = (l+r)/2;
            
            assign(2*v+1,l,mid,ql,qr,x);
            assign(2*v+2,mid+1,r,ql,qr,x);
         }
      }
      
      public int query(int v, int l, int r, int i){
         if(l == r){
            return ops[v];
         } else {
            propagate(v);
            
            int mid = (l+r)/2;
            
            if(i <= mid){
               return query(2*v+1,l,mid,i);
            } else {
               return query(2*v+2,mid+1,r,i);
            }
         }
      }
      
   }
   
      
}