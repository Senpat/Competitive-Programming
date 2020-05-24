//make sure to make new file!
import java.io.*;
import java.util.*;

public class P2{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      int t = Integer.parseInt(st.nextToken());
      int F = Integer.parseInt(st.nextToken());
      
      for(int q = 1; q <= t; q++){
         
         ArrayList<Character> answer = new ArrayList<Character>();
         
         ArrayList<Integer>[] alist = new ArrayList[5];
         for(int k = 0; k < 5; k++) alist[k] = new ArrayList<Integer>();
         
         for(int k = 0; k < 119; k++){
            out.println(k*5+1);
            out.flush();
            char c = f.readLine().charAt(0);
            alist[c-'A'].add(k);
         }
         
         int i = -1;
         for(int k = 0; k < 5; k++){
            //out.println(alist[k].size());
            if(alist[k].size() == 23){
               i = k;
               answer.add((char)('A'+k));
               break;
            }
         }
         
         ArrayList<Integer>[] alist2 = new ArrayList[5];
         for(int k = 0; k < 5; k++) alist2[k] = new ArrayList<Integer>();
         for(int k = 0; k < 23; k++){
            out.println(alist[i].get(k)*5+2);
            out.flush();
            char c = f.readLine().charAt(0);
            alist2[c-'A'].add(alist[i].get(k));
         }
         
         for(int k = 0; k < 5; k++){
            if(alist2[k].size() == 5){
               i = k;
               answer.add((char)('A'+k));
               break;
            }
         }
         
         ArrayList<Integer>[] alist3 = new ArrayList[5];
         for(int k = 0; k < 5; k++) alist3[k] = new ArrayList<Integer>();
         for(int k = 0; k < 5; k++){
            out.println(alist2[i].get(k)*5+3);
            out.flush();
            char c = f.readLine().charAt(0);
            alist3[c-'A'].add(alist2[i].get(k));
         }
         
         for(int k = 0; k < 5; k++){
            if(alist3[k].size() == 1){
               i = k;
               answer.add((char)('A'+k));
               break;
            }
         }
         
         out.println(alist3[i].get(0)*5+5);
         out.flush();
         answer.add(f.readLine().charAt(0));
         out.println(alist3[i].get(0)*5+4);
         out.flush();
         answer.add(f.readLine().charAt(0));
         
         for(int k = 0; k < 5; k++){
            out.print(answer.get(k));
         }
         out.println();
         out.flush();
         
         char verdict = f.readLine().charAt(0);
         if(verdict == 'N'){
            out.close();
            return;
         }
         
      }
      
      
      
      
      out.close();
   }
   
      
}