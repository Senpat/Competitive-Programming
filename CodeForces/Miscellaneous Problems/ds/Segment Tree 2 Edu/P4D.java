//make sure to make new file!
import java.io.*;
import java.util.*;

public class P4D{
   
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
      
      for(int t = 0; t < q; t++){
         st = new StringTokenizer(f.readLine());
         
         int type = Integer.parseInt(st.nextToken());
         int l = Integer.parseInt(st.nextToken())-1;
         int r = Integer.parseInt(st.nextToken())-1;
         
         if(type == 1){
            long d = Long.parseLong(st.nextToken());
            
            segtree.update(0,0,n-1,l,r,d);
         } else {
            Node node = segtree.query(0,0,n-1,l,r);
            
            long answer = node.wsum - l*node.sum;
            out.println(answer);
         }
      }
      
      
      
      
      
      
      out.close();
   }
   
   public static class Segtree{
   
      private long[] ops;
      private Node[] a;
      
      public Segtree(int size){
         a = new Node[4*size];
         for(int k = 0; k < a.length; k++){
            a[k] = new Node(0L,0L);
         }
         
         ops = new long[4*size];
      }
      
      public Node combine(Node node1, Node node2){
         return new Node(node1.wsum + node2.wsum, node1.sum + node2.sum);
      }
      
      public void propagate(int v, int l, int r){
         int mid = (l+r)/2;
         ops[2*v+1] += ops[v];
         a[2*v+1].update(ops[v],l,mid);
         ops[2*v+2] += ops[v];
         a[2*v+2].update(ops[v],mid+1,r);
         
         ops[v] = 0L;
      }
      
      public void build(int v, int l, int r, long[] array){
         if(l == r){
            a[v] = new Node((long)(l+1) * array[l],array[l]);
         } else {
            int mid = (l+r)/2;
            
            build(2*v+1,l,mid,array);
            build(2*v+2,mid+1,r,array);
            
            a[v] = combine(a[2*v+1],a[2*v+2]);
         }
      }
      
      public void update(int v, int l, int r, int ql, int qr, long d){
         if(l >= ql && r <= qr){
            ops[v] += d;
            a[v].update(d,l,r);
         } else if(r < ql || l > qr){
            return;
         } else {
            int mid = (l+r)/2;
            
            propagate(v,l,r);
            
            update(2*v+1,l,mid,ql,qr,d);
            update(2*v+2,mid+1,r,ql,qr,d);
            
            a[v] = combine(a[2*v+1],a[2*v+2]);
         }
         
      
      }
   
      public Node query(int v, int l, int r, int ql, int qr){
         if(l >= ql && r <= qr){
            return a[v];
         } else if(r < ql || l > qr){
            return new Node(0L,0L);
         } else {
            int mid = (l+r)/2;
            
            propagate(v,l,r);
            
            return combine(query(2*v+1,l,mid,ql,qr),query(2*v+2,mid+1,r,ql,qr));
            
         }
      }
   
   }
   
   public static class Node{
      long wsum;           //weighted sum (each element multiplied by its one-indexed index)
      long sum;
      
      public Node(long a, long b){
         wsum = a;
         sum = b;
      }
      
      public void update(long d, long l, long r){
         wsum += d * (r+1 + l+1) * (r+1 - (l+1) +1) / 2L;         //one-indexed
         sum += d * (r-l+1);
      }
   }
      
}