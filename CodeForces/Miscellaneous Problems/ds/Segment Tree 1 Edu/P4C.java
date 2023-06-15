//make sure to make new file!
import java.io.*;
import java.util.*;

public class P4C{
   
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
            int l = Integer.parseInt(st.nextToken())-1;
            int r = Integer.parseInt(st.nextToken())-1;
            
            out.println(segtree.query(0,0,n-1,l,r).inversions);
         } else {
            int index = Integer.parseInt(st.nextToken())-1;
            int x = Integer.parseInt(st.nextToken());
            
            segtree.set(0,0,n-1,index,x);
         }
      }
      
      
      
      
      
      
      
      
      
      out.close();
   }
   
   
   public static class Segtree{
      
      private Node[] a;
      
      public Segtree(int size){
         a = new Node[4*size];
      }
      
      public Node combine(Node left, Node right){
         Node ret = new Node();
         
         ret.inversions = left.inversions + right.inversions;
         long lefttotal = left.num;
         for(int k = 1; k <= 40; k++){
            lefttotal -= left.freq[k];
            ret.inversions += lefttotal * right.freq[k];
            
            ret.freq[k] = left.freq[k] + right.freq[k];
         }
         
         ret.num = left.num + right.num;
         
         return ret;
      }
      
      public void build(int v, int l, int r, int[] array){
         if(l == r){
            a[v] = new Node(array[l]);
         } else {
            int mid = (l+r)/2;
            
            build(2*v+1,l,mid,array);
            build(2*v+2,mid+1,r,array);
            
            a[v] = combine(a[2*v+1],a[2*v+2]);
         }
      }
      
      public void set(int v, int l, int r, int i, int x){
         if(l == r){
            a[v] = new Node(x);
         } else {
            int mid = (l+r)/2;
            
            if(i <= mid){
               set(2*v+1,l,mid,i,x);
            } else {
               set(2*v+2,mid+1,r,i,x);
            }
            
            a[v] = combine(a[2*v+1],a[2*v+2]);
         }
      }
         
      public Node query(int v, int l, int r, int ql, int qr){
         if(l >= ql && r <= qr){
            return a[v];
         } else if(r < ql || l > qr){
            return new Node();
         } else {
            int mid = (l+r)/2;
               
            return combine(query(2*v+1,l,mid,ql,qr),query(2*v+2,mid+1,r,ql,qr));
         }
      }
      
      
      
   }
   
   
   
   
   public static class Node{
      long inversions;
      long[] freq;
      long num;
      public Node(){
         inversions = 0L;
         freq = new long[41];
         num = 0L;
      }
      public Node(int x){
         inversions = 0L;
         freq = new long[41];
         freq[x] = 1;
         num = 1L;
      }
   }
   
      
}