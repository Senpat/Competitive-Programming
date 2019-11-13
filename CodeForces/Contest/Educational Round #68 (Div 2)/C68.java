//make sure to make new file!
import java.io.*;
import java.util.*;

public class C68{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 0; q < t; q++){
      
         String a = f.readLine();
         String b = f.readLine();
         String c = f.readLine();
         
         if(solve(a,b,c)){
            out.println("YES");
         } else {
            out.println("NO");
         }
      
      }
      
      
      
      
      out.close();
   }
   
   
   public static  boolean solve(String s, String t, String p){
      int[] pfreq = new int[26];
      for(int k = 0; k < p.length(); k++){
         pfreq[p.charAt(k)-'a']++;
      }
      
      
      int si = 0;
      for(int ti = 0; ti < t.length(); ti++){
         if(si >= s.length() || s.charAt(si) != t.charAt(ti)){
            if(pfreq[t.charAt(ti)-'a']<=0) return false;
            pfreq[t.charAt(ti)-'a']--;
         } else {
            si++;
         }
      }
      if(si < s.length()) return false;
      return true;   
   }
            
   
      
}