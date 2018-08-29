//Consecutive Subsequence
//wrong answer on test 5 (n=200000)

import java.io.*;
import java.util.*;

public class F479{

   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      
      int n = Integer.parseInt(f.readLine());
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      ArrayList<ArrayList<Integer>> alist = new ArrayList<ArrayList<Integer>>();
      int[] array = new int[n+1];
      
      HashMap<Integer,Integer> map = new HashMap<Integer,Integer>(); //last value, index
      
      for(int k = 1; k <= n; k++){
         int i = Integer.parseInt(st.nextToken());
         array[k] = i;
         if(map.containsKey(i-1)){                                //if contains i-1
            alist.get(map.get(i-1)).add(k);                       //add at correct index
            if(!map.containsKey(i) || alist.get(map.get(i)).size() < alist.get(map.get(i-1)).size()){
               map.put(i,map.get(i-1));
               map.remove(i-1);
            }
         } 
         else {
            ArrayList<Integer> first = new ArrayList<Integer>();
            first.add(k);
            alist.add(first);
            map.put(i,alist.size()-1);
            
         }
            
      }
      
      ArrayList<Integer> maxlist = new ArrayList<Integer>();
      int max = 0;
      
      for(ArrayList<Integer> a : alist){
         if(a.size()>max){
            max = a.size();
            maxlist = a;
         }
      }
      
      System.out.println(max);
      for(int i : maxlist){
         System.out.print(i + " ");
      }
   }
}