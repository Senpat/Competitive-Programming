//make sure to make new file!
import java.io.*;
import java.util.*;

public class D95{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int q = Integer.parseInt(st.nextToken());
      
      Integer[] array = new Integer[n];
      st = new StringTokenizer(f.readLine());
      
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(st.nextToken());
      }
      
      Arrays.sort(array);
      int maxdif = 0;
      TreeMap<Integer,Integer> diffs = new TreeMap<Integer,Integer>();
      for(int k = 1; k < n; k++){
         add(diffs,array[k]-array[k-1]);
      }
      
      TreeSet<Integer> tset = new TreeSet<Integer>();
      
      for(int k = 0; k < n; k++){
         tset.add(array[k]);
      }
      
      int[] answer = new int[q+1];
      if(n <= 1) answer[0] = 0;
      else answer[0] = array[n-1]-array[0] - diffs.lastKey();
      
      for(int t = 1; t <= q; t++){
         st = new StringTokenizer(f.readLine());
      
         int i = Integer.parseInt(st.nextToken());
         int x = Integer.parseInt(st.nextToken());
      
         if(i == 0){
            tset.remove(x);
            Integer floor = tset.lower(x);
            Integer ceil = tset.higher(x);
            if(floor != null){
               remove(diffs,x-floor);
            }
            
            if(ceil != null){
               remove(diffs,ceil-x);
            }
            
            if(floor != null && ceil != null){
               add(diffs,ceil-floor);
            }
            
         } else {
            Integer floor = tset.lower(x);
            Integer ceil = tset.higher(x);
            if(floor != null && ceil != null){
               remove(diffs,ceil-floor);
            }
            
            if(floor != null){
               add(diffs,x-floor);
            }
            
            if(ceil != null){
               add(diffs,ceil-x);
            }
            
            tset.add(x);
         
         
         }
         //out.println(tset.last() + " " + tset.first() + " " + diffs.lastKey());
         if(tset.size() <= 1) answer[t] = 0;
         else answer[t] = tset.last() - tset.first() - diffs.lastKey();
      }
      
      StringJoiner sj = new StringJoiner("\n");
      for(int k = 0; k <= q; k++){
         sj.add("" + answer[k]);
      }
      out.println(sj.toString());
         
      
      
      out.close();
   }
   
   public static void remove(TreeMap<Integer,Integer> tmap, Integer i){
      if(tmap.get(i) > 1){
         tmap.put(i,tmap.get(i)-1);
      } else {
         tmap.remove(i);
      }
   }
   
   public static void add(TreeMap<Integer,Integer> tmap, int i){
      if(!tmap.containsKey(i)){
         tmap.put(i,1);
      } else {
         tmap.put(i,tmap.get(i)+1);
      }
   }
      
}