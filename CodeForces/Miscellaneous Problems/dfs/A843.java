//Sorting by Subsequences
import java.io.*;
import java.util.*;

public class A843{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int[] array = new int[n+1];
      int[] sorted = new int[n+1];
      
      sorted[0] = Integer.MIN_VALUE;
      
      for(int k = 1; k <= n; k++){
         array[k] = Integer.parseInt(st.nextToken());
         sorted[k] = array[k];
      }
      
      HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
      HashMap<Integer,Integer> amap = new HashMap<Integer,Integer>();
      
      Arrays.sort(sorted);
      
      for(int k = 1; k <= n; k++){
         map.put(sorted[k],k);
         amap.put(array[k],k);
      }
      
      boolean[] used = new boolean[n+1];
      
      ArrayList<ArrayList<Integer>> subs = new ArrayList<ArrayList<Integer>>();
      
      
      for(int k = 1; k <= n; k++){
         if(!used[k]){
            subs.add(new ArrayList<Integer>());
            
            int ind = k;
            while(!used[ind]){   
               used[ind] = true;
               subs.get(subs.size()-1).add(ind);
               ind = map.get(array[ind]);
            }
            
         }
         
      }
      
      out.println(subs.size());
      
      for(ArrayList<Integer> alist : subs){
         out.print(alist.size() + " ");
         for(int i : alist) out.print(i + " ");
         out.println();
      }
               
               
      
      
      
      out.close();
   }
   
}