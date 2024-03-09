//make sure to make new file!
import java.io.*;
import java.util.*;

public class P1113{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      String s = f.readLine();
      int n = s.length();
      
      Char[] array = new Char[n];
      int poundindex = -1;
      for(int k = 0; k < n; k++){
         if(s.charAt(k) == '#'){
            poundindex = k;
         }
         array[k] = new Char(s.charAt(k),k);
      }
      
      Arrays.sort(array);
      
      StringJoiner sj = new StringJoiner("");
      int i = poundindex;

      for(int k = 0; k < n-1; k++){
         sj.add("" + array[i].c);
         i = array[i].i;
      }
      
      out.println(sj.toString());      
      
      
      
      
      
      
      
      out.close();
   }
   
   public static class Char implements Comparable<Char>{
      char c;
      int i;
      public Char(char a, int b){
         c = a;
         i = b;
      }
      
      public int compareTo(Char ch){
         if(c == ch.c) return i-ch.i;
         return (int)c - (int)ch.c;
      }
   }
   
      
}