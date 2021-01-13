//make sure to make new file!
import java.io.*;
import java.util.*;

public class D101{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         ArrayList<Query> answer = new ArrayList<Query>();
         if(n <= 32){
            for(int k = 3; k < n; k++){
               answer.add(new Query(k,n));
            }
            int i = n;
            while(i > 1){
               answer.add(new Query(n,2));
               i = (i+1)/2;
            }
         } else {
            int X = 16;
            for(int k = 3; k < n; k++){
               if(k != X) answer.add(new Query(k,n));
            }
            
            int a = X;
            int b = n;
            
            
            while(!((a == 2 && b == 1) || (a == 1 && b == 2))){
               if(a == b){
                  answer.add(new Query(n,2));
                  b = (b+1)/2;
            
               } else if(a < b){
                  answer.add(new Query(n,X));
                  b = (b+a-1)/a;
               } else {
                  answer.add(new Query(X,n));
                  a = (a+b-1)/b;
               }
            }
            /*
            while(b > 2){
               answer.add(new Query(n,X));
               b = (b+X-1)/X;
            }
            
            while(a > 1){
               answer.add(new Query(X,n));
               a = (a+1)/2;
            }*/
            
            
            if(b == 2){
               answer.add(new Query(2,n));
            } else if(a == 2){
               answer.add(new Query(2,X));
            }
         }
         
         out.println(answer.size());
         StringJoiner sj = new StringJoiner("\n");
         for(Query query : answer){
            sj.add(query.a + " " + query.b);
         }
         out.println(sj.toString());
         
      

      }
      
      
      
      
      out.close();
   }
   
   public static class Query{
      int a;
      int b;
      public Query(int c, int d){
         a = c;
         b = d;
      }
   }
      
}