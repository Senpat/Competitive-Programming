//make sure to make new file!
import java.io.*;
import java.util.*;
import java.lang.*;

public class I{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      while(true){
         int n = Integer.parseInt(f.readLine());
         if(n == 0) 
            break;
      
         int sumx = 0;
         int sumy = 0;
         
         for(int k = 0; k < n; k++){
            StringTokenizer st = new StringTokenizer(f.readLine());
         
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            sumx += x;
            sumy += y;
         }
         
         int answerx = sumx/n;
         if(n-sumx%n < sumx%n) answerx++;
         int answery = sumy/n;
         if(n-sumy%n < sumy%n) answery++;
         
         out.println(answerx + " " + answery);
      }
      
      
      
      
      
      
      
      out.close();
   }
   
      
}