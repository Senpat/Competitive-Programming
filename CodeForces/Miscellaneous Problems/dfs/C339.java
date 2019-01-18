//make sure to make new file!
import java.io.*;
import java.util.*;

public class C339{

   public static int n;
   public static HashSet<Integer> hs;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      String s = f.readLine();
      
      n = Integer.parseInt(f.readLine());
      
      hs = new HashSet<Integer>();
      
      for(int k = 1; k <= 10; k++){
         if(s.charAt(k-1) == '1') hs.add(k);
      }
      
        
      
      dfs(new State(0,-1,0,new State(-1,-1,-1,null)));
      
      
      
      out.close();
   }
   
   public static void dfs(State s){
      
      if(s.level == n){
         ArrayList<Integer> alist = new ArrayList<Integer>();
         State cur = s;
         while(cur.prev != -1){
            alist.add(cur.prev);
            cur = cur.par;
         }
         out.println("YES");
         for(int k = alist.size()-1; k >= 0; k--){
            out.println(alist.get(k) + " ");
         }
         out.close();
         System.exit(0);
      }
      
      for(int i : hs){
         if(i==s.prev) continue;
         if(s.level % 2 == 0){
            if(s.bal + i > 0){
               dfs(new State(s.bal+i,i,s.level+1,s));
            }
         } else {
            if(s.bal - i < 0){
               dfs(new State(s.bal-i,i,s.level+1,s));
            }
         }
      }
   
   public static class State{
      int bal;
      int prev;
      int level;
      State par;
      public State(int a, int b, int c, State d){
         bal = a;
         prev = b;
         level = c;
         par = d;
      }
   }
      
      
   
}