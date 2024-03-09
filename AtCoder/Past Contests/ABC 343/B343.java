//make sure to make new file!
import java.io.*;
import java.util.*;

public class B343{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      for(int k = 1; k <= n; k++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
         for(int j = 1; j <= n; j++){
            int i = Integer.parseInt(st.nextToken());
            if(i == 1){
               out.print(j + " ");
            }
         }
         out.println();
      }
      
      
      
      
      
      
      
      out.close();
   }
   
      
}