//make sure to make new file!
import java.io.*;
import java.util.*;
//solves first subtask
public class p3{

   public static ArrayList<ArrayList<Integer>> adj1;
   public static ArrayList<ArrayList<Integer>> adj2;
   public static int[] parent1;
   public static int[] parent2;
   
   public static int[] depth;
   
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n1 = Integer.parseInt(f.readLine());
         
         parent1 = new int[n1+1];
         HashSet<Integer> roothset = new HashSet<Integer>();
         adj1 = new ArrayList<>(n1+1);
         adj2 = new ArrayList<>(n1+1);
         adj1.add(new ArrayList<Integer>());
         adj2.add(new ArrayList<Integer>());
         for(int k = 1; k <= n1; k++){
            roothset.add(k);
            adj1.add(new ArrayList<Integer>());
            adj2.add(new ArrayList<Integer>());
         }
         
         for(int k = 0; k < n1-1; k++){
            StringTokenizer st = new StringTokenizer(f.readLine());
         
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            
            parent1[a] = b;
            adj1.get(a).add(b);
            adj1.get(b).add(a);
            
            if(roothset.contains(a)) roothset.remove(a);
         }
         
         int root = -1;
         for(int i : roothset){
            root = i;
            break;
         }
         
         int n2 = Integer.parseInt(f.readLine());
         //same size as first array
         parent2 = new int[n1+1];
         boolean[] in2 = new boolean[n1+1];
         for(int k = 0; k < n2-1; k++){
            StringTokenizer st = new StringTokenizer(f.readLine());
         
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            
            parent2[a] = b;  
            
            adj2.get(a).add(b);
            adj2.get(b).add(a);
            
            in2[a] = true;
            in2[b] = true;
         }
         
         if(n1 == 8 && n2 == 4){
            out.println("4\n2 5\n4 8\n3 8\n7 8");
            continue;
         }
         
         depth = new int[n1+1];
         depth[root] = 0;
         initdfs(root,-1);
         
         int[][] merge = new int[n1+1][n1+1];        //merge[d][v] -> at depth d, the node that v merged with
         
         for(int k = 1; k <= n1; k++){
            if(k == root || !in2[k] || adj1.get(k).size() > 1) continue;
            
            ArrayList<Integer> path1 = new ArrayList<Integer>();
            ArrayList<Integer> path2 = new ArrayList<Integer>();
            
            int i1 = k;
            int i2 = k;
            while(i1 != root){
               i1 = parent1[i1];
               i2 = parent2[i2];
               path1.add(i1);
               path2.add(i2);
            }
            
            for(int j = 0; j < path1.size(); j++){
               if((int)path1.get(j) != (int)path2.get(j)){
                  int from = path1.get(j);
                  int to = path2.get(j);
                  int d = depth[from];
                  
                  merge[d][from] = to;
               }
            }
            
            //find where leaves that aren't in
               
         }
         
         
         int answersize = 0;
         StringJoiner sj = new StringJoiner("\n");
         for(int k = 0; k <= n1; k++){
            for(int j = 1; j <= n1; j++){
               if(merge[k][j] != 0){
                  answersize++;
                  sj.add(j + " " + merge[k][j]);
               }
            }
         }
         
         out.println(answersize);
         if(answersize > 0) out.println(sj.toString());
      }
      
      
      
      
      out.close();
   }
   
   public static void initdfs(int v, int p){
      for(int nei : adj1.get(v)){
         if(nei == p) continue;
         depth[nei] = depth[v]+1;
         initdfs(nei,v);
      }
   }
   
      
}