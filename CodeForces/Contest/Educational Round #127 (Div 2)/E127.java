//make sure to make new file!
import java.io.*;
import java.util.*;

public class E127{
   
   public static long MOD = 998244353L;
   public static Node[] nodes;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int N = Integer.parseInt(f.readLine());
      
      char[] array = f.readLine().toCharArray();
      int n = array.length;
      
      nodes = new Node[n+1];
      for(int k = n; k >= 1; k--){
         nodes[k] = new Node(array[k-1]);
         if(k*2 <= n){
            nodes[k].left = nodes[k*2];
            nodes[k].right = nodes[k*2+1];
            nodes[k].isleaf = false;
         }
      }
      
      //order all of the nodes and set string
      sortdfs(nodes[1]);
      
      long answer = dfs(nodes[1]);
      
      out.println(answer);
      
      
      
      
      
      
      
      
      out.close();
   }
   
   public static long dfs(Node node){
      if(node.isleaf) return 1L;
      
      long a = dfs(node.left);
      long b = dfs(node.right);
      
      long ret = (a*b + MOD)%MOD;
      if(!node.left.s.equals(node.right.s)){
         ret = (ret*2 + MOD)%MOD;
      }
      
      return ret;
   }
   
   public static void sortdfs(Node node){
      
      if(node.isleaf) return;
      
      sortdfs(node.left);
      sortdfs(node.right);
      
      if(node.left.s.compareTo(node.right.s) <= 0){
         node.s += node.left.s+node.right.s;
      } else {  
         node.s += node.right.s+node.left.s;
         
         //swap left and right
         node.swaplr();
      }
         
   }
   
   
   public static class Node{
      Node left;
      Node right;
      char ch;
      String s;
      boolean isleaf;
      
      public Node(char c){
         left = null;
         right = null;
         ch = c;
         s = ""+c;
         isleaf = true;
      }
      
      public void swaplr(){
         Node temp = left;
         left = right;
         right = temp;
      }
   }
      
}