//make sure to make new file!
import java.io.*;
import java.util.*;

public class REBIT{
   
   public static long MOD = 998244353L;
   public static long mod4i = 748683265L;
   
   public static void main(String[] args)throws java.lang.Exception{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      
      for(int q = 1; q <= t; q++){
         
         char[] array = f.readLine().toCharArray();
         int n = array.length;
         
         Stack<Character> output = new Stack<Character>();
         Stack<Character> ops = new Stack<Character>();
         
         for(int k = 0; k < n; k++){
            if(array[k] == '#'){
               output.push(array[k]);
            }
            
            if(array[k] == '&' || array[k] == '|' || array[k] == '^'){
               while(!ops.isEmpty() && ops.peek() != '('){
                  output.push(ops.pop());
               }
               ops.push(array[k]);
            }
            
            if(array[k] == '('){
               ops.push(array[k]);
            }
            
            if(array[k] == ')'){
               while(!ops.isEmpty() && ops.peek() != '('){
                  output.push(ops.pop());
               }
               if(!ops.isEmpty() && ops.peek() == '('){
                  ops.pop();
               }
            }
            
         }
         
         while(!ops.isEmpty()){
            output.push(ops.pop());
         }
         
         Stack<Character> values = new Stack<Character>();
         while(!output.isEmpty()){
            values.push(output.pop());
         }
         
         Stack<State> answer = new Stack<State>();
         while(!values.isEmpty()){
            char c = values.pop();
            if(c == '#'){
               answer.add(new State(mod4i,mod4i,mod4i,mod4i));
            } else {
               State a = answer.pop();
               State b = answer.pop();
               
               long a0 = 0;
               long a1 = 0;
               long aa = 0;
               long aA = 0; 
               
               if(c == '&'){
                  a0 = (m(a.a0,b.a0) + m(a.a0,b.a1) + m(a.a0,b.aa) + m(a.a0,b.aA) + m(a.a1,b.a0) + m(a.aa,b.a0) + m(a.aa,b.aA) + m(a.aA,b.a0) + m(a.aA,b.aa) + MOD)%MOD;
                  a1 = (m(a.a1,b.a1) + MOD)%MOD;
                  aa = (m(a.a1,b.aa) + m(a.aa,b.a1) + m(a.aa,b.aa) + MOD)%MOD;
                  aA = (m(a.a1,b.aA) + m(a.aA,b.a1) + m(a.aA,b.aA) + MOD)%MOD;
               }
               if(c == '|'){
                  a0 = (m(a.a0,b.a0) + MOD)%MOD;
                  a1 = (m(a.a0,b.a1) + m(a.a1,b.a0) + m(a.a1,b.a1) + m(a.a1,b.aa) + m(a.a1,b.aA) + m(a.aa,b.a1) + m(a.aa,b.aA) + m(a.aA,b.a1) + m(a.aA,b.aa) + MOD)%MOD;
                  aa = (m(a.a0,b.aa) + m(a.aa,b.a0) + m(a.aa,b.aa) + MOD)%MOD;
                  aA = (m(a.a0,b.aA) + m(a.aA,b.a0) + m(a.aA,b.aA) + MOD)%MOD;
               } 
               if(c == '^'){
                  a0 = (m(a.a0,b.a0) + m(a.a1,b.a1) + m(a.aa,b.aa) + m(a.aA,b.aA) + MOD)%MOD;
                  a1 = (m(a.a0,b.a1) + m(a.a1,b.a0) + m(a.aa,b.aA) + m(a.aA,b.aa) + MOD)%MOD;
                  aa = (m(a.a0,b.aa) + m(a.a1,b.aA) + m(a.aa,b.a0) + m(a.aA,b.a1) + MOD)%MOD;
                  aA = (m(a.a0,b.aA) + m(a.a1,b.aa) + m(a.aa,b.a1) + m(a.aA,b.a0) + MOD)%MOD;
               }
               
               answer.add(new State(a0,a1,aa,aA));
            }
         }
         
         State sanswer = answer.pop();
         out.println(sanswer.a0 + " " + sanswer.a1 + " " + sanswer.aa + " " + sanswer.aA);
         
         
             
               
         
         
      }
      
      out.close();
   }
   
   public static long m(long a, long b){
      return (a*b + MOD)%MOD;
   }
   
   public static class State{
      long a0;
      long a1;
      long aa;
      long aA;
      
      public State(long a, long b, long c, long d){
         a0 = a;
         a1 = b;
         aa = c;
         aA = d;
      }
   }
   
}