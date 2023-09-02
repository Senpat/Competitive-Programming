//make sure to make new file!
import java.io.*;
import java.util.*;

public class P4F{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      Segtree segtree = new Segtree(n+1);
      while(true){
         StringTokenizer st = new StringTokenizer(f.readLine());
       
         char type = st.nextToken().charAt(0);
         
         if(type == 'Q'){
            long h = Long.parseLong(st.nextToken());
            
            int answer = segtree.query(0,0,n,h);
            if(answer == Integer.MAX_VALUE){
               answer = n;
            } else {
               answer--;
            }
            
            out.println(answer);
         } else if(type == 'I'){
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            long d = Long.parseLong(st.nextToken());
            
            long curx = segtree.getx(0,0,n,r);
            System.out.println(r + " " + curx);
            segtree.update(0,0,n,l,r,new Op(0,d));
            if(r < n){
               System.out.println("starting update 2");
               segtree.update(0,0,n,r+1,n,new Op(d * (long)(r-l+1) - curx));
            }
            
         } else {
            break;
         }
      }
      
      System.out.println("Printing:");
      segtree.print(0,0,n);
      
      
      out.close();
   }
   
   public static class Segtree{
   
      private Op[] ops;
      private long[] a;          //stores max on segment
      private long[] curx;       //current elevation change
      
      public Segtree(int size){
         ops = new Op[4*size];
         a = new long[4*size];
         curx = new long[4*size];
         
         for(int k = 0; k < 4*size; k++){
            ops[k] = new Op(0);
         }
         
         
      }
      
      private Op applytoop(Op prev, Op next){
         if(prev.type == 1 && next.type == 1){
            long newstart = prev.start + next.x - prev.x;
            return new Op(newstart,next.x);
         } else if(prev.type == 1 && next.type == 2){
            return new Op(prev.start + next.x, prev.x);
         } else if(prev.type == 2 && next.type == 1){
            return new Op(prev.x + next.start, next.x);
         } else {
            //both are type 2
            return new Op(prev.x + next.x);
         }
      }
      
      private long applytomax(long prev, Op op, int l, int r){
         if(op.type == 1){
            if(op.x <= 0){
               return op.start+op.x;         //first value
            } else {
               return op.start + op.x * (long)(r-l+1);      //last value;
            }
         } else {
            return prev + op.x;
         }
      } 
      
      private Op getrightop(Op op, int l, int r, int ql){
         if(op.type == 2) return op;
         
         int mid = (l+r)/2;
         
         if(op.type == 1 && ql > mid) return op;
         
         //mid-1 is last element in left, aka start
         long newstart = op.start + op.x * (long)(mid-1 - ql + 1);
         System.out.println("get right op: " + op + ", " + l + " " + r + " " + ql + " -> " + newstart);
         return new Op(newstart,op.x);
      }
      
      private void propagate(int v, int l, int r){
         Op rightop = getrightop(ops[v],l,r,l);
         
         ops[2*v+1] = applytoop(ops[2*v+1],ops[v]);
         a[2*v+1] = applytomax(a[2*v+1],ops[v],l,r);
         
         ops[2*v+2] = applytoop(ops[2*v+2],rightop);
         a[2*v+2] = applytomax(a[2*v+2],rightop,l,r);
         
         ops[v] = new Op(0);
         
         if(curx[v] != Long.MAX_VALUE){
            curx[2*v+1] = curx[v];
            curx[2*v+2] = curx[v];
         }
         curx[v] = Long.MAX_VALUE;
      }
         
      
      public void update(int v, int l, int r, int ql, int qr, Op op){
         System.out.println(v + " " + l + " " + r + " " + ql + " " + qr + " op: " + op);
         if(l >= ql && r <= qr){
            Op prevop = ops[v];
            long preva = a[v];
            ops[v] = applytoop(ops[v],op);
            a[v] = applytomax(a[v],op,l,r);
            System.out.println("Setting ops at " + v + " from " + prevop + " to " + ops[v]);
            System.out.println("Setting a at " + v + " from " + preva + " to " + a[v]);
            
            if(op.type == 1){
               System.out.println("Setting curx at " + v + " to " + op.x);
               curx[v] = op.x;
            } else {
               curx[v] += op.x;
            }
         } else if(r < ql || l > qr){
            return;
         } else{
            int mid = (l+r)/2;
            
            propagate(v,l,r);
            
            update(2*v+1,l,mid,ql,qr,op);
            Op rightop = getrightop(op,l,r,ql-1);
            update(2*v+2,mid+1,r,ql,qr,rightop);
            
            a[v] = Math.max(a[2*v+1],a[2*v+2]);
         }
      }
      
      //get index of first element > x, or Integer.MAX_VALUE if it reaches the end
      public int query(int v, int l, int r, long x){
         System.out.println("Query: " + v + " " + a[v]);
         if(l == r){
            if(a[v] > x) return l;
            return Integer.MAX_VALUE;
         } else {
            int mid = (l+r)/2;
            
            propagate(v,l,r);
            
            System.out.println("left: " + a[2*v+1]);
            System.out.println("right: " + a[2*v+2]);
            
            if(a[2*v+1] > x){
               return query(2*v+1,l,mid,x);
            } else {
               return query(2*v+2,mid+1,r,x);
            }
         }
      }
      
      public long getx(int v, int l, int r, int i){
         if(l == r){
            return curx[v];
         } else {
            int mid = (l+r)/2;
            
            propagate(v,l,r);
            
            if(i <= mid){
               return getx(2*v+1,l,mid,i);
            } else {
               return getx(2*v+2,mid+1,r,i);
            }
         }
      }
      
      void print(int v, int l, int r){
         if(l == r){
            System.out.println(l + ": ");
            System.out.println("Op: " + ops[v]);
            System.out.println("a: " + a[v]);
            System.out.println("curx: " + curx[v]);
         } else {
            int mid = (l+r)/2;
            
            print(2*v+1,l,mid);
            print(2*v+2,mid+1,r);
         }
      }
      
            
      
   }
   
   public static class Op{
      int type;                  //1 is arithmetic sequence, 2 is range update
      long start;
      long x;
      public Op(long a, long b){
         type = 1;
         start = a;
         x = b;
      }
      public Op(long a){
         type = 2;
         x = a;
      }
      
      public String toString(){
         return type + " " + start + " " + x;
      }
   }
   
      
}