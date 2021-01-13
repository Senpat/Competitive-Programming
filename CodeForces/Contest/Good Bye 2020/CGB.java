//make sure to make new file!
import java.io.*;
import java.util.*;

public class CGB{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         char[] array = f.readLine().toCharArray();
         int n = array.length;
         
         ArrayList<Pal> pals = new ArrayList<Pal>();
         for(int k = 0; k < n; k++){
            
            if(k < n-2 && array[k] == array[k+1] && array[k] == array[k+2]) pals.add(new Pal(k,3,true));
            else{
               if (k < n-2 && array[k] == array[k+2]) pals.add(new Pal(k,3,false));
               if (k < n-1 && array[k] == array[k+1] && !(k > 0 && array[k-1] == array[k])) pals.add(new Pal(k,2,false));
            }
         }
         
         ArrayList<Integer> changes = new ArrayList<Integer>();
         for(Pal p : pals){
            int last = -1;
            int last2 = -1;
            if(changes.size() >= 1) last = changes.get(changes.size()-1);
            if(changes.size() >= 2) last2 = changes.get(changes.size()-2);
            
            if(p.size == 2){
               if(last < p.x) changes.add(p.x+1);
            } else if(p.size == 3){
               if(p.triple){
                  
                  if(last < p.x){
                     changes.add(p.x+1);
                     changes.add(p.x+2);
                  }
                  else if(last2 < p.x) changes.add(p.x+2);
                  
               } else {
                  if(last < p.x || (last == p.x+1 && last2 < p.x)) changes.add(p.x+2);
               }
            }   
         }
         
         out.println(changes.size());

      }
      
      
      
      
      out.close();
   }
   
   public static class Pal{
      int x;
      int size;
      boolean triple;
      
      public Pal(int a, int b, boolean c){
         x = a;
         size = b;
         triple = c;
      }
   }
      
}