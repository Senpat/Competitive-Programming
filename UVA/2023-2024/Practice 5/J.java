//make sure to make new file!
import java.io.*;
import java.util.*;

public class J{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken())-1;
      int k = Integer.parseInt(st.nextToken());
      int g = Integer.parseInt(st.nextToken());
      
      int day = k/g;
      int answer = (n+day-1)/day;
      out.println(answer);
      
      
      
      
      
      
      
      
      out.close();
   }
   
      
}