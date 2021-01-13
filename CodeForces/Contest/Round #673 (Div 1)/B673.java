//make sure to make new file!
import java.io.*;
import java.util.*;

public class B673{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         long[] array = new long[n+1];
         long sum = 0L;
         for(int k = 1; k <= n; k++){
            array[k] = Long.parseLong(st.nextToken());
            sum += array[k];
         }
         
         if(sum%(long)n != 0){
            out.println("-1");
            continue;
         }
         
         long average = sum/(long)n;
         
         ArrayList<Move> answer = new ArrayList<Move>();
         
         long move;
         for(int k = 2; k <= n; k++){
            long kl = (long)k;
            if(array[k]%kl != 0){
               move = kl-(array[k]%kl);
               answer.add(new Move(1,k,move));
               array[1] -= move;
               array[k] += move;
            }
            
            //make it 0
            move = array[k]/kl;
            answer.add(new Move(k,1,move));
            array[1] += array[k];
            array[k] = 0;
         }
         
         //everything is 0 except array[1]
         for(int k = 2; k <= n; k++){
            answer.add(new Move(1,k,average));
            array[1] -= average;
            array[k] = average;
         }
         
         StringJoiner sj = new StringJoiner("\n");
         sj.add("" + answer.size());
         for(int k = 0; k < answer.size(); k++){
            sj.add(answer.get(k).toString());
         }
         out.println(sj.toString());
      
                 
         
      

      }
      
      
      
      
      out.close();
   }
   
   public static class Move{
      int i;
      int j;
      long x;
      public Move(int a, int b, long c){
         i = a;
         j = b;
         x = c;
      }
      public String toString(){
         return "" + i + " " + j + " " + x;
      }
   }
   
      
}