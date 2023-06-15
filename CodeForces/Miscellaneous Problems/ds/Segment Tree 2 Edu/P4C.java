//make sure to make new file!
import java.io.*;
import java.util.*;

public class P4C{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int q = Integer.parseInt(f.readLine());
      
      int N = 1000010;
      int MID = 500005;
      Segtree segtree = new Segtree(N);
      
      
      for(int t = 0; t < q; t++){
         StringTokenizer st = new StringTokenizer(f.readLine());
         
         char c = st.nextToken().charAt(0);
         int x = Integer.parseInt(st.nextToken()) + MID;
         int l = Integer.parseInt(st.nextToken());
         
         segtree.update(0,0,N-1,x,x+l-1,c);
         
         Node node = segtree.query();
         out.println(node.num + " " + node.len);
      }
      
      
      
      
      
      
      
      out.close();
   }
   
   public static class Segtree{
      
      private final char NOOP = '?';
      private char[] ops;
      private Node[] a;
      
      public Segtree(int size){
         a = new Node[4*size];
         
         for(int k = 0; k < a.length; k++){
            a[k] = new Node(0,0,false,false);
         }
         
         ops = new char[4*size];
         Arrays.fill(ops,NOOP);
      }
      
      public Node combine(Node left, Node right){
         int num = left.num + right.num;
         if(left.rightblack && right.leftblack) num--;
         int len = left.len + right.len;
         return new Node(num,len,left.leftblack,right.rightblack);
      }
      
      public void propagate(int v, int l, int r){
         if(ops[v] == NOOP) return;
         
         ops[2*v+1] = ops[v];
         ops[2*v+2] = ops[v];
         
         if(ops[v] == 'W'){
            a[2*v+1] = new Node(0,0,false,false);
            a[2*v+2] = new Node(0,0,false,false);
         } else {
            int mid = (l+r)/2;
            int lenl = mid-l+1;
            int lenr = r-(mid+1)+1;
            a[2*v+1] = new Node(1,lenl,true,true);
            a[2*v+2] = new Node(1,lenr,true,true);
         }
         
         ops[v] = NOOP;
      }
            
      
      public void update(int v, int l, int r, int ql, int qr, char c){
         if(l >= ql && r <= qr){
            ops[v] = c;
            if(c == 'W'){
               a[v] = new Node(0,0,false,false);
            } else {
               a[v] = new Node(1,r-l+1,true,true);
            }
         } else if(r < ql || l > qr){
            return;
         } else {
            int mid = (l+r)/2;
            
            propagate(v,l,r);
            
            update(2*v+1,l,mid,ql,qr,c);
            update(2*v+2,mid+1,r,ql,qr,c);
            
            a[v] = combine(a[2*v+1],a[2*v+2]);
         }
      }
      
      public Node query(){
         return a[0];
      }
   }
   
   public static class Node{
      int num;
      int len;
      boolean leftblack;      //if leftmost value is black
      boolean rightblack;     //if rightmost value is black
      
      public Node(int a, int b, boolean c, boolean d){
         num = a;
         len = b;
         leftblack = c;
         rightblack = d;
      }
   }
   
      
}