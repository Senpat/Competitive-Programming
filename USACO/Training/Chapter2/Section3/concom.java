/*
TASK: concom
LANG: JAVA
*/

import java.io.*;
import java.util.*;

class concom{
   
   public static void main(String[] args) throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("concom.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("concom.out")));
      
      int N = 100;
      
      int n = Integer.parseInt(f.readLine());
      
      int[][] adj = new int[N+1][N+1];
      
      for(int k = 0; k < n; k++){
         StringTokenizer st = new StringTokenizer(f.readLine());
         
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         int p = Integer.parseInt(st.nextToken());
         
         adj[a][b] = p;
      }
      
      ArrayList<Pair> answer = new ArrayList<Pair>();
      for(int k = 1; k <= N; k++){
         int[] control = new int[N+1];
         
         HashSet<Integer> seen = new HashSet<Integer>();
         seen.add(k);
         
         Queue<Integer> q = new LinkedList<Integer>();
         
         for(int j = 1; j <= N; j++){
            control[j] = adj[k][j];
            if(control[j] > 50 && !seen.contains(j)){
               q.add(j);
               seen.add(j);
            }
         }
         
         while(!q.isEmpty()){
            int i = q.poll();
            
            answer.add(new Pair(k,i));
            
            for(int j = 1; j <= N; j++){
               control[j] += adj[i][j];
               if(control[j] > 50 && !seen.contains(j)){
                  q.add(j);
                  seen.add(j);
               }
            }
         }
            
         
         
      }
      
      Collections.sort(answer);
      
      for(int k = 0; k < answer.size(); k++){
         out.println(answer.get(k).a + " " + answer.get(k).b);
      }
        
      out.close();
   }
   
   public static class Pair implements Comparable<Pair>{
      int a;
      int b;
      public Pair(int c, int d){
         a = c;
         b = d;
      }
      
      public int compareTo(Pair p){
         if(a == p.a) return b-p.b;
         return a-p.a;
      }
   }
      
}