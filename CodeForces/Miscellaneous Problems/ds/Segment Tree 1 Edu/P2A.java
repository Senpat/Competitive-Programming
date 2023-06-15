//make sure to make new file!
import java.io.*;
import java.util.*;

public class P2A{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int q = Integer.parseInt(st.nextToken());
      
      st = new StringTokenizer(f.readLine());
      long[] array = new long[n];
      for(int k = 0; k < n; k++){
         array[k] = Long.parseLong(st.nextToken());
      }
      
      Segtree segtree = new Segtree(n);
      segtree.build(0,0,n-1,array);
      
      out.println(segtree.get());
      
      for(int t = 0; t < q; t++){
         st = new StringTokenizer(f.readLine());
         
         int i = Integer.parseInt(st.nextToken());
         long x = Long.parseLong(st.nextToken());
         
         segtree.set(0,0,n-1,i,x);
         out.println(segtree.get());
      }
      
      
      
      
      
      
      
      
      out.close();
   }
   
   
   public static class Segtree{
      
      private Node[] a;
      
      public Segtree(int n){
         a = new Node[4*n];
      }
      
      private Node genNode(long x){
         if(x <= 0) 
            return new Node(0,0,0,x);
         else 
            return new Node(x,x,x,x);
      }
      
      private void combine(int v){
         Node left = a[2*v+1];
         Node right = a[2*v+2];
         
         long max = Math.max(left.max,right.max);
         max = Math.max(max,left.suff+right.pref);
         long pref = Math.max(left.pref,left.sum + right.pref);
         long suff = Math.max(right.suff,right.sum + left.suff);
         long sum = left.sum + right.sum;
         
         a[v] = new Node(max,pref,suff,sum);
      }
      
      public void build(int v, int l, int r, long[] array){
         if(l == r){
            a[v] = genNode(array[l]);
         } else {
            int mid = (l+r)/2;
            build(2*v+1,l,mid,array);
            build(2*v+2,mid+1,r,array);
            
            combine(v);
         }
      }
      
      public void set(int v, int l, int r, int i, long x){
         if(l == r){
            a[v] = genNode(x);
         } else {
            int mid = (l+r)/2;
            if(i <= mid){
               set(2*v+1,l,mid,i,x);
            } else {
               set(2*v+2,mid+1,r,i,x);
            }
            
            //combine
            combine(v);
         }
         
      }
      
      public long get(){
         return a[0].max;
      }
      
   }
   
   
   
   public static class Node{
      long max;
      long pref;
      long suff;
      long sum;
      
      public Node(long a, long b, long c, long d){
         max = a;
         pref = b;
         suff = c;
         sum = d;
      }
   }
   
      
}