//make sure to make new file!
import java.io.*;
import java.util.*;

public class D770{
   
   public static BufferedReader f;
   public static PrintWriter out;
   
   public static void main(String[] args)throws IOException{
      f = new BufferedReader(new InputStreamReader(System.in));
      out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         int n = Integer.parseInt(f.readLine());
      
         if(n == 4){
            int q1 = query(1,2,3);
            int q2 = query(1,2,4);
            int q3 = query(1,3,4);
            int q4 = query(2,3,4);
            
            int max = Math.max(Math.max(q1,q2),Math.max(q3,q4));
            
            ArrayList<Integer> answers = new ArrayList<Integer>();
            if(q1 != max) answers.add(4);
            if(q2 != max) answers.add(3);
            if(q3 != max) answers.add(2);
            if(q4 != max) answers.add(1);
            
            if(answers.size() == 1){
               out.println("! " + answers.get(0) + " 1");
            } else {
               out.println("! " + answers.get(0) + " " + answers.get(1));
            }
            out.flush();
            continue;
         }
            
            
         //find an extreme in n-2 queries
         int[] q1 = new int[n+1];
         for(int k = 3; k <= n; k++){
            q1[k] = query(1,2,k);
         }
         
         //check if both extremes are in 1 or 2
         boolean all = true;
         int allint = q1[3];
         for(int k = 3; k <= n; k++){
            if(q1[k] != q1[3]){
               all = false;
               break;
            }
         }
         /*
         if(all){
            out.println("! 1 2");
            out.flush();
            continue;
      
         }*/
         
         int max = -1;
         int ex1 = -1;
         for(int k = 3; k <= n; k++){
            if(q1[k] > max){
               max = q1[k];
               ex1 = k;
            }
         }
         
         ArrayList<Integer> qlist = new ArrayList<Integer>();
         ArrayList<Integer> x1list = new ArrayList<Integer>();
         ArrayList<Integer> x2list = new ArrayList<Integer>(); 
         
         //get other extreme
         for(int k = 1; k <= n; k++){
            if(k == ex1) 
               continue;
            
            int x1 = k;
            int x2 = k+1;
            if(x2 == ex1) x2++;
            if(x2 > n) 
               continue;
            
            int cur = query(x1,x2,ex1);
            
            qlist.add(cur);
            x1list.add(x1);
            x2list.add(x2);
         }
         
         //get max
         max = -1;
         int maxindex = -1;
         for(int k = 0; k < qlist.size(); k++){
            if(qlist.get(k) > max){
               max = qlist.get(k);
               maxindex = k;
            }
         }
         
         int ex2 = -1;
         if(maxindex == 0 && qlist.get(1) != max){
            ex2 = x1list.get(0);
         } else if(maxindex == qlist.size()-1){
            ex2 = x2list.get(x2list.size()-1);
         } else {
            int x1 = x1list.get(maxindex);
            int x2 = x2list.get(maxindex);
            int x3 = x2list.get(maxindex+1);
            
            //either x2 = ex2, or both x1 and x3 = ex2
            
            int cur = query(ex1,x1,x3);
            
            if(cur == max){
               ex2 = x1;
            } else {
               ex2 = x2;
            }
         }
         
         if(all && max <= allint) out.println("! 1 2");
         else out.println("! " + ex1 + " " + ex2);
         out.flush();
            
            
            
         
      
      
      }
      
      
      
      
      out.close();
   }
   
   
   public static int query(int a, int b, int c)throws IOException{
      out.println("? " + a + " " + b + " " + c);
      out.flush();
      
      return Integer.parseInt(f.readLine());
   }
      
}