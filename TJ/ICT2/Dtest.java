//make sure to make new file!
import java.io.*;
import java.util.*;

public class Dtest{
   
   public static long MOD = 3;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(new FileWriter("Dtest.out"));
      
      int n = 100;
      
      out.println(n);
      
      String s = "";
      for(int k = 0; k < n; k++){
         s += "" + Math.round(Math.random());
      }
      
      char[] c = s.toCharArray();
      
      out.println(s);
      
      int q = 100;
      out.println(q);
      
      ArrayList<Long> answers = new ArrayList<Long>();
      
      for(int k = 0; k < q; k++){
         if(Math.random() < 0.7){
            int a = (int)(Math.random() * n);
            int b = (int)(Math.random() * n);
         
            int x = Math.min(a,b);
            int y = Math.max(a,b);
         //System.out.println(x + " " + y);
            out.println("1 " + x + " " + y);
         
         //brute force answer
         
            int i = 0;
            long sum = 0;
            for(int j = y; j >= x; j--){
               if(c[j] == '1'){
                  sum += exp(2,i);
               }
               i++;
            }
            //if(sum < 0) System.out.println(l
            //out.println(sum);
            answers.add((sum+3)%3);
         }
         else{
            int o = (int)(Math.random()*n);
            c[o] = '1';
            out.println("0 " + o);
         }
      }
      
      out.println("\n");
      
      for(long i : answers){
         out.print(i + " ");
      }
      
      
      
      out.close();
   }
   
   public static long exp(int base, int power){
   if(power == 0) return 1;
   if(power == 1) return (base + MOD) % MOD;
   long ans = exp(base,power/2);
   ans = (ans * ans + MOD) % MOD;
   if(power%2 == 1) ans = (ans*base + MOD) % MOD;
   return (ans + MOD) % MOD;
   }
   
      
}