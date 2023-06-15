//make sure to make new file!
import java.io.*;
import java.util.*;

public class P1070{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      if(n == 2 || n == 3){
         out.println("NO SOLUTION");      
      } else {
         ArrayList<Integer> answer = new ArrayList<Integer>();
         //even numbers first
         for(int k = 2; k <= n; k += 2){
            answer.add(k);
         }
         //then odd numbers
         for(int k = 1; k <= n; k += 2){
            answer.add(k);
         }
         StringJoiner sj = new StringJoiner(" ");
         for(int i : answer){
            sj.add("" + i);
         }
         out.println(sj.toString());
      }
      
      
      
      
      
      
      
      out.close();
   }
   
      
}