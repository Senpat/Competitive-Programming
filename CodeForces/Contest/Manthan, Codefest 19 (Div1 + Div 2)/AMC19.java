//make sure to make new file!
import java.io.*;
import java.util.*;

public class AMC19{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int q = Integer.parseInt(f.readLine());
      
      for(int t = 0; t < q; t++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         int n = Integer.parseInt(st.nextToken());
         
         int c = a ^ b;
         
         int[] array = {a,b,c};
         
         int answer = array[n%3];
         out.println(answer);
         
      }
      
      
      
      
      out.close();
   }
   
      
}