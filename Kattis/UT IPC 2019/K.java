//make sure to make new file!
import java.io.*;
import java.util.*;

public class K{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      double i = 0.0;
      for(int k = 0; k < n; k++){
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         double x1 = Double.parseDouble(st.nextToken());
         double y1 = Double.parseDouble(st.nextToken());
         double x2 = Double.parseDouble(st.nextToken());
         double y2 = Double.parseDouble(st.nextToken());
         
         if(Math.floor(x1) != Math.floor(x2)){
            i+=1.0;
         }
         
      
      }
      
      double answer = 2.0/(i/((double)n));
      out.println(answer);
      
      
      
      out.close();
   }
   
      
}