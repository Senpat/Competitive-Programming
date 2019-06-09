//make sure to make new file!
import java.io.*;
import java.util.*;
//fst WA tc37 in contest, upsolved
public class B562{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      int[][] array = new int[m][2];
      
      for(int k = 0; k < m; k++){
         st = new StringTokenizer(f.readLine());
      
         int a = Integer.parseInt(st.nextToken());
         int b = Integer.parseInt(st.nextToken());
         
         array[k][0] = a;
         array[k][1] = b;
      }
      
      if(check(array,array[0][0]) || check(array,array[0][1])){
         out.println("YES");
      } else {
         out.println("NO");
      }

      
      
      
      
      out.close();
   }
   
   public static boolean check(int[][] array, int i){
      //assume i is one of the ints
      int other1 = -1;
      int other2 = -1;
      for(int k = 1; k < array.length; k++){
         if(array[k][0] == i || array[k][1] == i) continue;
         
         //neither
         if(other1 == -1 && other2 == -1){
            other1 = array[k][0];
            other2 = array[k][1];
         } else if(other2 == -1){
            if(array[k][0] != other1 && array[k][1] != other1) return false;
         } else {
            //both filled
            
            //both equal to neither
            if(array[k][0] != other1 && array[k][1] != other1 && array[k][0] != other2 && array[k][1] != other2){
               return false;
            }
            
            //one is equal to one other, set that to other1 
            if((array[k][0] == other1 && array[k][1] != other2) || (array[k][1] == other1 && array[k][0] != other2)){                     //these two ifs caused FST
               other2 = -1;
            } else if((array[k][0] == other2 && array[k][1] != other1) || (array[k][1] == other2 && array[k][0] != other1)){
               other1 = other2;
               other2 = -1;
            }
          }
       }
       return true;
   
   }
      
}