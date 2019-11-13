//make sure to make new file!
import java.io.*;
import java.util.*;

public class A575{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int q = Integer.parseInt(f.readLine());
      
      for(int t = 0; t < q; t++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
         long[] array = new long[3];      
         array[0] = Long.parseLong(st.nextToken());
         array[1] = Long.parseLong(st.nextToken());
         array[2] = Long.parseLong(st.nextToken());
         
         long answer = (array[0] + array[1] + array[2])/2;
         out.println(answer);
         
      }
      
      
      
      
      out.close();
   }
   
      
}