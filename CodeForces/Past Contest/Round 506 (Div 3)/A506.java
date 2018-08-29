//Many Equal Substrings
import java.io.*;
import java.util.*;

public class A506{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      String s = f.readLine();
      
      int index = 0;
      for(int k = 1; k < n; k++){
         //System.out.println(s.substring(k) + " " + s.substring(0,s.length()-k));
         if(s.substring(k).equals(s.substring(0,s.length()-k))){
            index = k;
            break;
         }
      }
      
      String answer = "";
      answer+=s;
      
      for(int k = 0; k < m-1; k++){
         if(index==0) answer+=s;
         else answer+=s.substring(s.length()-index);
      }
      
      out.println(answer);
      
      out.close();
   }
   
}