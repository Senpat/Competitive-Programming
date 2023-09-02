//Flip (Gym)
import java.io.*;
import java.util.*;

public class F103373{
   
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
         
         //flip every other element, now you are looking for segments of 1s or 0s
         if(k % 2 == 1){
            array[k] = 1-array[k];
         }
      }
      
      Segtree segtree = new Segtree(n);
      segtree.build(0,0,n-1,array);
      
      for(int t = 0; t < q; t++){
         st = new StringTokenizer(f.readLine());
         
         int qt = Integer.parseInt(st.nextToken());
         int l = Integer.parseInt(st.nextToken())-1;
         int r = Integer.parseInt(st.nextToken())-1;
         
         if(qt == 1){
            segtree.flip(0,0,n-1,l,r);
         } else {
            Node node = segtree.query(0,0,n-1,l,r);
            out.println(node.answer);
         }
      }
      
      
      
      
      
      
      
      
      out.close();
   }
   
   
   public static class Segtree{
   
      private boolean[] ops;
      private Node[] a;
      
      public Segtree(int size){
         a = new Node[4*size];
         ops = new boolean[4*size];
      }
      
      private Node combine(Node l, Node r){
         if(l.answer == -1) return r;
         if(r.answer == -1) return l;
         
         if(l.last == r.first){
            long answer = l.answer + r.answer;
            answer -= getans(l.lenlast) + getans(r.lenfirst);
            answer += getans(l.lenlast+r.lenfirst);
            
            long lenfirst = l.lenfirst;
            if(l.lenfirst == l.lentotal){
               lenfirst += r.lenfirst;
            }
            long lenlast = r.lenlast;
            if(r.lenlast == r.lentotal){
               lenlast += l.lenlast;
            }
            
            return new Node(answer,l.first,r.last,lenfirst,lenlast,l.lentotal+r.lentotal);
            
         } else {
            return new Node(l.answer+r.answer,l.first,r.last,l.lenfirst,r.lenlast,l.lentotal+r.lentotal);
         }
      }
      
      private void propagate(int v){
         if(!ops[v]) return;
         
         ops[2*v+1] = !ops[2*v+1];
         a[2*v+1].flip();
         
         ops[2*v+2] = !ops[2*v+2];
         a[2*v+2].flip();
         
         ops[v] = false;
      }
      
      public void build(int v, int l, int r, int[] array){
         if(l == r){
            a[v] = new Node(1L,array[l],array[l],1L,1L,1L);
         } else {
            int mid = (l+r)/2;
            
            build(2*v+1,l,mid,array);
            build(2*v+2,mid+1,r,array);
            
            a[v] = combine(a[2*v+1],a[2*v+2]);
         }
      }
      
      public void flip(int v, int l, int r, int ql, int qr){
         if(l >= ql && r <= qr){
            a[v].flip();
            ops[v] = !ops[v];
         } else if(r < ql || l > qr){
            return;
         } else {
            propagate(v);
            
            int mid = (l+r)/2;
            
            flip(2*v+1,l,mid,ql,qr);
            flip(2*v+2,mid+1,r,ql,qr);
            
            a[v] = combine(a[2*v+1],a[2*v+2]);
         }
         
      }
      
      //# of alternating subarrays
      public Node query(int v, int l, int r, int ql, int qr){
         if(l >= ql && r <= qr){
            return a[v];
         } else if(r < ql || l > qr){
            return new Node(-1,0,0,0,0,0);
         } else {
            propagate(v);
            
            int mid = (l+r)/2;
            
            Node lnode = query(2*v+1,l,mid,ql,qr);
            Node rnode = query(2*v+2,mid+1,r,ql,qr);
            
            return combine(lnode,rnode);
         
         }
      
      
      }
      
   }
   
   public static class Node{
   
      long answer;
      int first;
      int last;
      long lenfirst;
      long lenlast;
      long lentotal;
      
      public Node(long a, int b, int c, long d, long e, long f){
         answer = a;
         first = b;
         last = c;
         lenfirst = d;
         lenlast = e;
         lentotal = f;
      }
      
      public void flip(){
         first = 1-first;
         last = 1-last;
      }
   
   }
   
   public static long getans(long len){
      return (len+1)*len/2L;
   }
   
   
}