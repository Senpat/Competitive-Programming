//make sure to make new file!
import java.io.*;
import java.util.*;
//like p1.java but with binary search
public class p1b{

   public static long INF = 4000000000000000000L;

   public static ArrayList<Character> answer;
   public static long l,r;
   public static boolean adding;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      //BufferedReader f = new BufferedReader(new FileReader("p1.out"));
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
         psum[0] = 0L;
         long size = 0L;
         for(int j = 0; j < sn; j++){
            Node curnode = nodes.get(last[ops[k].s[j]-'a']);
            if(!curnode.leaf && curnode.size == 1L){
               curnode = curnode.nodes[0];
            }
            children[j] = curnode;
            size = Math.min(INF,size+children[j].size);
            if(j < sn-1) psum[j+1] = size;
         }
         last[ops[k].c-'a'] = nodes.size();
         nodes.add(new Node(nodes.size(),ops[k].c,size,children,psum));
      }
      
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
         if(done()) return;
         
         answer.add(node.c);
      
      } else {
         
         int start = 0;
         if(prev != -1L && prev > 0){
            //binary search to find starting point
            int bl = 0;
            int br = node.nodes.length-1;
            int ans = 0;
            while(bl <= br){
               int mid = bl + (br-bl)/2;
               
               if(node.psum[mid] < prev){
                  ans = mid;
                  bl = mid+1;
               } else {
                  br = mid-1;
               }
            }
            
            start = ans+1;
            
            dfs(node.nodes[ans],prev-node.psum[ans]);
         }
         
         //within range, print answer
         for(int k = start; k < node.nodes.length; k++){
            if(done()) return;
            dfs(node.nodes[k],-1L);
         }
            
         
      }
   }
   
   public static boolean done(){
      return answer.size() >= (int)(r-l+1);
   }
   
   public static class Node{
      int index;
      boolean leaf;
      char c;
      long size;
      Node[] nodes;
      long[] psum;
      
      public Node(int a, char b){
         index = a;
         c = b;
         leaf = true;
         size = 1L;
      }
      
      public Node(int a, char b, long d, Node[] n, long[] p){
         index = a;
         c = b;
         nodes = n;
         leaf = false;
         size = d;
         psum = p;
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