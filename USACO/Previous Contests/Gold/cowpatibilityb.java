/*
TASK: cowpatibility
LANG: JAVA
*/

import java.io.*;
import java.util.*;

class cowpatibilityb{
   
   public static void main(String[] args) throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("cowpatibility.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cowpatibility.out")));
      
      
      int n = Integer.parseInt(f.readLine());
      
      int[][] matrix = new int[n][5];
      HashMap<Integer,TreeSet<Integer>> hm = new HashMap<Integer,TreeSet<Integer>>();
      //HashMap<Integer,ArrayList<Integer>> hm = new HashMap<Integer,ArrayList<Integer>>();
      for(int k = 0; k < n; k++){
         StringTokenizer st = new StringTokenizer(f.readLine());
         for(int j = 0; j < 5; j++){
            int i = Integer.parseInt(st.nextToken());
            matrix[k][j] = i;
            if(!hm.containsKey(i)) hm.put(i,new TreeSet<Integer>());
            hm.get(i).add(k);
         }
      }
      
      long answer = 0L;
      
      for(int k = 0; k < n; k++){
         HashSet<Integer> hset = new HashSet<Integer>();
         for(int j = 0; j < 5; j++){
            hset.addAll(hm.get(matrix[k][j]).tailSet(k+1));
         }
         
         answer+=n-k-1-hset.size();
      }
      
      System.out.println(answer);
      out.println(answer);
      
      
      
        
        
      out.close();
   }
      
}