//make sure to make new file!
import java.io.*;
import java.util.*;

public class F343{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int q = Integer.parseInt(st.nextToken());
      
      st = new StringTokenizer(f.readLine());
      int[] array = new int[n];
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(st.nextToken());
      }
      
      Segtree segtree = new Segtree(n);
      segtree.build(0,0,n-1,array);
      
      for(int t = 0; t < q; t++){
         st = new StringTokenizer(f.readLine());
         
         int qt = Integer.parseInt(st.nextToken());
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         
         if(qt == 1){
            a--;
            segtree.update(0,0,n-1,a,b);
         } else {
            a--;
            b--;
            Node node = segtree.query(0,0,n-1,a,b);
            out.println(node.max2f);
         }
      }
      
      
      
      
      
      
      
      out.close();
   }
   
   
   public static class Segtree{
      private Node[] a;
      
      public Segtree(int size){
         a = new Node[4*size];
      }
      
      private Node combine(Node n1, Node n2){
         int max = Math.max(n1.max,n2.max);
         int maxf = 0;
         int max2 = 0;
         if(n1.max == max){
            maxf += n1.maxf;
            max2 = Math.max(max2,n1.max2);
         } else {
            max2 = Math.max(max2,n1.max);
         }
         if(n2.max == max){
            maxf += n2.maxf;
            max2 = Math.max(max2,n2.max2);
         } else {
            max2 = Math.max(max2,n2.max);
         }
         
         int max2f = 0;
         if(n1.max == max2) max2f += n1.maxf;
         if(n1.max2 == max2) max2f += n1.max2f;
         if(n2.max == max2) max2f += n2.maxf;
         if(n2.max2 == max2) max2f += n2.max2f;
         
         return new Node(max,maxf,max2,max2f);
      }
      
      public void build(int v, int l, int r, int[] array){
         if(l == r){
            a[v] = new Node(array[l],1,0,0);
         } else {
            int mid = (l+r)/2;
            
            build(2*v+1,l,mid,array);
            build(2*v+2,mid+1,r,array);
            
            a[v] = combine(a[2*v+1],a[2*v+2]);
         }
      }
      
      public void update(int v, int l, int r, int i, int x){
         if(l == r){
            a[v] = new Node(x,1,0,0);
         } else {
            int mid = (l+r)/2;
            
            if(i <= mid) update(2*v+1,l,mid,i,x);
            else update(2*v+2,mid+1,r,i,x);
            
            a[v] = combine(a[2*v+1],a[2*v+2]);
         }
      }
      
      public Node query(int v, int l, int r, int ql, int qr){
         if(l >= ql && r <= qr){
            return a[v];
         } else if(ql > r || qr < l){
            return new Node(0,0,0,0);
         } else {
            int mid = (l+r)/2;
            
            return combine(query(2*v+1,l,mid,ql,qr),query(2*v+2,mid+1,r,ql,qr));
         }
      }
            
   }
    
    
   public static class Node{
      int max;
      int maxf;
      int max2;
      int max2f;
      
      public Node(int a, int b, int c, int d){
         max = a;
         maxf = b;
         max2 = c;
         max2f = d;
      }
   }  
}