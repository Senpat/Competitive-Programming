/*
TASK: cowpatibility
LANG: JAVA
*/

import java.io.*;
import java.util.*;

class cowpatibility{
   
   public static void main(String[] args) throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("cowpatibility.in"));
      PrintWriter out1 = new PrintWriter(new BufferedWriter(new FileWriter("cowpatibility.out")));
      
      
      int n = Integer.parseInt(f.readLine());
      
      int[][] matrix = new int[n][5];
      //HashMap<Integer,TreeSet<Integer>> hm = new HashMap<Integer,TreeSet<Integer>>();
      //HashMap<Integer,ArrayList<Integer>> hm = new HashMap<Integer,ArrayList<Integer>>();
      for(int k = 0; k < n; k++){
         StringTokenizer st = new StringTokenizer(f.readLine());
         for(int j = 0; j < 5; j++){
            int i = Integer.parseInt(st.nextToken());
            matrix[k][j] = i;
            
         }
      }
      
      for(int k = 0; k < n; k++){
         Arrays.sort(matrix[k]);
      }
      ArrayList<HashSet<Integer>> in = new ArrayList<HashSet<Integer>>(1000001);
      ArrayList<HashSet<Integer>> out = new ArrayList<HashSet<Integer>>(1000001);
      
      for(int k = 0; k <= 1000000; k++){
         in.add(new HashSet<Integer>());
         out.add(new HashSet<Integer>());
      }
      
      for(int k = 0; k < n; k++){
         for(int j = 1; j < 5; j++){
            in.get(matrix[k][j-1]).add(matrix[k][j]);
            out.get(matrix[k][j]).add(matrix[k][j-1]);
         }
      }
      
      long answer = 0L;
      for(int k = 0; k <= 1000000; k++){
         int a = in.get(k).size();
         int b = out.get(k).size();
         if(a != b && 1-a!=b){
            //System.out.println(k);
            answer++;
         }
      }
      
      for(int k = 0; k < n-1; k++){
         if(matrix[k][4] == matrix[k+1][0]) answer++;
      }
      
      
      
      long total = 1L;
      for(int k = 2; k <= n; k++) total*=k;
      total /= 4;
      System.out.println(total-answer);
      out1.println(total-answer);
      
      
      
        
        
      out1.close();
   }
      
}