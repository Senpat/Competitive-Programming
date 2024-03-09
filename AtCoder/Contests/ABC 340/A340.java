//make sure to make new file!
import java.io.*;
import java.util.*;

public class A340{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int s = Integer.parseInt(st.nextToken());
      int e = Integer.parseInt(st.nextToken());
      int d = Integer.parseInt(st.nextToken());
      
      StringJoiner sj = new StringJoiner(" ");
      int i = s;
      while(i <= e){
         sj.add("" + i);
         i += d;
      }
      out.println(sj.toString());
      
      
      
      
      
      
      
      
      out.close();
   }
   
      
}