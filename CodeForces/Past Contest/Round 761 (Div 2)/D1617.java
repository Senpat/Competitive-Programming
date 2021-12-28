//Too Many Impostors (hard version)
import java.io.*;
import java.util.*;

public class D1617{

   public static BufferedReader f;
   public static PrintWriter out;
   
   public static int[] answer;
   
   public static void main(String[] args)throws IOException{
      f = new BufferedReader(new InputStreamReader(System.in));
      out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         int n = Integer.parseInt(f.readLine());
      
         answer = new int[n+1];
         Arrays.fill(answer,-1);
         
         int[] thirds = new int[n/3];
         int th0 = -1;
         int th1 = -1;
         
         for(int k = 0; k < n/3; k++){
            thirds[k] = query(k*3+1, k*3+2, k*3+3);
            
            if(th0 == -1 && thirds[k] == 0) th0 = k;
            if(th1 == -1 && thirds[k] == 1) th1 = k;
         }
         
         //figure out all of th0 and th1 (6 values in 9 queries)
         int th01 = th0*3+1;
         int th02 = th0*3+2;
         int th03 = th0*3+3;
         
         int th11 = th1*3+1;
         int th12 = th1*3+2;
         int th13 = th1*3+3;
         
         int a1 = query(th01,th11,th12);
         int a2 = query(th01,th11,th13);
         int a3 = query(th01,th12,th13);
         
         int b1 = query(th02,th11,th12);
         int b2 = query(th02,th11,th13);
         int b3 = query(th02,th12,th13);
         
         if(a1+a2+a3+b1+b2+b3 == 6){               //th1 all 1s
            answer[th11] = 1;
            answer[th12] = 1;
            answer[th13] = 1;
            
            solve0third(th0,th11);
         } else if(a1+a2+a3 == 1){
            answer[th01] = 0;
            int x0 = th01;                         //index guaranteed to be 0
            int x1 = a3 == 1 ? th13 : th11;        //index guaranteed to be 1
            if(a1 == 1){
               answer[th11] = 1;
               answer[th12] = 1;
               answer[th13] = 0;
            } else if(a2 == 1){
               answer[th11] = 1;
               answer[th12] = 0;
               answer[th13] = 1;
            } else if(a3 == 1){
               answer[th11] = 0;
               answer[th12] = 1;
               answer[th13] = 1;
            }
            
            answer[th02] = query(th02,x0,x1);
            answer[th03] = query(th03,x0,x1);
         } else if(b1+b2+b3 == 1){
            answer[th02] = 0;
            int x0 = th02;                         //index guaranteed to be 0
            int x1 = b3 == 1 ? th13 : th11;        //index guaranteed to be 1
            if(b1 == 1){
               answer[th11] = 1;
               answer[th12] = 1;
               answer[th13] = 0;
            } else if(b2 == 1){
               answer[th11] = 1;
               answer[th12] = 0;
               answer[th13] = 1;
            } else if(b3 == 1){
               answer[th11] = 0;
               answer[th12] = 1;
               answer[th13] = 1;
            }
            
            answer[th01] = query(th01,x0,x1);
            answer[th03] = query(th03,x0,x1);
         }  //shouldn't be any other cases
         
         int x0 = -1;
         int x1 = -1;
         
         for(int k = 1; k <= 3; k++){
            if(x0 == -1 && answer[th0*3+k] == 0) x0 = th0*3+k;
            if(x1 == -1 && answer[th1*3+k] == 1) x1 = th1*3+k;
         }
         
         for(int k = 0; k < n/3; k++){
            if(k == th0 || k == th1) 
               continue;
            
            if(thirds[k] == 0){
               solve0third(k,x1);
            } else {
               solve1third(k,x0);
            }
         }
         
         ArrayList<Integer> imposters = new ArrayList<Integer>();
         for(int k = 1; k <= n; k++){
            if(answer[k] == 0) imposters.add(k);
         }
         
         StringJoiner sj = new StringJoiner(" ");
         sj.add("!");
         sj.add("" + imposters.size());
         for(int i : imposters){
            sj.add("" + i);
         }
         
         out.println(sj.toString());
         out.flush();
         
         
      
      }
      
      
      
      
      out.close();
   }
   
   //index of third, index of number guaranteed to be 1
   public static void solve0third(int third, int x1)throws IOException{
      int i1 = third*3+1;
      int i2 = third*3+2;
      int i3 = third*3+3;
      
      int q1 = query(x1,i1,i2);
      if(q1 == 0){
         answer[i1] = 0;
         answer[i2] = 0;
         answer[i3] = query(x1,i1,i3);
      } else {                               //q1 = 1, one of i1 or i2 is 1
         int q2 = query(x1,i1,i3);
         if(q2 == 0){
            answer[i1] = 0;
            answer[i2] = 1;
            answer[i3] = 0;
         } else {
            answer[i1] = 1;
            answer[i2] = 0;
            answer[i3] = 0;
         }
      }
   }
   
   //index of third, index of number guaranteed to be 0
   public static void solve1third(int third, int x0)throws IOException{
      int i1 = third*3+1;
      int i2 = third*3+2;
      int i3 = third*3+3;
      
      int q1 = query(x0,i1,i2);
      if(q1 == 1){
         answer[i1] = 1;
         answer[i2] = 1;
         answer[i3] = query(x0,i1,i3);
      } else {                               //q1 = 1, one of i1 or i2 is 1
         int q2 = query(x0,i1,i3);
         if(q2 == 1){
            answer[i1] = 1;
            answer[i2] = 0;
            answer[i3] = 1;
         } else {
            answer[i1] = 0;
            answer[i2] = 1;
            answer[i3] = 1;
         }
      }
   }
         
   
   public static int query(int a, int b, int c) throws IOException {
      out.println("? " + a + " " + b + " " + c);
      out.flush();
      
      return Integer.parseInt(f.readLine());
   }
   
      
}