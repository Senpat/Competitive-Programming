//make sure to make new file!
import java.io.*;
import java.util.*;

public class P2165{

   public static ArrayList<Move> answer;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      answer = new ArrayList<Move>();
      
      dothing(n,1,2,3);
      
      StringJoiner sj = new StringJoiner("\n");
      sj.add("" + answer.size());
      for(Move m : answer){
         sj.add(m.toString());
      }
      out.println(sj.toString());
      
      
      
      
      
      
      
      
      out.close();
   }
   
   public static void dothing(int x, int from, int middle, int to){
      if(x == 0){
         return;
      } else {
         dothing(x-1,from,to,middle);
         answer.add(new Move(from,to));
         dothing(x-1,middle,from,to);
      }
   }
   
   public static class Move{
      int from;
      int to;
      public Move(int a, int b){
         from = a;
         to = b;
      }
      public String toString(){
         return from + " " + to;
      }
   }
      
}