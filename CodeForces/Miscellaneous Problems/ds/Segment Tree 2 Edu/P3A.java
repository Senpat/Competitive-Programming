//make sure to make new file!
import java.io.*;
import java.util.*;

public class P3A{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int q = Integer.parseInt(st.nextToken());
      
      Segtree segtree = new Segtree(n);
      for(int t = 0; t < q; t++){
         st = new StringTokenizer(f.readLine());
         
         int l = Integer.parseInt(st.nextToken());
         int r = Integer.parseInt(st.nextToken())-1;
         long x = Long.parseLong(st.nextToken());
         
         segtree.assign(0,0,n-1,l,r,x);
         out.println(segtree.max_segment());
      }
      
      
      
      
      
      
      
      out.close();
   }
   
   public static class Segtree{
      
      private final long NOOP = Long.MAX_VALUE;
      private long[] ops;
      private Node[] a;
      
      public Segtree(int size){
         a = new Node[4*size];
         Arrays.fill(a,new Node(0,0,0,0));
         ops = new long[4*size];
         Arrays.fill(ops,NOOP);
      }
      
      public Node constructNode(long x, long len){
         if(x <= 0){
            return new Node(0,0,0,len*x);
         } else {
            return new Node(len*x,len*x,len*x,len*x);
         }
      }
      
      public void propagate(int v, long lenl, long lenr){
         if(ops[v] == NOOP) return;
         
         ops[2*v+1] = ops[v];
         a[2*v+1] = constructNode(ops[v],lenl);
         ops[2*v+2] = ops[v];
         a[2*v+2] = constructNode(ops[v],lenr);
         
         ops[v] = NOOP;
         
      }
      
      public Node combine(Node left, Node right){
         
         long pref = Math.max(left.pref,left.sum + right.pref);
         long suff = Math.max(right.suff,right.sum + left.suff);
         long sum = left.sum + right.sum;
         long maxseg = Math.max(left.maxseg,right.maxseg);
         maxseg = Math.max(maxseg,Math.max(pref,suff));
         maxseg = Math.max(maxseg,left.suff + right.pref);
         
         return new Node(maxseg,pref,suff,sum);
      
      }
      
      public void assign(int v, int l, int r, int ql, int qr, long x){
         long len = (long)(r-l+1);
         if(l >= ql && r <= qr){
            ops[v] = x;
            a[v] = constructNode(x,len);
         } else if(r < ql || l > qr){
            return;
         } else {
            int mid = (l+r)/2;
            
            long lenl = (long)(mid-l+1);
            long lenr = (long)(r-(mid+1)+1);
            
            propagate(v,lenl,lenr);
            
            assign(2*v+1,l,mid,ql,qr,x);
            assign(2*v+2,mid+1,r,ql,qr,x);
            
            a[v] = combine(a[2*v+1],a[2*v+2]);  
         }
      }
      
      public long max_segment(){
         return a[0].maxseg;
      }
      
   }
   
   public static class Node{
      long maxseg;
      long pref;
      long suff;
      long sum;
      
      public Node(long a, long b, long c, long d){
         maxseg = a;
         pref = b;
         suff = c;
         sum = d;
      }
   }
    
}