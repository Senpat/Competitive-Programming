/*
yeet
*/
import java.util.*;
import java.math.*;
import java.io.*;

   public class B66b
   {
      public static void main(String args[]) throws Exception
      {
         BigInteger constant = new BigInteger(""+Integer.MAX_VALUE);
         BufferedReader infile = new BufferedReader(new InputStreamReader(System.in));  
         StringTokenizer st = new StringTokenizer(infile.readLine());
         //long N = Long.parseLong(st.nextToken());
         int L = Integer.parseInt(st.nextToken());
         Stack<Integer> stck = new Stack<Integer>();
         BigInteger run = new BigInteger("1");
         long res = 0L;
         for(int qw=0; qw < L; qw++)
         {
            st = new StringTokenizer(infile.readLine());
            char c = st.nextToken().charAt(0);
            int num = -1;
            if(c == 'f')
            {
               num = Integer.parseInt(st.nextToken());
               stck.push(num);  
               run = run.multiply(new BigInteger(""+num));
            }
            else if(c == 'e')
               run = run.divide(new BigInteger(""+stck.pop()));
            else
            {
               if(run.compareTo(constant) >= 0)
               {
                  System.out.println("OVERFLOW!!!");
                  return;
               }
               res += run.longValue();
               if(res > Integer.MAX_VALUE)
               {
                  System.out.println("OVERFLOW!!!");
                  return;
               }
            }
         }
         System.out.println(res);
      }
   }