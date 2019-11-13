//Catowice City
import java.io.*;
import java.util.*;

public class F1248{

   public static ArrayList<ArrayList<Node>> jadj;
   public static ArrayList<ArrayList<Node>> cadj;
   public static HashSet<Integer> jhs;
   public static HashSet<Integer> chs;
   public static HashSet<Integer> janswer;
   public static HashSet<Integer> canswer;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
         
         jadj = new ArrayList<ArrayList<Node>>(n+1);
         cadj = new ArrayList<ArrayList<Node>>(n+1);
         for(int k = 0; k <= n; k++){
            jadj.add(new ArrayList<Node>());
            cadj.add(new ArrayList<Node>());
         }
         for(int k = 0; k < m; k++){
            st = new StringTokenizer(f.readLine());
         
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            
            jadj.get(a).add(new Node(b,1));
            cadj.get(b).add(new Node(a,0));
         }
         
         janswer = new HashSet<Integer>();
         canswer = new HashSet<Integer>();
            
         jhs = new HashSet<Integer>();
         chs = new HashSet<Integer>();
         
         //dfs de 1
         dfs(new Node(1,0),0);
         
         if(jhs.size() == n){
            //1 component
         
         
         } else {
            int adder = 1;
            jhs = new HashSet<Integer>();
            chs = new HashSet<Integer>();
            for(int k = 1; k <= n; k++){
               if(jhs.contains(k)) 
                  continue;
               dfs(new Node(k,0),adder);
               adder = 3-adder;
            }
         
         
         }
         
         out.println("Yes");
         out.println(janswer.size() + " " + canswer.size());
         for(int i : janswer){
            out.print(i + " ");
         }
         out.println();
         for(int i : canswer){
            out.print(i + " ");
         }
         out.println();
         
         if(q < t) f.readLine();
         
      }
      
      
      
      
      out.close();
   }
   //w = 0 -> ne ajouter rien, w = 1 -> ajouter a jury, w = 2 -> ajouter a chat
   public static void dfs(Node no, int w){
      add(no);
      
      if(w == 1) janswer.add(no.i);
      if(w == 2) canswer.add(no.i);
      
      if(no.b == 0){
         for(Node nei : jadj.get(no.i)){
            if(has(nei)) 
               continue;
            dfs(nei,w);
         }
      } else {
         for(Node nei : cadj.get(no.i)){
            if(has(nei)) 
               continue;
            dfs(nei,w);
         }
      }
   }
   
   
   public static void add(Node no){
      if(no.b == 0) jhs.add(no.i);
      if(no.b == 1) chs.add(no.i);
   }
   
   public static boolean has(Node no){
      if(no.b == 0) 
         return jhs.contains(no.i);
      if(no.b == 1) 
         return chs.contains(no.i);
      return false;
   }
   
   public static class Node{
      int i;
      int b;                           //0 est jury, 1 est chat
      public Node(int a, int c){
         i = a;
         b = c;
      }
   }
   
      
}