//make sure to make new file!
import java.io.*;
import java.util.*;
//wrong (doesn't consider all w)
public class G337{

   public static ArrayList<ArrayList<Integer>> adj;
   
   public static long[] answer;
   
   public static int[] subsize;        //overridden a lot
   public static boolean[] iscentroid;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      adj = new ArrayList<ArrayList<Integer>>(n+1);
      for(int k = 0; k <= n; k++){
         adj.add(new ArrayList<Integer>());
      }
      
      for(int k = 0; k < n-1; k++){
         StringTokenizer st = new StringTokenizer(f.readLine());
         
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         
         adj.get(a).add(b);
         adj.get(b).add(a);
      }
      
      answer = new long[n+1];
      
      iscentroid = new boolean[n+1];
      subsize = new int[n+1];
      
      solve(1);
      
      
      
      
      
      
      
      
      out.close();
   }
   
   public static void dfs_size(int v, int p){
      subsize[v] = 1;
      for(int nei : adj.get(v)){
         if(nei == p || iscentroid[nei]) continue;
         dfs_size(nei,v);
         subsize[v] += subsize[nei];
      }
   }
   
   public static int dfs_centroid(int v, int p, int thresh){
      for(int nei : adj.get(v)){
         if(nei == p || iscentroid[nei]) continue;
         if(subsize[nei] > thresh){
            return dfs_centroid(nei,v,thresh);
         }
      }
      
      return v;
   }
   
   public static long all = 0L;
   public static HashMap<Integer,Long> sub;
   
   public static void calc(int v, int p, int cent){
      if(
   }
   
   public static void solve(int v){
      dfs_size(v,-1);
      
      int thresh = subsize[v]/2;
      int centroid = dfs_centroid(v,-1,thresh);
      
      //add to all
      all = 0L;
      sub = HashMap<Integer,Long>();
      
      for(int nei : adj.get(centroid)){
         calc(nei,centroid,centroid);
      }
   }
   
      
}