//make sure to make new file!
import java.io.*;
import java.util.*;
//in Kotlin Practice 9, different method
//"add" two arrays and divide by 2
public class E550{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());

      char[] a = f.readLine().toCharArray();
      char[] b = f.readLine().toCharArray();
      
      int[] add = new int[n];
      boolean carry = false;
      for(int k = n-1; k >= 0; k--){
         add[k] = (a[k]-'a') + (b[k]-'a');
         
         if(carry) add[k]++;
         
         if(add[k] >= 26 && k != 0){
            carry = true;
            add[k] -= 26;
         } else {
            carry = false;
         }
      }
      
      int[] answer = new int[n];
      carry = false;
      for(int k = 0; k < n; k++){
         if(carry) add[k] += 26;
         if(add[k]%2 == 1){
            carry = true;
         } else {
            carry = false;
         }
         answer[k] = add[k]/2;
      }
      
      StringJoiner sj = new StringJoiner("");
      for(int k = 0; k < n; k++){
         sj.add("" + (char)(answer[k]+'a'));
      }
      out.println(sj.toString());
      
      
      
      
      
      
      
      
      
      
      out.close();
   }
   
      
}