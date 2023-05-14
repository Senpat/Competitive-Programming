//make sure to make new file!
import java.io.*;
import java.util.*;
//in-contest attempt
public class F145{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         long m = Long.parseLong(st.nextToken());
         
         long allsum = 0L;
         long[] a = new long[n];
         int[] b = new int[n];
         StringTokenizer st1 = new StringTokenizer(f.readLine());
         StringTokenizer st2 = new StringTokenizer(f.readLine());
         for(int k = 0; k < n; k++){
            a[k] = Long.parseLong(st1.nextToken());
            b[k] = Integer.parseInt(st2.nextToken());
            allsum += a[k];
         }
         
         int D = 18;
         State[][] jmp = new State[n][D];
         
         for(int k = 0; k < n; k++){
            long x,y,z,sum;
            x = 0;
            if(b[k] == 1){
               y = 0;
               z = m-a[k];
            } else {
               y = a[k];
               z = 0;
            }
            sum = a[k];
            
            jmp[k][0] = new State(x,y,z,sum);
         }
         
         for(int d = 1; d < D; d++){
            for(int k = 0; k < n; k++){
               jmp[k][d] = combine(jmp[k][d-1],jmp[(k + (1 << (d-1)))%n][d-1]);
            }
         }
         /*
         for(int d = 0; d <= 2; d++){
            out.println("Level: " + d);
            for(int k = 0; k < n; k++){
               out.println(jmp[k][d]);
            }
         }*/
         
         long[] answer = new long[n];
         for(int k = 0; k < n; k++){
            State s = new State(-1,-1,-1,-1);
            int start = k;
            int i = n;
            for(int d = D-1; d >= 0; d--){
               if((1 << d) <= i){
                  if(k == start){
                     s = jmp[start][d];
                  } else {
                     s = combine(s,jmp[start][d]);
                  }
                  start = (start + (1 << d))%n;
                  i -= (1 << d);
               }
            }
            
            answer[k] = s.x + s.y + allsum;
         }
         
         StringJoiner sj = new StringJoiner(" ");
         for(int k = 0; k < n; k++){
            sj.add("" + answer[k]);
         }
         out.println(sj.toString());
         
         
      }
      
      
      
      
      out.close();
   }
   
   public static State combine(State s1, State s2){
      long x,y,z,sum;
      x = s1.x + s2.x;
      if(s1.y != s1.sum){
         x += Math.max(0,s2.y-s1.z);
      }
      if(s1.y == s1.sum && s2.y == s2.sum){
         y = s1.sum + s2.sum;
      } else if(s1.y == s1.sum){
         y = s1.sum + s2.y;
      } else {
         y = s1.y;
      }
      z = s2.z;
      if(s2.y == s2.sum){
         //second jump is all 2s
         z = Math.max(z,s1.z-s2.sum);
      }
               
      sum = s1.sum + s2.sum;
      
      return new State(x,y,z,sum);
   }
   
   public static class State{
      long x;              //# of 2 gallons needed (after reaching a 1)
      long y;              //# of gallons before hitting a 1
      long z;              //max excess gallons (filled with a 1)
      long sum;            //sum of a in jump
      public State(long a, long b, long c, long d){
         x = a;
         y = b;
         z = c;
         sum = d;
      }
      public String toString(){
         return x + " " + y + " " + z + " " + sum;
      }
   }
   
      
}