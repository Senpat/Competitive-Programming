//make sure to make new file!
import java.io.*;
import java.util.*;

public class p1{

   public static long INF = 4000000000000000000L;

   public static ArrayList<Character> answer;
   public static long l,r;
   public static boolean adding;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      l = Long.parseLong(st.nextToken());
      r = Long.parseLong(st.nextToken());
      int o = Integer.parseInt(st.nextToken());
      
      Op[] ops = new Op[o];
      for(int k = 0; k < o; k++){
         st = new StringTokenizer(f.readLine());
         
         char c = st.nextToken().charAt(0);
         char[] s = st.nextToken().toCharArray();
         
         ops[k] = new Op(k,c,s);
         
      }
      
      //index of node that that character leads to
      int[] last = new int[26];
      ArrayList<Node> nodes = new ArrayList<Node>();
      //add leaves
      for(int k = 0; k < 26; k++){
         nodes.add(new Node(k,(char)('a'+k)));
         last[k] = k;
      }
      
      for(int k = o-1; k >= 0; k--){
         int sn = ops[k].s.length;
         Node[] children = new Node[sn];
         long[] psum = new long[sn];
         long size = 0L;
         for(int j = 0; j < sn; j++){
            children[j] = nodes.get(last[ops[k].s[j]-'a']);
            size = Math.min(INF,size+children[j].size);
         }
         last[ops[k].c-'a'] = nodes.size();
         nodes.add(new Node(nodes.size(),ops[k].c,children,size));
      }
      
      //Node[] heada = new Node[]{nodes.get(last[0])};
      //Node head = new Node(nodes.size(),'a',heada,nodes.get(last[0]).size);
      
      //prev - find the prev-th node in that range
      answer = new ArrayList<Character>();
      dfs(nodes.get(last[0]),l);
      
      StringJoiner sj = new StringJoiner("");
      for(char c : answer){
         sj.add(""+c);
      }
      out.println(sj.toString());
      
      
      
      
      
      
      out.close();
   }
   
   public static void dfs(Node node, long prev){
      
      if(node.leaf){
         if(answer.size() >= (int)(r-l+1)) 
            return;
         
         if(!adding){
            if(prev == 1){
               adding = true;
            }
         }
         
         if(adding){
            answer.add(node.c);
         }
      
      } else {
         
         long curprev = 0L;
         for(Node nei : node.nodes){
            if(answer.size() >= (int)(r-l+1)) 
               return;
            
            if(!adding){
               if(curprev + nei.size >= prev){
                  dfs(nei,prev-curprev);
               }
            }
            else {
               //prev doesn't matter
               dfs(nei,-1L);
            }
            
            curprev += nei.size;
         }
      }
   }
   
   public static class Node{
      int index;
      boolean leaf;
      char c;
      Node[] nodes;
      long size;
      
      public Node(int a, char b){
         index = a;
         c = b;
         nodes = new Node[0];
         leaf = true;
         size = 1L;
      }
      
      public Node(int a, char b, Node[] n, long d){
         index = a;
         c = b;
         nodes = n;
         leaf = false;
         size = d;
      }
      
      public String toString(){
         return "" + index + " " + c + " " + leaf + " " + size;
      }
   }
   
   //Operation
   public static class Op{
      int index;
      char c;
      char[] s;
      public Op(int a, char b, char[] cs){
         index = a;
         c = b;
         s = cs;
      } 
   }  
      
}