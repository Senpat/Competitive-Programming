//make sure to make new file!
import java.io.*;
import java.util.*;

public class THEATRE{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      int[] map = new int[13];
      map[12] = 0;
      map[3] = 1;
      map[6] = 2;
      map[9] = 3;
      
      for(int q = 0; q < t; q++){
      
         int n = Integer.parseInt(f.readLine());
      
         int[][] matrix new int[4][4];
         
         for(int k = 0; k < n; k++){
            StringTokenizer st = new StringTokenizer(f.readLine());
            char a = st.nextToken().charAt(0);
            int b = Integer.parseInt(st.nextToken());
            
            matrix[a-'A'][map[b]]++;
         }
         
         
         
      }
      
   
      
      
      
      
      
      out.close();
   }
   
      
}