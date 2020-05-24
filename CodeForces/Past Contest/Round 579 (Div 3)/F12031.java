//make sure to make new file!
import java.io.*;
import java.util.*;
//solve F1, from F12032
public class F12031{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      ArrayList<Task2> pos = new ArrayList<Task2>();
      ArrayList<Task1> neg = new ArrayList<Task1>();
      
      for(int k = 0; k < n; k++){
         st = new StringTokenizer(f.readLine());
      
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         
         if(b < 0){
            neg.add(new Task1(Math.max(a,-b),b));
         } else {
            pos.add(new Task2(a,b));
         }
      }
      
      Collections.sort(pos);
      Collections.sort(neg);
      
      int i = 0;
      while(i < pos.size() && pos.get(i).i <= m){
         m+=pos.get(i).d;
         i++;
      }
      
      int answer = i;
      
      int nn = neg.size();
      int[][] dp = new int[nn+1][m+1];
      
      for(int k = 0; k <= nn; k++) Arrays.fill(dp[k],-1);
      
      dp[nn][m] = 0;
      
      for(int k = nn; k > 0; k--){
         for(int j = 0; j <= m; j++){
            if(dp[k][j] == -1) continue;
            
            //don't do project
            dp[k-1][j] = Math.max(dp[k-1][j],dp[k][j]);
            
            //do project
            if(j+neg.get(k-1).d >= 0 && j >= neg.get(k-1).i){
               dp[k-1][j+neg.get(k-1).d] = Math.max(dp[k-1][j+neg.get(k-1).d],dp[k][j]+1);
            }
         }
      }
      
      int max = 0;
      for(int k = 0; k <= m; k++){
         max = Math.max(max,dp[0][k]);
      }
      
      answer += max;
      
      if(answer == n){
         out.println("YES");
      } else {
         out.println("NO");
      } 
      
      

      
      
      
      
      
      out.close();
   }
   
   public static class Task2 implements Comparable<Task2>{
      int i;            //rating needed
      int d;            //rating change
      public Task2(int a, int b){
         i = a;
         d = b;
      }
      public int compareTo(Task2 t){
         return i-t.i;
      }
   }
   
   public static class Task1 implements Comparable<Task1>{
      int i;            //rating needed
      int d;            //rating change
      public Task1(int a, int b){
         i = a;
         d = b;
      }
      public int compareTo(Task1 t){
         return i+d-t.i-t.d;
      }
   }
      
}