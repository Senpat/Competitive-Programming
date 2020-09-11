//make sure to make new file!
import java.io.*;
import java.util.*;

public class B524{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int q = Integer.parseInt(f.readLine());
      
      for(int t = 0; t < q; t++){
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         long l = Long.parseLong(st.nextToken());
         long r = Long.parseLong(st.nextToken());
         
         long answer = sum1(r)-2*sumodd(r) - (sum1(l-1)-2*sumodd(l-1));
         out.println(answer);
         
         
      }
      
      
      
      
      
      
      out.close();
   }
   
   public static long sum1(long x){
      return (x*(x+1))/2;
   }
   
   public static long sumodd(long x){
      return ((x+1)/2)*((x+1)/2);
   }
      
}