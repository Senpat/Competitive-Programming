//make sure to make new file!
import java.io.*;
import java.util.*;

public class D603{
   
   public static HashSet<Integer> seen;
   public static ArrayList<HashSet<Integer>> adj;
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      String[] array = new String[n];
      for(int k = 0; k < n; k++){
         array[k] = f.readLine();
      }
      
      adj = new ArrayList<HashSet<Integer>>(26);
      
      for(int k = 0; k < 26; k++){
         adj.add(new HashSet<Integer>());
      }
      
      HashSet<Integer> total = new HashSet<Integer>();
      
      for(int k = 0; k < n; k++){
         HashSet<Character> hset = new HashSet<Character>();
         for(int j = 0; j < array[k].length(); j++){
            if(hset.contains(array[k].charAt(j))) continue;
            else {
               for(char c : hset){
                  adj.get(c-'a').add(array[k].charAt(j)-'a');
                  adj.get(array[k].charAt(j)-'a').add(c-'a');
               }
               hset.add(array[k].charAt(j));
            }
            
            total.add(array[k].charAt(j)-'a');
         }
      }
      
      
      int answer = 0;
      seen = new HashSet<Integer>();
      for(int k = 0; k < 26; k++){
         if(seen.contains(k) || !total.contains(k)) continue;
         answer++;
         seen.add(k);
         dfs(k);
      }
      
      
      out.println(answer);
      
      
      
      
      
      out.close();
   }
   
   public static void dfs(int v){
      
      for(int nei : adj.get(v)){
         if(seen.contains(nei)) continue;
         seen.add(nei);
         dfs(nei);
      }
   }
   
      
}