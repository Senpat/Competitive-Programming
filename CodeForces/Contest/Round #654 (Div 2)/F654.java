//make sure to make new file!
import java.io.*;
import java.util.*;
//upsolve
public class F654{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int q = Integer.parseInt(st.nextToken());
      
      char[] array = f.readLine().toCharArray();
      
      Segtree segtree = new Segtree(n);
      segtree.build(0,0,n-1,array);
      
      for(int t = 0; t < q; t++){
         st = new StringTokenizer(f.readLine());
         
         int l = Integer.parseInt(st.nextToken())-1;
         int r = Integer.parseInt(st.nextToken())-1;
         
         segtree.flip(0,0,n-1,l,r);
         Node node = segtree.query(0,0,n-1,l,r);
         out.println(node.max);
      }
      
      
      
      
      
      
      out.close();
   }
   
   public static class Segtree{
      
      private boolean[] ops;
      private int[] state;
      //0 is no flip, 1 is flipped
      private Node[][] a;
      
      public Segtree(int size){
         state = new int[4*size];
         ops = new boolean[4*size];
         a = new Node[4*size][2];
      }
      
      private void swap(int v){
         Node temp = a[v][0];
         a[v][0] = a[v][1];
         a[v][1] = temp;
      }
      
      private void propagate(int v){
         if(!ops[v]) return;
         
         ops[2*v+1] = !ops[2*v+1];
         swap(2*v+1);
         ops[2*v+2] = !ops[2*v+2];
         swap(2*v+2);
         
         ops[v] = false;
      }
      
      private Node combine(Node left, Node right){
         if(left.max == -1) return right;
         if(right.max == -1) return left;
         
         int max = Math.max(left.max,right.max);
         max = Math.max(max, left.outright + right.inleft);    // >>|>>
         max = Math.max(max, left.inright + right.outleft);    // <<|<<
         max = Math.max(max, left.outright + right.outleft);   // >>|<<
         
         int outleft = left.outleft;
         if(left.allleft) outleft += right.outleft;
         int outright = right.outright;
         if(right.allright) outright += left.outright;
         
         int inleft = left.inleft;
         if(left.allright){
            inleft += Math.max(right.inleft,right.outleft);
         } else if(inleft == left.len){
            inleft += right.outleft;
         }
         
         int inright = right.inright;
         if(right.allleft){
            inright += Math.max(left.inright,left.outright);
         } else if(inright == right.len){
            inright += left.outright;
         }
         
         int len = left.len + right.len;
         
         boolean allleft = left.allleft & right.allleft;
         boolean allright = left.allright & right.allright;
         
         return new Node(max,outleft,outright,inleft,inright,len,allleft,allright);
      }
      
      public void build(int v, int l, int r, char[] array){
         if(l == r){
            Node less = new Node(1,1,0,0,1,1,true,false);
            Node greater = new Node(1,0,1,1,0,1,false,true);
            if(array[l] == '<'){
               a[v][0] = less;
               a[v][1] = greater;
            } else {
               a[v][0] = greater;
               a[v][1] = less;
            }
         } else {
            int mid = (l+r)/2;
            
            build(2*v+1,l,mid,array);
            build(2*v+2,mid+1,r,array);
            
            a[v][0] = combine(a[2*v+1][0],a[2*v+2][0]);
            a[v][1] = combine(a[2*v+1][1],a[2*v+2][1]);
         }
      }
      
      public void flip(int v, int l, int r, int ql, int qr){
         if(l >= ql && r <= qr){
            ops[v] = !ops[v];
            swap(v);
         } else if(r < ql || l > qr){
            return;
         } else {
            propagate(v);
            
            int mid = (l+r)/2;
            
            flip(2*v+1,l,mid,ql,qr);
            flip(2*v+2,mid+1,r,ql,qr);
            
            a[v][0] = combine(a[2*v+1][0],a[2*v+2][0]);
            a[v][1] = combine(a[2*v+1][1],a[2*v+2][1]);
         }
      }
      
      public Node query(int v, int l, int r, int ql, int qr){
         if(l >= ql && r <= qr){
            return a[v][0];
         } else if(r < ql || l > qr){
            return new Node(-1,0,0,0,0,0,false,false);
         } else {
            propagate(v);
            
            int mid = (l+r)/2;
            
            return combine(query(2*v+1,l,mid,ql,qr),query(2*v+2,mid+1,r,ql,qr));
         }
      }
   
   }
   
   public static class Node{
      int max;
      
      int outleft;
      int outright;
      
      //>>><< -> inleft = 5
      int inleft;
      int inright;
      
      int len;
      
      boolean allleft;
      boolean allright;
      
      public Node(int a, int b, int c, int d, int e, int f, boolean g, boolean h){
         max = a;
         outleft = b;
         outright = c;
         inleft = d;
         inright = e;
         len = f;
         
         allleft = g;
         allright = h;
      }
   }
      
}