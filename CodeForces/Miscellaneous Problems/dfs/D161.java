//Distance in Tree
//standard floyd-warshall
//memory limit exceeded
import java.io.*;
import java.util.*;

public class D161{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      int[][] fw = new int[n+1][n+1];
      
      ArrayList<ArrayList<Integer>> alist = new ArrayList<ArrayList<Integer>>(n+1);
      
      for(int k = 0; k <= n; k++) alist.add(new ArrayList<Integer>());
      
      //initialize fw
      for(int k = 0; k <= n; k++) Arrays.fill(fw[k],60000);
      
      for(int k = 1; k <= n; k++) fw[k][k] = 0;
      
      //initialize edges
      
      for(int k = 0; k < n-1; k++){
         st = new StringTokenizer(f.readLine());
         
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         
         alist.get(a).add(b);
         alist.get(b).add(a);
         
         fw[a][b] = 1;
         fw[b][a] = 1;
      }
      
      for(int k = 1; k <= n; k++){
         for(int j = 1; j <= n; j++){
            for(int l = 1; l <= n; l++){
               if(fw[j][l] > fw[j][k] + fw[k][l]){
                  fw[j][l] = fw[j][k] + fw[k][l];
               }
            }
         }
      }
      
      int answer = 0;
      
      for(int k = 1; k <= n; k++){
         for(int j = k; j <= n; j++){
            if(fw[k][j] == m){
               answer++;
            }
         }
      }
      
      out.println(answer);
      
      
      
      out.close();
   }
   
}  