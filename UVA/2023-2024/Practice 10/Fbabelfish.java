//make sure to make new file!
import java.io.*;
import java.util.*;

public class Fbabelfish{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      
      HashMap<String,String> hmap = new HashMap<String,String>();
      while(true){
         String s = f.readLine();
         if(s.length() == 0) break;
         StringTokenizer st = new StringTokenizer(s);
         
         String a = st.nextToken();
         String b = st.nextToken();
         
         hmap.put(b,a);
      }
      //out.println("hi");
      while(f.ready()){
         String s = f.readLine();
         if(hmap.containsKey(s)){
            out.println(hmap.get(s));
         } else {
            out.println("eh");
         }
      }
      
      
      
      
      
      
      out.close();
   }
   
      
}