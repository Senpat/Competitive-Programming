//make sure to make new file!
import java.io.*;
import java.util.*;

public class C581{
   
   public static int INF = Integer.MAX_VALUE;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
   
      int[][] graph = new int[n][n];
      for(int k = 0; k < n; k++){
         for(int j = 0; j < n; j++){
            graph[k][j] = INF;
            if(k==j) graph[k][j] = 0;
         }
      }
      
      for(int k = 0; k < n; k++){
         String s = f.readLine();
         for(int j = 0; j < n; j++){
            if(s.charAt(j)=='1'){
               graph[k][j] = 1;
            }
         
         }
      }
      
      int m = Integer.parseInt(f.readLine());
      int[] array = new int[m];
      StringTokenizer st = new StringTokenizer(f.readLine());
      for(int k = 0; k < m; k++){
         array[k] = Integer.parseInt(st.nextToken())-1;
      }
      
      //floyd warshall from geeksforgeeks
      int[][] dist = new int[n][n];
      
      for (int i = 0; i < n; i++) 
         for (int j = 0; j < n; j++) 
            dist[i][j] = graph[i][j];
   
      for (int k = 0; k < n; k++) 
      { 
         for (int i = 0; i < n; i++) 
         { 
            for (int j = 0; j < n; j++) 
            {  
               if (dist[i][k] != INF && dist[k][j] != INF && dist[i][k] + dist[k][j] < dist[i][j]) 
                  dist[i][j] = dist[i][k] + dist[k][j]; 
            } 
         } 
      } 
      
      
      ArrayList<Integer> answer = new ArrayList<Integer>();
      
      answer.add(array[0]+1);
      int last = array[0];
      int cur = array[0];
      int diff = -1;
      int index = 0;
      while(index < m){
         cur = array[index];
         diff++;
         if(dist[last][cur] != diff){
            answer.add(array[index-1]+1);
            last = array[index-1];
            cur = last;
            diff = 0;
            index--;
         }
         index++;
         
      }
      
      if(answer.get(answer.size()-1)!=array[m-1]+1){
         answer.add(array[m-1]+1);
      }
      out.println(answer.size());
      for(int i : answer){
         out.print(i+" ");
      }
      
      
      
      out.close();
   }
   
      
}