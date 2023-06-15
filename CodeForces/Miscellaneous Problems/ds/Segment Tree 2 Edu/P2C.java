//make sure to make new file!
import java.io.*;
import java.util.*;

public class P2C{
   
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
            
            segtree.or(0,0,n-1,l,r,x);
         } else {
            out.println(segtree.and(0,0,n-1,l,r));
         }
      }
      
      
      
      
      
      
      
      out.close();
   }
   
   public static class Segtree{
   
      private int[] ops;
      private int[] a;
      public Segtree(int size){
         ops = new int[4*size];
         a = new int[4*size];
      }
      
      public void or(int v, int l, int r, int ql, int qr, int x){
         if(l >= ql && r <= qr){
            ops[v] |= x;
            a[v] |= x;
         } else if(r < ql || l > qr){
            return;
         } else {
            int mid = (l+r)/2;
            
            or(2*v+1,l,mid,ql,qr,x);
            or(2*v+2,mid+1,r,ql,qr,x);
            
            a[v] = ops[v] | (a[2*v+1] & a[2*v+2]);
         }
      }
      
      public int and(int v, int l, int r, int ql, int qr){
         if(l >= ql && r <= qr){
            return a[v];
         } else if(r < ql || l > qr){
            return (1 << 30)-1;
         } else {
            int mid = (l+r)/2;
            
            int andl = and(2*v+1,l,mid,ql,qr);
            int andr = and(2*v+2,mid+1,r,ql,qr);
            
            return ops[v] | (andl & andr);
         }
      }
      
   
   }
   
      
}