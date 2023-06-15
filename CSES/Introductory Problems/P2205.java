//make sure to make new file!
import java.io.*;
import java.util.*;

public class P2205{

   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      ArrayList<String> answer = new ArrayList<String>();
      answer.add("0");
      answer.add("1");
      
      for(int k = 2; k <= n; k++){
         ArrayList<String> newanswer = new ArrayList<String>();
         for(int j = 0; j < answer.size(); j++){
            newanswer.add("0" + answer.get(j));
         }
         for(int j = answer.size()-1; j >= 0; j--){
            newanswer.add("1" + answer.get(j));
         }
         answer = newanswer;
      }
      
      for(String s : answer){
         out.println(s);
      }
      
      
      
      
      
      
      
      out.close();
   }
   
      
}