//Military Problem
//too slow
import java.io.*;
import java.util.*;

public class E498{

   public static ArrayList<ArrayList<Integer>> stor,alist;
   public static boolean[] used;
   public static int[] array;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      
      StringTokenizer st = new StringTokenizer(f.readLine());

      int n = Integer.parseInt(st.nextToken());
      int q = Integer.parseInt(st.nextToken());
      
      st = new StringTokenizer(f.readLine());
      
      alist = new ArrayList<ArrayList<Integer>>(n+1);
      array = new int[n+1];
      for(int k = 0; k < n+1; k++) alist.add(new ArrayList<Integer>());
      
      for(int k = 2; k <= n; k++){
         int i = Integer.parseInt(st.nextToken());
         alist.get(i).add(k);
         array[k] = i;
      }
      
      stor = new ArrayList<ArrayList<Integer>>(n+1);
      used = new boolean[n+1];
      //Arrays.fill(used,false);
      for(int k = 0; k < n+1; k++) stor.add(new ArrayList<Integer>());
      for(int k = 2; k < n+1; k++){
         dothing(k);
      }
      
      for(int k = 0; k < q; k++){
         st = new StringTokenizer(f.readLine());
         int u = Integer.parseInt(st.nextToken());
         int i = Integer.parseInt(st.nextToken());
         if(i==1){
            System.out.println(u);
            continue;
         }
         if(i>stor.get(u).size()+1){
            System.out.println(-1);
            continue;
         }
         System.out.println(stor.get(u).get(i-2));
      }
   }
   
   public static void dothing(int i){
      if(used[i]) return;
      int cur = i;
      while(cur!=1){
         used[cur] = true;
         stor.get(array[cur]).add(i);
         cur = array[cur];
      }
      //stor.get(1).add(i);
      for(int k : alist.get(i)){
         dothing(k);
      }
   }
   
   public static void p(ArrayList<ArrayList<Integer>> alist){
      for(int k = 0; k < alist.size(); k++){
         System.out.print(k+ ": ");
         for(int i : alist.get(k)){
            System.out.print(i + " ");
         }
         System.out.println();
      }
   }
         
   

}