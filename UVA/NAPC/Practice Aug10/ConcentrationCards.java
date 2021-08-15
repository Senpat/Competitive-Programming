//CCO '03 P2 - Concentration Cards
import java.io.*;
import java.util.*;

public class ConcentrationCards{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         
         //make a is smaller, b is bigger
         if(a > b){
            int temp = a;
            a = b;
            b = temp;
         }
         
         int answer = (a*n + b)*2;
         
         for(int k = 1; k <= n; k++){
            if(n % k == 0){
               int rows = n/k;
               
               //rows rows, each with k axb cards
               answer = Math.min(answer, (k*b + rows*a)*2);
               
               //rows rows, each with k bxa cards
               answer = Math.min(answer, (k*a + rows*b)*2);
            }
         }
               
         
         int gcd = gcd(a,b);
         
         a/=gcd;
         b/=gcd;
         
         int mul = 1;
         
         
         while(a*mul <= n){
            int cura = a*mul;
            int curb = b*mul;
            
            //cura is how many axb cards/row, curb is how many bxa cards/row
            
            int numa = 0;
            while(n-cura*numa >= curb){
               if((n-cura*numa)%curb == 0){
                  int numb = (n-cura*numa)/curb;
                  
                  int curanswer = ((numa*a+numb*b) + cura*b) * gcd * 2;
                  answer = Math.min(curanswer,answer);
               }
               
               numa++;
            }
            mul++;
         }
         

         out.println(answer);
      }
      
      
      
      
      out.close();
   }
   
   
   public static int gcd(int a, int b){
      if(a == b) return a;
      if(a < b){
         if(a == 0) return b;
         return gcd(b%a,a);
      }
      if(b < a){
         if(b == 0) return a;
         return gcd(a%b,b);
      }
      return -1;
   }
      
}