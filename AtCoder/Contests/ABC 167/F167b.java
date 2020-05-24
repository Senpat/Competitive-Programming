//make sure to make new file!
import java.io.*;
import java.util.*;

public class F167b{
   /*
   public static long[] t;
   public static int MAX_N = 1000000;
*/
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      String[] array = new String[n];
      for(int k = 0; k < n; k++){
         array[k] = f.readLine();
      }
      
      int[] eo = new int[n];      //extra open
      int[] ec = new int[n];      //extra closed
      
      
      for(int k = 0; k < n; k++){
         
         char[] cur = array[k].toCharArray();
         Stack<Character> stk = new Stack<Character>();
         
         for(int j = 0; j < cur.length; j++){
            if(cur[j] == ')'){
               if(stk.isEmpty()){
                  ec[k]++;
               } else {
                  stk.pop();
               }
            } else {
               stk.add('(');
            }
         }
         
         eo[k] += stk.size();
      }
      
      HashSet<Integer> oo = new HashSet<Integer>();      //only extra open
      HashSet<Integer> oc = new HashSet<Integer>();      //only extra closed
      
      PriorityQueue<Pair> pqgain = new PriorityQueue<Pair>();
      PriorityQueue<Pair2> pqlose = new PriorityQueue<Pair2>();
      //TreeSet<Pair> both = new TreeSet<Pair>();
      for(int k = 0; k < n; k++){
         if(eo[k] == 0 && ec[k] == 0) continue;
         if(ec[k] == 0) oo.add(k);
         else if(eo[k] == 0) oc.add(k);
         else{
            if(eo[k] >= ec[k])
               pqgain.add(new Pair(eo[k],ec[k]));
            else
               pqlose.add(new Pair2(eo[k],ec[k]));
         }
      }  
      
      
      //t = new long[4*MAX_N];
      
      int open = 0;
      for(int i : oo){
         open += eo[i];
      }
      
      while(!pqgain.isEmpty()){
         Pair p = pqgain.poll();
         
         open -= p.closed;
         if(open < 0){
            out.println("No");
            out.close();
            return;
         }
         
         open += p.open;
      }
      
      while(!pqlose.isEmpty()){
         Pair2 p = pqlose.poll();
         
         open -= p.closed;
         if(open < 0){
            out.println("No");
            out.close();
            return;
         }
         
         open += p.open;
      }
      
      for(int i : oc){
         open -= ec[i];
      }
      
      if(open == 0){
         out.println("Yes");
      } else {
         out.println("No");
      }
      
      
      
      
      out.close();
   }
   
   public static class Pair implements Comparable<Pair>{
      int open;
      int closed;
      public Pair(int a, int b){
         open = a;
         closed = b;
      }
      public int compareTo(Pair p){          //compare by closed
         return closed-p.closed;
      }
   }
   
   public static class Pair2 implements Comparable<Pair2>{
      int open;
      int closed;
      public Pair2(int a, int b){
         open = a;
         closed = b;
      }
      public int compareTo(Pair2 p){          //compare by closed
         return closed-open-(p.closed-p.open);
      }
   }
   
   
   /*
   //to call: v=1, tl = 0, tr = n-1
   public static long max(int v, int tl, int tr, int l, long r) {
      if (l > r) 
         return Long.MIN_VALUE;
      if (l == tl && r == tr) {
         return t[v];
      }
      int tm = (tl + tr) / 2;
      return Math.max(max(v*2, tl, tm, l, Math.min(r, tm)),max(v*2+1, tm+1, tr, Math.max(l, tm+1), r));
   }
   
   //to call: v=1, tl = 0, tr = n-1
   public static void update(int v, int tl, int tr, int pos, long new_val) {
      if (tl == tr) {
         t[v] = new_val;
      } else {
         int tm = (tl + tr) / 2;
         if (pos <= tm)
            update(v*2, tl, tm, pos, new_val);
         else
            update(v*2+1, tm+1, tr, pos, new_val);
         t[v] = Math.max(t[v*2],t[v*2+1]);
      }
   }
     */ 
}