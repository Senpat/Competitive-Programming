//make sure to make new file!
import java.io.*;
import java.util.*;

public class AHS{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int x = Integer.parseInt(st.nextToken());
         int y = Integer.parseInt(st.nextToken());
         int n = Integer.parseInt(st.nextToken());
         
         
         int[] answer = new int[n];
         answer[0] = x;
         answer[n-1] = y;
         
         int i = 1;
         for(int k = n-2; k >= 1; k--){
            answer[k] = answer[k+1] - i;
            i++;
         }
         
         if(answer[1] - i < x){
            out.println(-1);
         } else {
            StringJoiner sj = new StringJoiner(" ");
            for(int k = 0; k < n; k++){
               sj.add("" + answer[k]);
            }
            out.println(sj.toString());
         }
      

      }
      
      
      
      
      out.close();
   }
   
      
}