//make sure to make new file!
import java.io.*;
import java.util.*;

public class I104757gym{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         char[] array = f.readLine().toCharArray();
         
         int n = array.length;
                  
         ArrayList<Integer> hyphens = new ArrayList<Integer>();
         ArrayList<Integer> nums = new ArrayList<Integer>();
         
         boolean fail = false;
         for(int k = 0; k < n; k++){
            if(array[k] == '-'){
               hyphens.add(k);
            } else if(array[k] == 'X'){
               if(k != n-1){
                  fail = true;
                  break;
               } else {
                  nums.add(10);
               }
            } else {
               nums.add(array[k]-'0');
            }
         }
         
         //check X placement
         if(fail || nums.size() != 10){
            out.println("invalid");
            continue;
         }
         
         //check hyphen placement
         if(!checkhyphens(hyphens,n)){
            out.println("invalid");
            continue;
         }
         
         //check checksum
         int sum = 0;
         for(int k = 0; k < 10; k++){
            sum += (10-k) * nums.get(k);
         }
         if(sum%11 != 0){
            out.println("invalid");
            continue;
         }
         
         ArrayList<Character> answer = new ArrayList<Character>();
         ArrayList<Integer> anums = new ArrayList<Integer>();
         
         answer.add('9'); anums.add(9);
         answer.add('7'); anums.add(7);
         answer.add('8'); anums.add(8);
         answer.add('-');
         
         for(int k = 0; k < n-1; k++){
            answer.add(array[k]);
         }
         for(int i : nums) anums.add(i);
         
         //calculate checksum
         int asum = 0;
         for(int k = 0; k < 12; k++){
            if(k%2 == 0) asum += anums.get(k);
            else asum += 3*anums.get(k);
         }
         
         int csum = 10-(asum%10);
         if(csum == 10) csum = 0;
         answer.add((char)(csum + '0'));
         
         StringJoiner sj = new StringJoiner("");
         for(char c : answer) sj.add("" + c);
         out.println(sj.toString());
         
      }
      
      
      
      
      out.close();
   }
   
   public static boolean checkhyphens(ArrayList<Integer> hyphens, int n){
      int h = hyphens.size();
      if(h > 3) return false;
      if(hyphens.size() == 0) return true;
      
      
      //cannot begin with a hyphen
      if(hyphens.get(0) == 0) return false;
      //cannot end with a hyphen
      if(hyphens.get(h-1) == n-1) return false;
      //cannot contain consecutive hyphens
      for(int k = 1; k < h; k++){
         if((int)hyphens.get(k) == hyphens.get(k-1) + 1) return false;
      }
      
      if(h == 3){
         if(hyphens.get(2) != n-2) return false;
      }
      
      return true;
   }
}