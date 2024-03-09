//make sure to make new file!
import java.io.*;
import java.util.*;

public class A906{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         char[] cin = f.readLine().toCharArray();
         
         if(n%2 == 1){
            out.println("-1");
            continue;
         }
         
         ArrayList<Integer> array = new ArrayList<Integer>();
         int sum = 0;
         for(int k = 0; k < n; k++){
            array.add(Character.getNumericValue(cin[k]));
            sum += array.get(k);
         }
         
         if(sum != n/2){
            out.println("-1");
            continue;
         }
         
         ArrayList<Integer> answer = new ArrayList<Integer>();
         
         int front = 0;
         int back = n;
         
         int li = (int)array.get(0);
         int ri = (int)array.get(n-1);
         
         int l = 1;
         int r = n-2;
         
         int cancel = 0;
         while(cancel < n){
            //out.println(li + " " + l + " " + r + " " + ri);
            //out.flush();
            if(li != ri){
               li = (int)array.get(l);
               ri = (int)array.get(r);
               l++;
               r--;
               cancel+=2;
               front++;
               back--;
            } else if(li == 1){
               //add 01 to the beginning
               answer.add(front);
               front++;
               back++;
               
               li = 1;
               l--;
               ri = (int)array.get(r);
               r--;
               cancel++;
               
               array.add(l,1);
               l++;
               r++;
            } else if(li == 0){
               //add 01 to the end
               answer.add(back);
               front++;
               back++;
               
               ri = 0;
               li = (int)array.get(l);
               l++;
               r++;
               
               array.add(r+1,0);
               cancel++;
            }
            
         }
         
         out.println(answer.size());
         StringJoiner sj = new StringJoiner(" ");
         for(int i : answer){
            sj.add("" + i);
         }
         out.println(sj.toString());

      }
      
      
      
      
      out.close();
   }
   
      
}