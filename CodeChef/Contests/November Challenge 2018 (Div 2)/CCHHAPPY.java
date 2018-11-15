//make sure to make new file!
//brute force
import java.io.*;
import java.util.*;

public class CCHHAPPY{
   
   public static void main(String[] args)throws java.lang.Exception{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int q = Integer.parseInt(f.readLine());
      
      for(int k = 0; k < q; k++){
      
         int n = Integer.parseInt(f.readLine());
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int[] array = new int[n+1];
         
         for(int p = 1; p <= n; p++){
            array[p] = Integer.parseInt(st.nextToken());
         }
         
         if(check2(array,n)) out.println("Truly Happy");
         else out.println("Poor Chef");
         
         
      
      }      
      
      
      out.close();
   }
   
   public static boolean check2(int[] array, int n){
      HashSet<Integer> hset = new HashSet<Integer>();
      for(int k = 1; k <= n; k++) hset.add(array[k]);
      
      HashMap<Integer,HashSet<Integer>> map = new HashMap<Integer,HashSet<Integer>>();
      
      int msize = 0;
      for(int k = 1; k <= n; k++){
         if(!map.containsKey(array[k])){
            map.put(array[k],new HashSet<Integer>());
         }
         map.get(array[k]).add(k);
         msize = Math.max(msize,map.get(array[k]).size());
      }
      
      if(msize == n) return false;
      
      for(int i : map.keySet()){
         if(map.get(i).size() >= 2){
            int cc = 0;
            for(int in : map.get(i)){
               if(hset.contains(in)){
                  cc++;
                  if(cc >= 2) return true;
               }
            }
         }
      }
      
      return false;
   
            
   
   }
   
   public static boolean check(int[] array,int n){
      for(int k = 1; k <= n; k++){
         for(int j = 1; j <= n; j++){
            if(j!=k){
               if(array[k] != array[j] && array[array[k]] == array[array[j]]){
                  return true;
               }
            }
         }
      }
      return false;
   }
         
   
}