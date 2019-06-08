//make sure to make new file!
import java.io.*;
import java.util.*;

public class B65{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int[] array = new int[4];
      
      out.println("? 1 2");
      out.flush();
      array[0] = Integer.parseInt(f.readLine());
      out.println("? 2 3");
      out.flush();
      array[1] = Integer.parseInt(f.readLine());
      out.println("? 3 4");
      out.flush();
      array[2] = Integer.parseInt(f.readLine());
      out.println("? 4 5");
      out.flush();
      array[3] = Integer.parseInt(f.readLine());
   
      ArrayList<HashSet<Integer>> prod = new ArrayList<HashSet<Integer>>(1000);
      for(int k = 0; k < 1000; k++) prod.add(new HashSet<Integer>());
      Integer[] nums = {4,8,15,16,23,42};
      HashSet<Integer> numset = new HashSet<Integer>(Arrays.asList(nums));
      
      for(int k = 0; k < 6; k++){
         for(int j = 0; j < 6; j++){
            if(k==j) 
               continue;
            prod.get(nums[k]*nums[j]).add(nums[k]);
            prod.get(nums[k]*nums[j]).add(nums[j]);
         }
      }
      
      int[] answer = new int[6];
      
      //1 is the intersection between 0 and 1
      for(int i : prod.get(array[0])){
         if(prod.get(array[1]).contains(i)){
            answer[1] = i;
         } else {
            answer[0] = i;
         }
         numset.remove(i);
      }
      for(int k = 1; k < 4; k++){
         for(int i : prod.get(array[k])){
            if(i!=answer[k]){
               answer[k+1] = i;
               numset.remove(i);
            }
         }
      }
      
      for(int i : numset){
         answer[5] = i;
      
      }
      
      out.print("!");
      for(int k = 0; k < 6; k++){
         out.print(" " + answer[k]);
      }
      
      
      out.close();
   }
   
      
}