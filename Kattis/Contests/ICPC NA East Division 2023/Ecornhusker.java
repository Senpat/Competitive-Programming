//make sure to make new file!
import java.io.*;
import java.util.*;

public class Ecornhusker{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int sum = 0;
      for(int k = 0; k < 5; k++){
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         sum += a*b;
      }
      
      sum /= 5;
      
      st = new StringTokenizer(f.readLine());
      
      int numer = Integer.parseInt(st.nextToken());
      int denom = Integer.parseInt(st.nextToken());
      
      int answer = sum * numer / denom;
      out.println(answer);
      
      
      
      
      
      
      
      
      out.close();
   }
   
      
}