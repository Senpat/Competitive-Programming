//make sure to make new file!
import java.io.*;
import java.util.*;

public class BMC19{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int n = Integer.parseInt(f.readLine());
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int[] array = new int[n];
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(st.nextToken());
      }
      
      HashSet<Integer> hs = new HashSet<Integer>();
      
      int index = 0;
      while(index < n && !hs.contains(array[index])){
         hs.add(array[index]);
         index++;
      }
      if(index == n){
         out.println(0);
         out.close();
         System.exit(0);
      }
      
      HashSet<Integer> hs2 = new HashSet<Integer>();
      
      int start = index;
      index = n-1;
      while(index >= start && !hs.contains(array[index]) && !hs2.contains(array[index])){
         hs2.add(array[index]);
         index--;
      }
      
      int answer1 = index-start+1;
      
      //do same but for going the other direction
      hs = new HashSet<Integer>();
      index = n-1;
      while(index >= 0 && !hs.contains(array[index])){
         hs.add(array[index]);
         index--;
      }
      
      hs2 = new HashSet<Integer>();
      start = index;
      index = 0;
      while(index <= start && !hs.contains(array[index]) & !hs2.contains(array[index])){
         hs2.add(array[index]);
         index++;
      }
      
      int answer2 = start-index+1;
      
      out.println(Math.min(answer1,answer2));
      
      
      
      
      
      
      
      out.close();
   }
   
      
}