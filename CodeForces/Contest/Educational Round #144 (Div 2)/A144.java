//make sure to make new file!
import java.io.*;
import java.util.*;

public class A144{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      HashSet<String> hset = new HashSet<String>();
      
      for(int s = 0; s < 15; s++){
         String cur = "";
         for(int k = 0; k < 40; k++){
            if((s+k) % 15 == 0){
               hset.add(cur + "F");
               if(cur.length() > 1) hset.add(cur.substring(1)+"F");
               cur += "FB";
            } else if((s+k)%3 == 0){
               cur += "F";
            } else if((s+k)%5 == 0){
               cur += "B";
            }
            
            hset.add(cur);
            if(cur.length() > 1) hset.add(cur.substring(1));
         }
      }
      /*
      for(String s : hset){
         out.println(s);
      }
      */     
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
         
         int n = Integer.parseInt(f.readLine());
         String s = f.readLine();
      
         if(hset.contains(s)){
            out.println("YES");
         } else {
            out.println("NO");
         }
      }
      
      
      
      
      out.close();
   }
   
      
}