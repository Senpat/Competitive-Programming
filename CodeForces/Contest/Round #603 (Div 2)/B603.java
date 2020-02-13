//make sure to make new file!
import java.io.*;
import java.util.*;

public class B603{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         String[] array = new String[n];
         boolean[] change = new boolean[n];
         for(int k = 0; k < n; k++){
            array[k] = f.readLine();
         }
         
         HashSet<String> hset = new HashSet<String>();
         for(int k = 0; k < n; k++){
            if(!hset.contains(array[k])){
               hset.add(array[k]);
            } else {
               change[k] = true;
            }
         }
         
         int change1 = 0;
         for(int k = 0; k < n; k++){
            if(change[k]){
               change1++;
               for(int j = 0; j <= 9; j++){
                  String s = "" + j + array[k].substring(1);
                  if(!hset.contains(s)){
                     hset.add(s);
                     array[k] = s;
                     break;
                  }
               }
            }
         }
         
         out.println(change1);
         for(int k = 0; k < n; k++){
            out.println(array[k]);
         }
         

      }
      
      
      
      
      out.close();
   }
   
      
}