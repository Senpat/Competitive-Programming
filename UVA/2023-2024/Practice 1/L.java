//make sure to make new file!
import java.io.*;
import java.util.*;

public class L{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      String c1 = st.nextToken();
      
      int i = 0;
      for(int k = 0; k < c1.length(); k++){
         i += c1.charAt(k)-'!';
         if(k > 0) i++;
      }
      
      int ca = st.nextToken().charAt(0)-'a';
      
      int diff = (ca-i+26)%26;
      
      String s;
      while(f.ready()){
         s = f.readLine();
         
         st = new StringTokenizer(s);
         StringJoiner sj = new StringJoiner("");
         
         while(st.hasMoreTokens()){
            String token = st.nextToken();
            
            if(token.equals("0")){
               sj.add(" ");
            } else if(token.equals("<")){
               sj.add(",");
            } else if(token.equals(">")){
               sj.add(".");
            } else {
            
               i = 0;
               for(int k = 0; k < token.length(); k++){
                  i += token.charAt(k)-'!';
                  if(k > 0) i++;
               }
               
               
               char c = (char)((i+diff)%26+'a');
               sj.add("" + c);
            
            }
         }
         
         out.println(sj.toString());
      }
      
      
      
      
      
      
      
      
      
      out.close();
   }
   
      
}