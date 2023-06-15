//make sure to make new file!
import java.io.*;
import java.util.*;

public class P4E{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int q = Integer.parseInt(st.nextToken());
      
      Segtree segtree = new Segtree(n);
      
      for(int t = 0; t < q; t++){
         st = new StringTokenizer(f.readLine());
         
         int qt = Integer.parseInt(st.nextToken());
         int l = Integer.parseInt(st.nextToken());
         int r = Integer.parseInt(st.nextToken());
         int h = Integer.parseInt(st.nextToken());
         
         Op op;
         if(qt == 1){
            //adding
            op = new Op(h,Integer.MAX_VALUE);
         } else {
            //removing
            op = new Op(0,h);
         }
         
         segtree.update(0,0,n-1,l,r,op);
      }
      
      int[] answer = new int[n];
      segtree.get(0,0,n-1,answer);
      
      StringJoiner sj = new StringJoiner("\n");
      for(int k = 0; k < n; k++){
         sj.add("" + answer[k]);
      }
      out.println(sj.toString());
      
      
      
      
      
      
      
      out.close();
   }
   
   public static class Segtree{
      
      private Op[] ops;
      private int[] a;
      
      public Segtree(int size){
         ops = new Op[4*size];
         a = new int[4*size];
         
         for(int k = 0; k < 4*size; k++){
            ops[k] = new Op();
         }
      }
      
      //apply next to prev
      private Op apply(Op prev, Op next){
         if(next.max <= prev.min){
            return new Op(next.max,next.max);
         } else if(next.min >= prev.max){
            return new Op(next.min,next.min);
         } else {
            return new Op(Math.max(prev.min,next.min),Math.min(prev.max,next.max));
         }
      }
      
      private void propagate(int v){
         ops[2*v+1] = apply(ops[2*v+1],ops[v]);
         ops[2*v+2] = apply(ops[2*v+2],ops[v]);
         ops[v] = new Op();
      }
      
      public void update(int v, int l, int r, int ql, int qr, Op op){
         if(l >= ql && r <= qr){
            ops[v] = apply(ops[v],op);
         } else if(r < ql || l > qr){
            return;
         } else {
            int mid = (l+r)/2;
            
            propagate(v);
            
            update(2*v+1,l,mid,ql,qr,op);
            update(2*v+2,mid+1,r,ql,qr,op);
            
         }
         
      }
      
      public void get(int v, int l, int r, int[] answer){
         if(l == r){
            //apply 0 to op
            answer[l] = ops[v].min;
         } else {
            int mid = (l+r)/2;
            
            propagate(v);
            
            get(2*v+1,l,mid,answer);
            get(2*v+2,mid+1,r,answer);
         }
      }
      
   }
   
   public static class Op{
      int min;             //everything <= min goes to min
      int max;             //everything >= max goes to max
      
      public Op(){
         this(0,Integer.MAX_VALUE);
      }
      
      public Op(int a, int b){
         min = a;
         max = b;
      }
   }
}