//make sure to make new file!
import java.io.*;
import java.util.*;

public class A668{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
         
         char[] array = f.readLine().toCharArray();
         
         boolean fail = false;
         for(int k = 0; k < m; k++){
            char c = getvalue(array,k,m);
            if(c == '!'){
               fail = true;
               break;
            }
            setvalue(array,k,m,c);
         }
         
         if(fail){
            out.println("NO");
            continue;
         }
         
         if(check(array,m)){
            out.println("YES");
         } else {
            out.println("NO");
         }
      
      
      }
      
      
      
      
      out.close();
   }
   
   public static boolean check(char[] array,int m){
      
      int numq = 0;
      int num0 = 0;
      int num1 = 0;
      
      //initialize
      for(int k = 0; k < m; k++){
         if(array[k] == '?') numq++;
         if(array[k] == '1') num1++;
         if(array[k] == '0') num0++;
      }
      
      if(numq+num0 < m/2 || numq+num1 < m/2) return false;
      
      for(int k = m; k < array.length; k++){
         if(array[k-m] == '?') numq--;
         if(array[k-m] == '1') num1--;
         if(array[k-m] == '0') num0--;
         
         if(array[k] == '?') numq++;
         if(array[k] == '1') num1++;
         if(array[k] == '0') num0++;
         
         if(numq+num0 < m/2 || numq+num1 < m/2) return false;
      }
      
      return true;
   }
      
   
   //'!' means fail
   public static char getvalue(char[] array, int start, int d){
      
      char cur = '?';
      for(int k = start; k < array.length; k+=d){
         if(array[k] != '?'){
            if(cur != '?' && cur != array[k]) 
               return '!';
            cur = array[k];
         }
      }
      
      return cur;
   }
   
   public static void setvalue(char[] array, int start, int d, char c){
      for(int k = start; k < array.length; k+=d){
         array[k] = c;
      }
   }
      
}