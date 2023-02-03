//make sure to make new file!
import java.io.*;
import java.util.*;

public class D{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      HashMap<String,String> hmap = new HashMap<String,String>();
      //hopefully no capital letters in the middle of a sequence
      hmap.put("at","@");
      hmap.put("and","&");
      hmap.put("one","1");
      hmap.put("won","1");
      hmap.put("to","2");
      hmap.put("too","2");
      hmap.put("two","2");
      hmap.put("for","4");
      hmap.put("four","4");
      hmap.put("bea","b");
      hmap.put("be","b");
      hmap.put("bee","b");
      hmap.put("sea","c");
      hmap.put("see","c");
      hmap.put("eye","i");
      hmap.put("oh","o");
      hmap.put("owe","o");
      hmap.put("are","r");
      hmap.put("you","u");
      hmap.put("why","y");
      
      hmap.put("At","@");
      hmap.put("And","&");
      hmap.put("One","1");
      hmap.put("Won","1");
      hmap.put("To","2");
      hmap.put("Too","2");
      hmap.put("Two","2");
      hmap.put("For","4");
      hmap.put("Four","4");
      hmap.put("Bea","B");
      hmap.put("Be","B");
      hmap.put("Bee","B");
      hmap.put("Sea","C");
      hmap.put("See","C");
      hmap.put("Eye","I");
      hmap.put("Oh","O");
      hmap.put("Owe","O");
      hmap.put("Are","R");
      hmap.put("You","U");
      hmap.put("Why","Y");
      
      
      for(int q = 1; q <= t; q++){
         String s = f.readLine();
         int n = s.length();
         StringJoiner sj = new StringJoiner("");
         
         int i = 0;
         
         while(i < n){
            //check 4
            if(i+3 < n){
               String cur = "" + s.charAt(i) + Character.toLowerCase(s.charAt(i+1)) + Character.toLowerCase(s.charAt(i+2)) + Character.toLowerCase(s.charAt(i+3));
               
               if(hmap.containsKey(cur)){
                  sj.add(hmap.get(cur));
                  i += 4;
                  continue;
               }
            }         
            //check 3
            if(i+2 < n){
               String cur = "" + s.charAt(i) + Character.toLowerCase(s.charAt(i+1)) + Character.toLowerCase(s.charAt(i+2));
               
               if(hmap.containsKey(cur)){
                  sj.add(hmap.get(cur));
                  i += 3;
                  continue;
               }
            }
            if(i+1 < n){
               String cur = "" + s.charAt(i) + Character.toLowerCase(s.charAt(i+1));
               if(hmap.containsKey(cur)){
                  sj.add(hmap.get(cur));
                  i += 2;
                  continue;
               }
            }
            sj.add("" + s.charAt(i));
            i++;
         }
         
         out.println(sj.toString());
      

      }
      
      
      
      
      out.close();
   }
   
      
}