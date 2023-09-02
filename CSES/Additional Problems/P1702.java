//make sure to make new file!
import java.io.*;
import java.util.*;

public class P1702{

   public static int[] preorder;
   public static int[] inorder;
   
   public static int[] inindexof;
   
   public static int[][] children;
   
   public static int pi;
   
   public static ArrayList<Integer> answer;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st1 = new StringTokenizer(f.readLine());
      StringTokenizer st2 = new StringTokenizer(f.readLine());
      
      preorder = new int[n];
      inorder = new int[n];
      
      inindexof = new int[n+1];
      
      for(int k = 0; k < n; k++){
         preorder[k] = Integer.parseInt(st1.nextToken());
         inorder[k] = Integer.parseInt(st2.nextToken());
         
         inindexof[inorder[k]] = k;
      }
      
      children = new int[n+1][2];
      int root = preorder[0];
      int mid = inindexof[preorder[0]];
      
      pi = 1;
      
      recur(root,0,0,mid-1);
      recur(root,1,mid+1,n-1);
      
      answer = new ArrayList<Integer>();
      
      getpost(root);
      
      StringJoiner sj = new StringJoiner(" ");
      for(int i : answer){
         sj.add("" + i);
      }
      out.println(sj.toString());
      
      
      
      
      out.close();
   }
   
   
   public static void recur(int p, int c, int l, int r){
      if(r < l) return;
      
      int cur = preorder[pi];
      children[p][c] = cur;
      
      int mid = inindexof[cur];
      
      pi++;
      
      recur(cur,0,l,mid-1);
      recur(cur,1,mid+1,r);
   }
   
   public static void getpost(int v){
      if(children[v][0] != 0) getpost(children[v][0]);
      if(children[v][1] != 0) getpost(children[v][1]);
      answer.add(v);
   }
      
}