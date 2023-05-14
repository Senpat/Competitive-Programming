//make sure to make new file!
import java.io.*;
import java.util.*;

public class C145{

   public static int[] answer;
   public static int INF = 1000;
   public static int ONE = 1;
   public static int NEG = -31;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
         
         answer = new int[n];
         
         solve(n,m,0);
         
         for(int k = 0; k < n; k++){
            out.print(answer[k] + " ");
         }
         out.println();
         
      }
      
      
      
      
      out.close();
   }
   
   public static void solve(int n, int m, int start){
      //System.out.println(n + " " +  m + " " + start);
      if(start >= answer.length){
         return;
      } else if(m >= n){
         answer[start] = INF;
         solve(n-1,m-n,start+1);
      } else if(m == 0){
         for(int k = 0; k < n; k++){
            answer[k+start] = NEG;
         }
      } else {
         //get longest streak
         int streak = 0;
         for(int k = 0; k < n; k++){
            if(streak+1 > m){
               answer[k+start] = NEG;
               streak = 0;
            } else {
               answer[k+start] = ONE;
               m -= (streak+1);
               streak++;
            }
         }
         
      }
   }
      
}