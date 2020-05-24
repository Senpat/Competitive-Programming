//make sure to make new file!
import java.io.*;
import java.util.*;

public class P1{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         
         String[] array = new String[n];
         for(int k = 0; k < n; k++){
            array[k] = f.readLine();
         }
         
         String answer = "";
         int i = 0;
         boolean fail = false;
         while(true){
            HashSet<Character> hset = new HashSet<Character>();
            for(int k = 0; k < n; k++){
               if(equals(array[k],answer,i)){
                  hset.add(array[k].charAt((i)%array[k].length()));
               }
            }
            /*
            for(char c : hset){
               out.println(i + " " + c);
            }*/
            if(hset.size() == 3){
               fail = true;
               break;
            }
            
            if(hset.size() == 1){
               for(char c : hset){
                  if(c == 'R') answer += 'P';
                  if(c == 'P') answer += 'S';
                  if(c == 'S') answer += 'R';
               }
               break;
            }
            
            if(hset.size() == 0){
               //shouldn't happen
               break;
            }
            
            if(hset.size() == 2){
               if(!hset.contains('R')) answer+='S';
               if(!hset.contains('S')) answer+='P';
               if(!hset.contains('P')) answer+='R';
            }
            i++;
         }
         
         if(fail){
            out.println("Case #" + q + ": IMPOSSIBLE");
         } else {
            out.println("Case #" + q + ": " + answer);
         }
            

      }
      
      
      
      
      out.close();
   }
   
   public static boolean equals(String s,String answer,int i){
      for(int k = 0; k < i; k++){
         if(s.charAt(k%s.length())!=answer.charAt(k)) return false;
      }
      return true;
   }
      
}