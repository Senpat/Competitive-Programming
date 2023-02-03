//make sure to make new file!
import java.io.*;
import java.util.*;
//upsolve, max clique practice
//max independent set is same as max clique on inverted graph
public class E533{

   public static int n;
   public static int[][] iadj;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int q = Integer.parseInt(st.nextToken());
      n = Integer.parseInt(st.nextToken());
      
      ArrayList<HashSet<Integer>> sets = new ArrayList<HashSet<Integer>>();
      
      HashSet<Integer> cur = new HashSet<Integer>();
      HashMap<String,Integer> id = new HashMap<String,Integer>();
      int ids = 0;
      
      for(int k = 0; k < q; k++){
         st = new StringTokenizer(f.readLine());
         
         int i = Integer.parseInt(st.nextToken());
         
         if(i == 1){
            if(cur.size() > 0){
               sets.add(cur);
               cur = new HashSet<Integer>();
            }
         } else {
            String s = st.nextToken();
            if(!id.containsKey(s)){
               id.put(s,ids);
               ids++;
            }
            cur.add(id.get(s));
         }
      }
      
      if(cur.size() > 0) sets.add(cur);
      
      //inverse adjacency matrix
      iadj = new int[n][n];
      for(int k = 0; k < n; k++) {
         Arrays.fill(iadj[k],1);
         iadj[k][k] = 0;
      }
      
      for(int k = 0; k < sets.size(); k++){
         for(int i : sets.get(k)){
            for(int j : sets.get(k)){
               if(i == j || iadj[i][j] == 0) continue;
               iadj[i][j] = 0;
               iadj[j][i] = 0;
            }
         }
      }
      
      long mask = 0;
      for(int k = 0; k < n; k++){
         mask += (1L << k);
      }
      
      mem = new HashMap<Long,Integer>();
      int answer = maxclique(mask);
      
      out.println(answer);
      
      
      
      out.close();
   }
   
   public static HashMap<Long,Integer> mem;
   
   public static int maxclique(long mask){
      if(mask == 0L) return 0;
      
      if(mem.containsKey(mask)) return mem.get(mask);
      
      int i = n;
      
      while((mask & (1L << i)) == 0){
         i--;
      }
      
      int a1 = maxclique(mask ^ (1L << i));
      
      long mask2 = 0;
      for(int nei = 0; nei < n; nei++){
         if(iadj[i][nei] == 1){
            mask2 += (1L << nei);
         }
      }
      
      int a2 = 1+maxclique(mask & mask2);
      
      mem.put(mask,Math.max(a1,a2));
      return Math.max(a1,a2);
   }
   
   
      
}