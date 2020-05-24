//make sure to make new file!
import java.io.*;
import java.util.*;

public class B{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      HashSet<String> images = new HashSet<String>();
      
      String line = f.readLine();
      while(line.length() > 0){
         images.add(line);
         line = f.readLine();
      }
      
      
      ArrayList<String> fanswer = new ArrayList<String>();
      
      HashSet<String> files = new HashSet<String>();
      
      while(f.ready()){
         String s = f.readLine();
         if(s.length() == 0) 
            break;
         String s2 = s.substring(0,secondfromend(s));
         //out.println(s2);
         if(!images.contains(s2)){
            fanswer.add(s);
         }
         
         files.add(s2);
         
      }
      
      ArrayList<String> ianswer = new ArrayList<String>();
      
      for(String image : images){
         if(!files.contains(image)){
            ianswer.add(image);
         }
      }
      
      if(fanswer.size() + ianswer.size() == 0){
         out.println("No mismatches.");
      } else {
      
         Collections.sort(fanswer);
         Collections.sort(ianswer);
      
         StringJoiner sj = new StringJoiner("\n");
         for(String s : fanswer){
            sj.add("F " + s);
         }
         for(String s : ianswer){
            sj.add("I " + s);
         }
         
         out.println(sj.toString());
      }
      
      
      
      
      
      
      
      
      out.close();
   }
   
   public static int secondfromend(String s){
      int i = 0;
      for(int k = s.length()-1; k >= 0; k--){
         if(s.charAt(k) == '_') i++;
         if(i==2) return k;
      }
      return i;//shouldn't happen
   }
   
   
}