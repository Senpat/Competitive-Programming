//make sure to make new file!
import java.io.*;
import java.util.*;

public class GMBIT{
   
   public static BufferedReader f;
   public static PrintWriter out;
   
   public static void main(String[] args)throws IOException{
      f = new BufferedReader(new InputStreamReader(System.in));
      out = new PrintWriter(System.out);
      
      
      
      
      ArrayList<Character> used = new ArrayList<Character>();
      
      char[] chars = new char[62];
      for(int k = 0; k < 26; k++){
         chars[k] = (char)('a'+ k);
         chars[k+26] = (char)('A'+k);
      }
      for(int k = 0; k < 10; k++){
         chars[k+52] = (char)(k+'0');
      }
      
      for(int k = 0; k < 62; k++){
         String s = query(chars[k]);
         if(s.equals("C")){
            return;
         } else if(s.equals("Y")){
            used.add(chars[k]);
         }
      }
      
      String answer = ""+used.get(0);
      
      for(int k = 1; k < used.size(); k++){
         
         //bs to know where to insert used.get(k)
         
         int l = 0;
         int r = answer.length()-1;
         int ans = -1;
         
         while(l <= r){
            int mid = l+(r-l)/2;
            
            String s = query("" + used.get(k) + answer.charAt(mid));
            if(s.equals("C")){
               return;
            }
            
            if(s.equals("Y")){
               r = mid-1;
               ans = mid;
            } else {
               l = mid+1;
            }
         }
         
         if(ans == -1){
            answer += used.get(k);
         } else {
            answer = answer.substring(0,ans) + used.get(k) + answer.substring(ans);
         }
         //out.println(answer);
      }
      
      out.println(answer);
         
      
      
      
      
      
      
      out.close();
   }
   
   public static String query(Object o) throws IOException{
      out.println(o);
      out.flush();
      
      return f.readLine();
   }
      
}