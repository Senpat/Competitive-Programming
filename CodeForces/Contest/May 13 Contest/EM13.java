//make sure to make new file!
import java.io.*;
import java.util.*;

public class EM13{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      
      ArrayList<Integer> answer = new ArrayList<Integer>();
      
      int first = n%m;
      
      for(int k = 0; k < n/m; k++){
         answer.add(first);
         answer.add(m-first);
         answer.add(0);
      }
      answer.add(first);
      
      out.println(answer.size());
      
      for(int i : answer){
         out.print(i + " ");
      }
      
      
      
      
      
      
      
      
      
      
      out.close();
   }
   
      
}