//make sure to make new file!
import java.io.*;
import java.util.*;

public class FG{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      ArrayList<Pair> answer=  new ArrayList<Pair>();
      
      int i = 1;
      while(i*2 < n){
         i *= 2;
      }
      
      for(int k = 1; k < i; k *= 2){                     //size
         for(int j = 1; j < i; j += k*2){                //starting point
            for(int h = j; h < j+k; h++){
               answer.add(new Pair(h,h+k));
            }
         }
      }
      
      StringJoiner sj = new StringJoiner("\n");
      sj.add("" + answer.size()*2);
      for(Pair p : answer){
         sj.add("" + p.a + " " + p.b);
      }
      for(Pair p : answer){
         sj.add("" + (p.a+n-i) + " " + (p.b+n-i));
      }
      
      
      out.println(sj.toString());
      
      
      
      
      
      
      
      
      out.close();
   }
   
   public static class Pair{
      int a;
      int b;
      public Pair(int c, int d){
         a = c;
         b = d;
      }
   }
   
      
}