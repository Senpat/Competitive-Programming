//make sure to make new file!
import java.io.*;
import java.util.*;

public class E528{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 0; q < t; q++){
      
         int n = Integer.parseInt(f.readLine());
      
         String s = f.readLine();
         String a = f.readLine();
         String b = f.readLine();
      
         HashSet<Character> hs = new HashSet<Character>();
         
         for(int k = 0; k < n; k++){
            hs.add(s.charAt(k));
         }
         
         HashMap<Character,Character> hm = new HashMap<Character,Character>();
         
         boolean solved = false;
         for(char c : hs){
            if(ctoi(c) == n-1){
               if(hm.containsKey(c)){
                  out.println("NO");
                  solved = true;
                  break;
               } else {
                  hm.put(c,c);
               }
            } else {
               if(hm.containsKey((char)(ctoi(c) + 1))){
                  if(hm.containsKey(c)){
                     out.println("NO");
                     solved = true;
                     break;
                  }
               } else {
                  hm.put(c,(char)(ctoi(c) + 1)));
               }
            }
         }
         if(solved) continue;
         
         String s2 = "";
         for(int k = 0; k < n; k++){
            if(hm.containsKey(s.charAt(k))){
               s2+=hm.get(s.charAt(k));
            } else {
               s2+=s.charAt(k);
            }
         }
         
         if(s2.compareTo(s) == 
         
         
      
      }
      
      out.close();
   }
   
   public int ctoi(char c){
      return (int)c - 97;
   }
   
}