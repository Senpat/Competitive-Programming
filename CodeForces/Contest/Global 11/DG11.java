//make sure to make new file!
import java.io.*;
import java.util.*;

public class DG11{

   public static int n;
   public static int[] array;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      array = new int[n];
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(st.nextToken());
      }
      
      ArrayList<String> answer = new ArrayList<String>();
      
      while(!sorted()){
         answer.add(dothing());
      }
      
      out.println(answer.size());
      for(String s : answer) out.println(s);
   
      
      
      out.close();
   }
   
   public static boolean sorted(){
      for(int k = 0; k < n; k++){
         if(array[k] != k+1) return false;
      }
      return true;
   }
   
   public static int streak(int i){
      int ii = i-1;
      while(array[ii] == array[ii+1]-1){
         ii--;
      }
      return ii+1;
   }
   
   public static int indexof(int x){
      for(int k = 0; k < n; k++){
         if(array[k] == x) 
            return k;
      }
      return -1;
   }
   
   public static int[] convert(int a, int b){
      int[] ret = new int[n];
      for(int k = 0; k < b; k++){
         ret[k] = array[k+a];
      }
      for(int k = 0; k < a; k++){
         ret[k+b] = array[k];
      }
      return ret;
   }
   
   public static int[] convert(int a, int b, int c){
      int[] ret = new int[n];
      for(int k = 0; k < c; k++){
         ret[k] = array[k+a+b];
      }
      for(int k = 0; k < b; k++){
         ret[k+c] = array[k+a];
      }
      for(int k = 0; k < a; k++){
         ret[k+c+b] = array[k];
      }
      return ret;
   }
   
   public static String dothing(){
      int other = array[0]-1;
      if(other == 0) other = n;
      
      //get index of array[0]-1
      int i = indexof(other);
      if(i == n-1){
         int streak = streak(i);
         
         int a = streak;
         int b = n-streak;
         
         array = convert(a,b);
         return "2 " + a + " " + b;
      } else {
      //get longest streak before i
         int streak = streak(i);
         
         int a = streak;
         int b = i-streak+1;
         int c = n-i-1;
         
         array = convert(a,b,c);
         return "3 " + a + " " + b + " " + c;
      }
   }
         
         
         
}