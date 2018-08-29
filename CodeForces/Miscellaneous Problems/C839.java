//Journey
import java.io.*;
import java.util.*;

public class C839{

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
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         
         roads.get(a).add(b);
         roads.get(b).add(a);
      }
      
      boolean[] used = new boolean[n+1];
      
      solve(1,0.0,1.0,used);
      
      out.println(answer);
      
      out.close();
   }
   
   public static void solve(int cur, double count, double prob, boolean[] used){
      boolean[] used2 = Arrays.copyOf(used,used.length);
      used2[cur] = true;
      boolean did = false;
      for(int i : roads.get(cur)){
         if(!used2[i]){
            solve(i,count+1,prob/roads.size(),used2);
            did = true;
         }
      }
      if(!did){
         answer += count*prob;
      }
   }
   
}