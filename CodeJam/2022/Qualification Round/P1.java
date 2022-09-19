//make sure to make new file!
import java.io.*;
import java.util.*;

public class P1{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
         out.println("Case #" + q + ":");
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int m = Integer.parseInt(st.nextToken());
         int n = Integer.parseInt(st.nextToken());
         
         //add first row
         StringJoiner sj = new StringJoiner("");
         sj.add("..+");
         for(int k = 1; k < n; k++) sj.add("-+");
         sj.add("\n..|");
         for(int k = 1; k < n; k++) sj.add(".|");
         sj.add("\n+");
         for(int k = 0; k < n; k++) sj.add("-+");
         sj.add("\n");
         for(int k = 1; k < m; k++){
            sj.add("|");
            for(int j = 0; j < n; j++){
               sj.add(".|");
            }
            sj.add("\n+");
            for(int j = 0; j < n; j++){
               sj.add("-+");
            }
            sj.add("\n");
         }
         
         out.println(sj.toString());
      
      

      }
      
      
      
      
      out.close();
   }
   
      
}