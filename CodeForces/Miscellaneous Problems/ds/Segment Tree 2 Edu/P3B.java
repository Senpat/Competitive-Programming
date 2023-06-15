//make sure to make new file!
import java.io.*;
import java.util.*;

public class P3B{
   
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
            
            segtree.flip(0,0,n-1,l,r);
         } else {
            int x = Integer.parseInt(st.nextToken())+1;
            
            out.println(segtree.xth(0,0,n-1,x));
         }
      }
      
      
      
      
      
      
      
      out.close();
   }
   
   public static class Segtree{
   
      private short[] flipped;  //0 for false, 1 for true
      private int[] a;        //stores sum
      
      public Segtree(int size){
         a = new int[4*size];
         flipped = new short[4*size];
      }
      
      public void propagate(int v, int lenl, int lenr){
         if(flipped[v] == 0) return;
         
         flipped[2*v+1] ^= 1;
         a[2*v+1] = lenl-a[2*v+1];
         flipped[2*v+2] ^= 1;
         a[2*v+2] = lenr-a[2*v+2];
         
         flipped[v] = 0;
      }
      
      public void flip(int v, int l, int r, int ql, int qr){
         if(l >= ql && r <= qr){
            flipped[v] ^= 1;
            a[v] = (r-l+1)-a[v];
         } else if(r < ql || l > qr){
            return;
         } else {
            int mid = (l+r)/2;
            
            int lenl = mid-l+1;
            int lenr = r-(mid+1)+1;
            
            propagate(v,lenl,lenr);
            
            flip(2*v+1,l,mid,ql,qr);
            flip(2*v+2,mid+1,r,ql,qr);
            
            a[v] = a[2*v+1] + a[2*v+2];
         }
      }
      
      //gets index of xth 1
      public int xth(int v, int l, int r, int x){
         if(l == r){
            return l;
         } else {
            int mid = (l+r)/2;
            
            int lenl = mid-l+1;
            int lenr = r-(mid+1)+1;
            
            propagate(v,lenl,lenr);
            
            if(a[2*v+1] >= x){
               return xth(2*v+1,l,mid,x);
            } else {
               return xth(2*v+2,mid+1,r,x-a[2*v+1]);
            }
         }
      }
   
   }
   
}