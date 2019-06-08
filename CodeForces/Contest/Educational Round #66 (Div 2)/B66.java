//make sure to make new file!
import java.io.*;
import java.util.*;

public class B66{

   public static long MAX = 4294967295L;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      long multiplier = 1L;
      long answer = 0L;
      Stack<Long> stk = new Stack<Long>();
      for(int k = 0; k < n; k++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
         
         String s = st.nextToken();
         if(s.equals("add")){
            if(multiplier > MAX-answer){
               out.println("OVERFLOW!!!");
               out.close();
               System.exit(0);
            }
            answer+=multiplier;
            if(answer > MAX || multiplier > MAX || multiplier < 0 || answer < 0){
               out.println("OVERFLOW!!!");
               out.close();
               System.exit(0);
            }
         } else if(s.equals("end")){
            multiplier/=stk.pop();
         } else {
            long i = Long.parseLong(st.nextToken());
            stk.add(i);
            multiplier*=i;
         }
      
      }
      
      out.println(answer);
      
      
      
      out.close();
   }
   
      
}