//make sure to make new file!
import java.io.*;
import java.util.*;

public class CSI{
   
   public static BufferedReader f;
   public static PrintWriter out;
   
   public static void main(String[] args)throws IOException{
      f = new BufferedReader(new InputStreamReader(System.in));
      out = new PrintWriter(System.out);
      
      ArrayList<Character> chars = new ArrayList<Character>();
      
      for(int k = 0; k < 5; k++){
         String s = "";
         for(int j = 0; j < 5; j++){
            s += (char)('a'+(k*5)+j);
         }
         String res = query(s);
         if(res.equals("22222")) return;
         
         for(int j = 0; j < 5; j++){
            if(res.charAt(j) != '0'){
               chars.add(s.charAt(j));
            }
         }
      }
      
      if(chars.size() != 5) chars.add('z');
      
      char[] answer = new char[5];
      Arrays.fill(answer,'?');
      
      for(int k = 0; k < 4; k++){
         String s = "";
         for(int j = 0; j < 5; j++){
            s += chars.get(k);
         }
         
         String res = query(s);
         if(res.equals("22222")) return;
         
         answer[res.indexOf('2')] = chars.get(k);
      }
      
      String ansstring = "";
      for(int k = 0; k < 5; k++){
         if(answer[k] == '?') ansstring += chars.get(4);
         else ansstring += answer[k];
      }
      query(ansstring);    
      
         
      
      
      
      
      out.close();
   }
   
   public static String query(String s) throws IOException{
      out.println("? " + s);
      out.flush();
      
      return f.readLine();
   }
      
}