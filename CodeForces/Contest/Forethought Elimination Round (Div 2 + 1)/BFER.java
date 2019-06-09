//make sure to make new file!
import java.io.*;
import java.util.*;

public class BFER{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      char[] t = f.readLine().toCharArray();
      
      ArrayList<Letter> noa = new ArrayList<Letter>();
      int lasta = -1;
      for(int k = 0; k < t.length; k++){
         if(t[k] == 'a'){
            lasta = k;
         } else {
            noa.add(new Letter(t[k],k));
         }
      }
      
      if(noa.size() % 2 == 1){
         out.println(":(");
         out.close();
         System.exit(0);
      }
      if(noa.size() == 0){
         for(int k = 0; k < t.length; k++){
            out.print(t[k]);
         }
         out.close();
         System.exit(0);
      }
      
      int last = noa.get(noa.size()/2).i;
      if(lasta > last || !check(noa)){
         out.println(":(");
         out.close();
         System.exit(0);
      }
      
      for(int k = 0; k < last; k++){
         out.print(t[k]);
      }

      
      
      
      
      out.close();
   }
   
   public static boolean check(ArrayList<Letter> alist){
      for(int k = 0; k < alist.size()/2; k++){
         if(alist.get(k).c != alist.get(k+alist.size()/2).c) return false;
      }
      return true;
   }
   
   public static class Letter{
      char c;
      int i;
      public Letter(char a, int b){
         c = a;
         i = b;
      }
   }
   
      
}