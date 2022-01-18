//make sure to make new file!
import java.io.*;
import java.util.*;

public class E103{

   public static ArrayList<ArrayList<Integer>> adj;
   public static Stack<Integer> stk;
   public static HashSet<Integer> seen;
   public static HashSet<Integer> added;
   public static boolean topsortfail;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      int x = Integer.parseInt(st.nextToken());
      
      HashMap<String,Integer> hmap = new HashMap<String,Integer>();
      for(int k = 1; k <= n; k++){
         String s = f.readLine();
         hmap.put(s,k);
      }
      
      String[] strings = new String[m];
      int[] spat = new int[m];
      
      for(int k = 0; k < m; k++){
         st = new StringTokenizer(f.readLine());
      
         strings[k] = st.nextToken();
         spat[k] = Integer.parseInt(st.nextToken());
      }
      
      adj = new ArrayList<ArrayList<Integer>>(n+1);
      for(int k = 0; k <= n; k++) adj.add(new ArrayList<Integer>());
      
      boolean fail = false;
      for(int k = 0; k < m; k++){
         //generate strings
         
         boolean spatfound = false;
         for(int i = 0; i < 1<<x; i++){
            String s = "";
            for(int j = 0; j < x; j++){
               if((i & 1<<j) == 0) s += "_";
               else s += strings[k].charAt(j);
            }
            //out.println(s);
            if(hmap.containsKey(s)){
               int val = hmap.get(s);
               if(val == spat[k]){
                  spatfound = true;
               } else {
                  adj.get(spat[k]).add(val);
               }
            }
         }
         
         if(!spatfound){
            fail = true;
            break;
         }
      }
      
      if(fail){
         out.println("NO");
         out.close();
         return;
      }
      
      //do top sort
      stk = new Stack<Integer>();
      seen = new HashSet<Integer>();
      added = new HashSet<Integer>();
      topsortfail = false;
      for(int k = 1; k <= n; k++){
         if(!seen.contains(k)){
            topsort(k);
         }
         if(topsortfail) break;
      }     
      
      if(topsortfail){
         out.println("NO");
      } else {
         out.println("YES");
         StringJoiner sj = new StringJoiner(" ");
         while(!stk.isEmpty()){
            sj.add("" + stk.pop());
         }
         out.println(sj.toString());
      }
      
      
      
      
      
      
      
      out.close();
   }
   
   public static void topsort(int v){
      if(added.contains(v)){
         topsortfail = true;
         return;
      }
      if(seen.contains(v)) return;
      seen.add(v);
      
      
      added.add(v);
      for(int nei : adj.get(v)){
         topsort(nei);
         
      }
      
      added.remove(v);
      stk.add(v);
   }
      
}