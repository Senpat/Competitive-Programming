//make sure to make new file!
import java.io.*;
import java.util.*;

public class A337{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      int a = 0;
      int b = 0;
      
      for(int k = 0; k < n; k++){
         StringTokenizer st = new StringTokenizer(f.readLine());
         
         a+=Integer.parseInt(st.nextToken());
         b+=Integer.parseInt(st.nextToken());
      }
      
      if(a > b){
         out.println("Takahashi");
      } else if(a < b){
         out.println("Aoki");
      } else {
         out.println("Draw");
      }
      
      
      
      
      
      
      
      out.close();
   }
   
      
}