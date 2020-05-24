//make sure to make new file!
import java.io.*;
import java.util.*;

public class C84{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      int nicetrollproblemn = Integer.parseInt(st.nextToken());
      
      for(int k = 0; k < nicetrollproblemn*2; k++){
         st = new StringTokenizer(f.readLine());
         int nicetrollproblem = Integer.parseInt(st.nextToken()) + Integer.parseInt(st.nextToken());
      }
      
      StringJoiner sj = new StringJoiner("");
      for(int k = 0; k < m-1; k++){
         sj.add("L");
      }
      for(int k = 0; k < n-1; k++){
         sj.add("U");
      }
      
      for(int k = 0; k < m; k++){
         if(k%2 == 0){
            for(int j = 0; j < n-1; j++){
               sj.add("D");
            }
         } else {
            for(int j = 0; j < n-1; j++){
               sj.add("U");
            }
         }
         
         if(k < m-1){
            sj.add("R");
         }
      }
      
      out.println(sj.length());
      out.println(sj.toString());
      
      

      
      
      
      
      
      out.close();
   }
   
      
}