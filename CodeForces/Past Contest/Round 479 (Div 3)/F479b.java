//Consecutive Subsequence
import java.io.*;
import java.util.*;

public class F479b{

   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      
      int n = Integer.parseInt(f.readLine());
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int[] array = new int[n];
      
      for(int k = 0; k < n; k++) array[k] = Integer.parseInt(st.nextToken());
      
      HashMap<Integer, Integer> map = new HashMap<Integer,Integer>();
      
      int max = 1;
      int maxnum = array[0];
      for(int k = 0; k < n; k++){
         if(!map.containsKey(array[k]-1)) map.put(array[k],1);
         else {
            map.put(array[k],map.get(array[k]-1)+1);
            if(map.get(array[k]) > max){
               max = map.get(array[k]);
               maxnum = array[k];
            }
         }
      }
      System.out.println(max);
      for(int k = 0; k < n; k++){
         if(array[k] == maxnum-max+1){
            System.out.print((k+1) + " ");
            max--;
         }
      }
   }
}
            
   