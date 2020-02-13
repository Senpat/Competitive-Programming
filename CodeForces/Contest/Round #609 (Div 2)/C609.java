//make sure to make new file!
import java.io.*;
import java.util.*;

public class C609{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      String s = f.readLine();
      
      String first = gen(s,m);
      if(first.compareTo(s) >= 0){
         out.println(first.length());
         out.println(first);
      } else {
         int index = Math.min(m,n-m-1);
         String s2 = "" + (Integer.parseInt(s.substring(0,index+1))+1) + s.substring(index+1);
         String answer = gen(s2,m);
         out.println(answer.length());
         out.println(answer);
      }
      
      
      
      
      
      out.close();
   }
   
   
   
   public static String gen(String s, int m){
      StringBuilder sb = new StringBuilder(s.substring(0,m));
      
      for(int k = m; k < s.length(); k++){
         sb.append(s.charAt((k+m)%m));
      }
      return sb.toString();
   }
   
   public static boolean check(String s, int m){
      for(int k = 0; k < s.length()-m; k++){
         if(s.charAt(k) != s.charAt(k+m)) return false;
      }
      return true;
   }
      
}