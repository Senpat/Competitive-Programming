//Alien Rhyme 2
import java.io.*;
import java.util.*;

public class Solution{
   
   public static int MAXW = 55;
   
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
         //HashSet<Integer> used = new HashSet<Integer>();
         //HashSet<String> sused = new HashSet<String>();
                  
         for(int k = 0; k < n; k++){
            for(int j = 0; j < array[k].length(); j++){
               String s = array[k].substring(j,array[k].length());
               if(!hmap.containsKey(s)){
                  hmap.put(s,new HashSet<Integer>());
               }
               hmap.get(s).add(k);
            }
         }
         
         
         ArrayList<HashSet<String>> bucket = new ArrayList<HashSet<String>>(MAXW);
         for(int k = 0; k < MAXW; k++) bucket.add(new HashSet<String>());
         for( String s : hmap.keySet()){
            if(hmap.get(s).size() > 1){
               bucket.get(s.length()).add(s);
            }
         }
         
         HashSet<Integer> used = new HashSet<Integer>();
         for(int k = MAXW-1; k >= 1; k--){
            for(String s : bucket.get(k)){
               
               int first = -1;
               int second = -1;
               for(int i : hmap.get(s)){
                  if(used.contains(i)) 
                     continue;
                  if(first == -1){
                     first = i;
                  } else {
                     second = i;
                     break;
                  }
               }
               
               if(first != -1 && second != -1){
                  used.add(first);
                  used.add(second);
               }
               
            }
         }
         
         out.println("Case #" + t + ": " + used.size());
               
      
      }
      
      
      
      
      out.close();
   }
   
      
}