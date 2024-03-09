//make sure to make new file!
import java.io.*;
import java.util.*;

public class A21{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      String s = f.readLine();
      int n = s.length();
      
      int atindex = -1;
      int slashindex = n;
      
      boolean fail = false;
      for(int k = 0; k < n; k++){
         if(s.charAt(k) == '@'){
            if(atindex != -1){
               fail = true;
               break;
            }
            atindex = k;
         }
         
         if(s.charAt(k) == '/'){
            if(slashindex != n){
               fail = true;
               break;
            }
            slashindex = k;
         }
      }
      
      if(fail){
         out.println("NO");
         out.close();
         return;
      }
      
      //check username
      for(int k = 0; k < atindex; k++){
         if(!checkchar(s.charAt(k))){
            fail = true;
            break;
         }
      }
      
      int userlen = atindex;
      if(userlen < 1 || userlen > 16){
         fail = true;
      }
      
      //check hostname
      int lastp = atindex;
      for(int k = atindex+1; k < slashindex; k++){
         if(!checkchar(s.charAt(k)) && s.charAt(k) != '.'){
            fail = true;
            break;
         }
         if(s.charAt(k) == '.'){
            int wordlen = k-lastp-1;
            lastp = k;
            if(wordlen < 1 || wordlen > 16){
               fail = true;
               break;
            }
            
         }
      }
      
      int wordlen = slashindex - lastp -1;
      if(wordlen < 1 || wordlen > 16){
         fail = true;
      }
      
      int hostlen = slashindex-1 - (atindex+1) + 1;
      if(hostlen < 1 || hostlen > 32){
         fail = true;
      }
      
      //check resource
      if(slashindex != n){
         for(int k = slashindex+1; k < n; k++){
            if(!checkchar(s.charAt(k))){
               fail = true;
               break;
            }  
         }
         
         int resourcelen = n-1 - (slashindex+1) + 1;
         if(resourcelen < 1 || resourcelen > 16){
            fail = true;
         }
      }
      
      if(fail){
         out.println("NO");
      } else {
         out.println("YES");
      }
      
      
      
      
      
      out.close();
   }
   
   public static boolean checkchar(char c){
      return (c >= '0' && c <= '9') || (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || c == '_';
   }
      
}