//make sure to make new file!
import java.io.*;
import java.util.*;
//danny
public class B692{

   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
   
      char[] array = f.readLine().toCharArray();
      int n = array.length;
   
      StringTokenizer st = new StringTokenizer(f.readLine());
   
      long x = Long.parseLong(st.nextToken());
      long y = Long.parseLong(st.nextToken());
      
      //start with all 0
      long start = 0L;
      long num0 = 0L;
      long num1 = 0L;
      for(int k = 0; k < n; k++){
         if(array[k] == '1'){
            start += x*num0;
            num1++;
         } else {
            start += y*num1;
            num0++;
         }
      }
      
      long answer = start;
      
      long cur = start;
      long right1 = num1;
      long left1 = 0L;
      
      long right0 = num0;
      long left0 = 0L;
      for(int k = 0; k < n; k++){
         if(array[k] == '?'){
            //simulate changing to 1
            cur -= y*left1 + x*right1;
            cur += x*left0 + y*(right0-1);
            answer = Math.min(cur,answer);
            right0--;
            left1++;
         } else if(array[k] == '0'){
            right0--;
            left0++;
         } else if(array[k] == '1'){
            right1--;
            left1++;
         }
      }
      
      
      cur = start;
      right1 = 0L;
      left1 = num1;
      
      right0 = 0L;
      left0 = num0;
      for(int k = n-1; k >= 0; k--){
         if(array[k] == '?'){
            //simulate changing to 1
            cur -= y*left1 + x*right1;
            cur += x*(left0-1) + y*right0;
            answer = Math.min(cur,answer);
            left0--;
            right1++;
         } else if(array[k] == '0'){
            right0++;
            left0--;
         } else if(array[k] == '1'){
            right1++;
            left1--;
         }
      }
      
      
      
      out.println(answer);
      
   
   
   
   
   
      out.close();
   }

   
}