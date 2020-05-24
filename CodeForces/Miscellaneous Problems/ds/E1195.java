//make sure to make new file!
import java.io.*;
import java.util.*;

public class E1195{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      
      st = new StringTokenizer(f.readLine());
      
      long g0 = Long.parseLong(st.nextToken());
      long x = Long.parseLong(st.nextToken());
      long y = Long.parseLong(st.nextToken());
      long z = Long.parseLong(st.nextToken());
      
      long[] g = new long[n*m+5];
      
      g[0] = g0;
      
      for(int k = 1; k < n*m+5; k++){
         g[k] = (g[k-1]*x+y+z)%z;
      }
      
      
      long[][] matrix = new long[n][m];
      
      for(int k = 0; k < n; k++){
         for(int j = 0; j < m; j++){
            matrix[k][j] = g[k*m+j];
         }
      }
         
      long[][] cols = new long[n-a+1][m];
      
      //fill cols
      for(int k = 0; k < m; k++){
         Deque<Long> dq = new LinkedList<Long>();
         //initial
         for(int j = 0; j < a; j++){
            while(!dq.isEmpty() && dq.peekLast() > matrix[j][k]){
               dq.pollLast();
            }
            dq.add(matrix[j][k]);
         }
         
         for(int j = 0; j < n-a+1; j++){
            //get minimum
            cols[j][k] = dq.peekFirst();
            
            //remove
            if(!dq.isEmpty() && dq.peekFirst() == matrix[j][k]){
               dq.pollFirst();
            }
            
            //add next one
            if(j+a<n){
               while(!dq.isEmpty() && dq.peekLast() > matrix[j+a][k]){
                  dq.pollLast();
               }
               dq.add(matrix[j+a][k]);
            }
               
         }
      }
      
      long answer = 0L;
      
      for(int k = 0; k < n-a+1; k++){
         //min of all subarrays of cols of len=b
         
         Deque<Long> dq = new LinkedList<Long>();
         for(int j = 0; j < b; j++){
            while(!dq.isEmpty() && dq.peekLast() > cols[k][j]){
               dq.pollLast();
            }
            dq.add(cols[k][j]);
         }
         
         for(int j = 0; j < m-b+1; j++){
            //get minimum
            answer += dq.peekFirst();
            
            //remove
            if(!dq.isEmpty() && dq.peekFirst() == cols[k][j]){
               dq.pollFirst();
            }
            
            //add next one
            if(j+b<m){
               while(!dq.isEmpty() && dq.peekLast() > cols[k][j+b]){
                  dq.pollLast();
               }
               dq.add(cols[k][j+b]);
            }
         }
         
      }
      
      
      out.println(answer);
      
      
      out.close();
   }
   
      
}