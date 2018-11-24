//make sure to make new file!
import java.io.*;
import java.util.*;

public class A512{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      for(int k = 0; k < n; k++){
         if(Integer.parseInt(st.nextToken()) == 1){
            out.println("HARD");
            out.close();
            System.exit(0);
         }
      }
      
      out.println("EASY");
      
      
      
      out.close();
   }
   
}