//Alien Rhyme
import java.io.*;
import java.util.*;

public class Solution{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int q = Integer.parseInt(f.readLine());
      
      for(int t = 1; t <= q; t++){
      
         int n = Integer.parseInt(f.readLine());
         
         String[] array = new String[n];
         for(int k = 0; k < n; k++){
            array[k] = f.readLine();
         }
         
         HashMap<String,HashSet<Integer>> hmap = new HashMap<String,HashSet<Integer>>();
         HashSet<Integer> used = new HashSet<Integer>();
         HashSet<String> sused = new HashSet<String>();
                  
         for(int k = 0; k < n; k++){
            boolean added = false;
            for(int j = 0; j < array[k].length(); j++){
               if(added) break;
               
               String s = array[k].substring(j,array[k].length());
               if(sused.contains(s)) continue;
               if(!hmap.containsKey(s)){
                  hmap.put(s,new HashSet<Integer>());
               }
               
               
               
               for(int i : hmap.get(s)){
                  if(!used.contains(i)){
                     used.add(i);
                     used.add(k);
                     sused.add(s);
                     added = true;
                  }
               }
               hmap.get(s).add(k);
            }
         }
         
         out.println("Case #" + t + ": " + used.size());
               
      
      }
      
      
      
      
      out.close();
   }
   
      
}