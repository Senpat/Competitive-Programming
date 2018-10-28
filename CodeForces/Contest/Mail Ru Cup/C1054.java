//make sure to make new file!
import java.io.*;
import java.util.*;

public class C1054{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      StringTokenizer st2 = new StringTokenizer(f.readLine());
      
      
      int[] left = new int[n];
      int[] right = new int[n];
      int[] sum = new int[n];
      
      for(int k = 0; k < n; k++){
         left[k] = Integer.parseInt(st.nextToken());
         right[k] = Integer.parseInt(st2.nextToken());
         // if(left[k] > k || right[k] > n-k-1){
//             out.println("NO");
//             out.close();
//             System.exit(0);
//          }
         
         
         sum[k] = n-(left[k] + right[k]);
      }
      
      for(int k = 0; k < n; k++){
         //compute left
         int curleft = 0;
         for(int j = 0; j < k; j++){
            if(sum[j] > sum[k]){
               curleft++;
            }
         }
         
         int curright = 0;
         for(int j = k+1; j < n; j++){
            if(sum[j] > sum[k]){
               curright++;
            }
         }
         
         if(curleft!=left[k] || curright!=right[k]){
            out.println("NO");
            out.close();
            System.exit(0);
         }
         
      }
      
      out.println("YES");
      for(int k = 0; k < n; k++) out.print(sum[k] + " ");
      
      
      out.close();
   }
   
}