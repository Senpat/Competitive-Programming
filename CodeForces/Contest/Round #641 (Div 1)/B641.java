//make sure to make new file!
import java.io.*;
import java.util.*;
//WRONG
public class B641{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         int n = Integer.parseInt(st.nextToken());
         int m = Integer.parseInt(st.nextToken());
         
         int[] array = new int[n];
         st = new StringTokenizer(f.readLine());
         for(int k = 0; k < n; k++){
            array[k] = Integer.parseInt(st.nextToken());
         }
         
         if(n == 1 && array[0] == m){
            out.println("yes");
            continue;
         }
         
         if(check(array,m)){
            out.println("yes");
         } else {
            out.println("no");
         }
      
      }
      
      
      
      
      out.close();
   }
   
   public static boolean checkallsame(int[] array,int m){
      for(int k = 0; k < array.length; k++){
         if(array[k] != m) return false;
      }
      return true;
   }
   
   public static boolean check(int[] array, int m){
      int n = array.length;
      
      //left to right
      //find first index
      int index = -1;
      for(int k = 0; k < n; k++){
         if(array[k] == m){
            index = k;
            break;
         }
      }
      
      if(index == -1) return false;
      
      int cur = 1;
      for(int k = index+1; k < n; k++){
         //update cur
         if(array[k] >= m){
            cur++;
         } else {
            cur--;
         }
         
         if(cur > 0) return true;
         
         if(array[k] == m){
            cur = Math.max(cur,1);
         }
      }
      
      //right to left
      //get last index
      index = -1;
      for(int k = 0; k < n; k++){
         if(array[k] == m){
            index = k;
         }
      }
      
      cur = 1;
      for(int k = index-1; k >= 0; k--){
         //update cur
         if(array[k] >= m){
            cur++;
         } else {
            cur--;
         }
         
         if(cur > 0) return true;
         
         if(array[k] == m){
            cur = Math.max(cur,1);
         }
      }
      
      return false;
      
      
      
   }

      
}