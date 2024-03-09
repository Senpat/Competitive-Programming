import java.io.*;
import java.util.*;

class D{
   
   public static void main(String[] args) throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("d_full.txt"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("d_full_out.txt")));
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         int n = Integer.parseInt(f.readLine());
         
         StringTokenizer st = new StringTokenizer(f.readLine());
         int[] array = new int[n];
         for(int k = 0; k < n; k++){
            array[k] = Integer.parseInt(st.nextToken());
         }
         
         Segtree segtree = new Segtree(n);
         segtree.build(0,0,n-1,array);
         
         long answer = 0L;
         int queries = Integer.parseInt(f.readLine());
         for(int qq = 0; qq < queries; qq++){
            st = new StringTokenizer(f.readLine());
            
            int l = Integer.parseInt(st.nextToken())-1;
            int r = Integer.parseInt(st.nextToken())-1;
            
            segtree.update(0,0,n-1,l,r);
            
            answer += (long)(segtree.max(0,0,n-1,0,n-1).i+1);
         }
         
         out.println("Case #" + q + ": " + answer);
      }
        
        
      out.close();
   }
   
   public static int MOD = 1000000007;
   
   public static class Segtree{
      private boolean[] op;
      private Node[] a;
      
      public Segtree(int size){
         op = new boolean[4*size];
         a = new Node[4*size];
      }
      
      private Node combine(Node l, Node r){
         Node ret = new Node(l.minval,l.minindex,l.maxval,l.maxindex);
         
         if(r.minval < l.minval){
            ret.minval = r.minval;
            ret.minindex = r.minindex;
         }
         
         if(r.maxval > l.maxval){
            ret.maxval = r.maxval;
            ret.maxindex = r.maxindex;
         }
         
         return ret;
      }
      
      public void build(int v, int l, int r, int[] array){
         if(l == r){
            a[v] = new Node(array[l],l,array[l],l);
         } else {
            int mid = (l+r)/2;
            
            build(2*v+1,l,mid,array);
            build(2*v+2,mid+1,r,array);
            
            a[v] = combine(a[2*v+1],a[2*v+2]);
         }
      }
      
      private void propagate(int v){
         if(!op[v]) return;
         
         op[2*v+1] = !op[2*v+1];
         a[2*v+1].flip();
         
         op[2*v+2] = !op[2*v+2];
         a[2*v+2].flip();
         
         op[v] = false;
         a[v] = combine(a[2*v+1],a[2*v+2]);
      }
      
      public void update(int v, int l, int r, int ql, int qr){
         if(l >= ql && r <= qr){
            op[v] = !op[v];
            a[v].flip();
         } else if(r < ql || l > qr){
            return;
         } else {
            propagate(v);
            
            int mid = (l+r)/2;
            
            update(2*v+1,l,mid,ql,qr);
            update(2*v+2,mid+1,r,ql,qr);
            
            a[v] = combine(a[2*v+1],a[2*v+2]);
         }
      }
      
      public Ret max(int v, int l, int r, int ql, int qr){
         if(l >= ql && r <= qr){
            return new Ret(a[v].maxindex,a[v].maxval);
         } else if(r < ql || l > qr){
            return new Ret(-1,-1);
         } else {
            propagate(v);
            
            int mid = (l+r)/2;
            
            Ret left = max(2*v+1,l,mid,ql,qr);
            Ret right = max(2*v+2,mid+1,r,ql,qr);
            
            if(right.val > left.val) return right;
            else return left;
         }
      }
   }
   
   public static class Node{
      int minval;
      int minindex;
      int maxval;
      int maxindex;
      
      public Node(int a, int b, int c, int d){
         minval = a;
         minindex = b;
         maxval = c;
         maxindex = d;
      }
      
      public void flip(){
         int oldminval = minval;
         int oldmaxval = maxval;
         
         minval = MOD - oldmaxval;
         maxval = MOD - oldminval;
         
         int temp = minindex;
         minindex = maxindex;
         maxindex = temp;
      }
   }
    
   public static class Ret{
      int i;
      int val;
      public Ret(int a, int b){
         i = a;
         val = b;
      }
   }  
}