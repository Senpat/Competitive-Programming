//Journey
import java.io.*;
import java.util.*;
//semi-tutorial
public class C839b{

   public static double answer = 0.0;
   public static ArrayList<ArrayList<Integer>> roads;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      roads = new ArrayList<ArrayList<Integer>>(n);
      
      for(int k = 0; k < n+1; k++) roads.add(new ArrayList<Integer>());
      
      for(int k = 0; k < n-1; k++){
         StringTokenizer st = new StringTokenizer(f.readLine());
         int a = Integer.parseInt(st.nextToken())-1;
         int b = Integer.parseInt(st.nextToken())-1;
         
         roads.get(a).add(b);
         roads.get(b).add(a);
      }
      
      double answer = dfs(0,-1);
      
      if(n==1) out.println(0.0);
      else if(answer>0) out.println(answer);
      else out.println(-1);
      
      out.close();
   }
   
   public static double dfs(int cur, int parent){
      double sum = 0.0;
      for(int i : roads.get(cur)){
         if(i!=parent){
            sum+=dfs(i,cur)+1;
         }
      }
      
      if(sum==0.0) return 0.0;
      if(parent==-1) return sum/roads.get(cur).size();
      else return sum/(roads.get(cur).size()-1);
      
   }
   
}