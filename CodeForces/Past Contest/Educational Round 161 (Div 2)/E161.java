//make sure to make new file!
import java.io.*;
import java.util.*;

public class E161{
   
   public static ArrayList<String> answer;
   public static int[] numsize = new int[]{0,1,2,2,3,3,4,3};
   public static int size;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         long x = Long.parseLong(f.readLine());
         answer = new ArrayList<String>();
         size = 0;
         if(x <= 8){
            String ret = num(x-1,0);
            out.println(size);
            out.println(ret);
            continue;
         }
         //get digits base 8
         int zero = 1000;
         int digit = 0;
         while(x > 0){
            long mod = x%8;
            x /= 8;
            if(x > 0){
               answer.add(num(mod,digit));
               answer.add(zeros(zero));
            } else {
               answer.add(num(mod-1,digit));
            }
            zero -= 3;
            digit += 4;
         }
         
         out.println(size);
         StringJoiner sj = new StringJoiner(" ");
         for(int k = answer.size()-1; k >= 0; k--){
            if(answer.get(k).length() == 0) continue;
            sj.add(answer.get(k));
         }
         
         out.println(sj.toString());
      }
      
      
      
      
      out.close();
   }
   
   public static String num(long x, int s){
      size += numsize[(int)x];
      if(x == 0L) return "";
      if(x == 1L) return "" + s;
      if(x == 2L) return "" + s + " " + s;
      if(x == 3L) return "" + s + " " + (s+1);
      if(x == 4L) return "" + s + " " + (s+1) + " " + s;
      if(x == 5L) return "" + s + " " + (s+1) + " " + (s+1);
      if(x == 6L) return "" + s + " " + (s+1) + " " + (s+1) + " " + s;
      if(x == 7L) return "" + s + " " + (s+1) + " " + (s+2);
      return "";        //won't reach here
   }
   
   public static String zeros(int s){
      size += 3;
      return "" + s + " " + (s+1) + " " + (s+2);
   }     
}