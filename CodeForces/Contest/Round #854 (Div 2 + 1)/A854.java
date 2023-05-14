//make sure to make new file!
import java.io.*;
import java.util.*;

public class A854{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
         
         st = new StringTokenizer(f.readLine());
         int[] array = new int[m];
         for(int k = 0; k < m; k++){
            array[k] = Integer.parseInt(st.nextToken());
         }
         
         int[] answer = new int[n];
         Arrays.fill(answer,-1);
         int ai = n-1;
         
         HashSet<Integer> seen = new HashSet<Integer>();
         
         for(int k = 0; k < m; k++){
            if(seen.contains(array[k])) continue;
            answer[ai] = k+1;
            seen.add(array[k]);
            ai--;
            if(ai < 0) break;
         }
         
         StringJoiner sj = new StringJoiner(" ");
         for(int k = 0; k < n; k++){
            sj.add("" + answer[k]);
         }
         out.println(sj.toString());
      

      }
      
      
      
      
      out.close();
   }
   
      
}