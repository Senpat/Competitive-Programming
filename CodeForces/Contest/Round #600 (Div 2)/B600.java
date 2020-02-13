//make sure to make new file!
import java.io.*;
import java.util.*;

public class B600{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int[] array = new int[n];
      
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(st.nextToken());
      }
      
      ArrayList<Integer> days = new ArrayList<Integer>();
      days.add(0);
      
      HashSet<Integer> entered = new HashSet<Integer>();
      HashSet<Integer> left = new HashSet<Integer>();
      
      for(int k = 0; k < n; k++){
         int i = array[k];
         boolean neg = i < 0;
         i = Math.abs(i);
         
         if(neg){
            if(entered.contains(i)){
               left.add(i);
               entered.remove(i);
            }
            else {
               out.println(-1);
               out.close();
               return;
            }
         } else {
            if(left.contains(i)){
               if(entered.isEmpty()){
                  //make new day
                  days.add(k);
                  left = new HashSet<Integer>();
                  entered.add(i);
               } else {
                  out.println(-1);
                  out.close();
                  return;
               }
            } else {
               if(entered.contains(i)){
                  out.println(-1);
                  out.close();
                  return;
               } else {
                  entered.add(i);
               }
            }
         }
      }
      
      if(!entered.isEmpty()){
         out.println(-1);
         out.close();
         return;
      }
      
      
      days.add(n);
      
      out.println(days.size()-1);
      for(int k = 1; k < days.size(); k++){
         out.print(days.get(k)-days.get(k-1));
         out.print(" ");
      }
      
      
      
      
      
   
      
      
      
      
      
      out.close();
   }
   
      
}