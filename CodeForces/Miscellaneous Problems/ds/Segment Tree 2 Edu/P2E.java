//make sure to make new file!
import java.io.*;
import java.util.*;

public class P2E{
   
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
         int l = Integer.parseInt(st.nextToken());
         int r = Integer.parseInt(st.nextToken())-1;
         
         if(qt == 1){
            int x = Integer.parseInt(st.nextToken());
            
            segtree.assign(0,0,n-1,l,r,x);
         } else {
            out.println(segtree.min(0,0,n-1,l,r));
         }
      }
      
      
      
      
      
      
      out.close();
   }
   
   
   public static class Segtree{
      
      private final int NOOP = Integer.MAX_VALUE;
      private int[] ops;
      private int[] a;
      
      public Segtree(int size){
         ops = new int[4*size];
         Arrays.fill(ops,NOOP);
         a = new int[4*size];
      }
      
      private void propagate(int v){
         if(ops[v] == NOOP) return;
         ops[2*v+1] = ops[v];
         a[2*v+1] = ops[v];
         ops[2*v+2] = ops[v];
         a[2*v+2] = ops[v];
         
         ops[v] = NOOP;
      }
      
      public void assign(int v, int l, int r, int ql, int qr, int x){
         if(l >= ql && r <= qr){
            ops[v] = x;
            a[v] = x;
         } else if(r < ql || l > qr){
            return;
         } else {
            propagate(v);
            
            int mid = (l+r)/2;
            
            assign(2*v+1,l,mid,ql,qr,x);
            assign(2*v+2,mid+1,r,ql,qr,x);
            
            a[v] = Math.min(a[2*v+1],a[2*v+2]);
         }
      }
      
      public int min(int v, int l, int r, int ql, int qr){
         if(l >= ql && r <= qr){
            return a[v];
         } else if(r < ql || l > qr){
            return Integer.MAX_VALUE;
         } else {
            propagate(v);
            
            int mid = (l+r)/2;
            
            return Math.min(min(2*v+1,l,mid,ql,qr),min(2*v+2,mid+1,r,ql,qr));
         }
      }
      
   }
      
}