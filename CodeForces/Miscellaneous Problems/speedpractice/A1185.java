//make sure to make new file!
import java.io.*;
import java.util.*;

public class A1185{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int[] array = new int[3];
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      array[0] = Integer.parseInt(st.nextToken());
      array[1] = Integer.parseInt(st.nextToken());
      array[2] = Integer.parseInt(st.nextToken());
      int d = Integer.parseInt(st.nextToken());
      
      Arrays.sort(array);
      
      int answer = Math.max(d-(array[1]-array[0]),0) + Math.max(d-(array[2]-array[1]),0);
      
      out.println(answer);

      
      
      
      
      out.close();
   }
   
      
}