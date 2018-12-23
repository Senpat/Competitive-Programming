//make sure to make new file!
import java.io.*;
import java.util.*;

public class poker{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      HashMap<String,Integer> rank = new HashMap<String,Integer>();
      rank.put("Straight flush",1);
      rank.put("Four of a kind",2);
      rank.put("Full house",3);
      rank.put("Flush",4);
      rank.put("Straight",5);
      rank.put("Three of a kind",6);
      rank.put("Two pair",7);
      rank.put("Pair",8);
      rank.put("High card",9);
      
      
      
      int n = Integer.parseInt(f.readLine());
      
      for(int k = 1; k <= n; k++){
         String r = f.readLine();
         String t = f.readLine();
         
         String winner = "";
         if(rank.get(r) < rank.get(t)){
            winner = "Ryan";
         } else {
            winner = "Tyler";
         }
         
         out.println("Game #" + k + ": " + winner);
      }
      
      
      
      out.close();
   }
   
}