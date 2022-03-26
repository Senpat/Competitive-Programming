//make sure to make new file!
import java.io.*;
import java.util.*;

public class C769{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         
         int N = 20;
         
         int answer = b-a;
         for(int add = 0; a+add <= b; add++){
            int newanswer = 1+((a+add)|b)-b+add;                  //or then add to b
            //add to b then or
            
            int i = (1 << N);
            
            int newa = a;
            int newb = b;
            while(i > 0){
               int ai = a & i;
               int bi = b & i;
               
               if(ai != 0 && bi != 0){
                  newa -= ai;
                  newb -= bi;
               } else if(ai == 0 && bi != 0){
                  newb -= bi;
               } else if(ai != 0 && bi == 0){
                  break;
               }
               i >>= 1;
            }
               
            newanswer = Math.min(newanswer, 1+add+(newa-newb));
            answer = Math.min(answer,newanswer);
         }
         
         out.println(answer);

      }
      
      
      
      
      out.close();
   }
   
      
}