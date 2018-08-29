import java.io.*;
import java.util.*;

class sort{
  
   public static int[] array,sorta;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("sort.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("sort.out")));
      
      int n = Integer.parseInt(f.readLine());
      
      array = new int[n];
      sorta = new int[n];
      HashMap<Integer,Integer> hm = new HashMap<Integer,Integer>();
      HashMap<Integer,Integer> freq = new HashMap<Integer,Integer>();
      
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(f.readLine());
         sorta[k] = array[k];
         if(!freq.containsKey(array[k])){
            freq.put(array[k],0);
         } else {
            freq.put(array[k],freq.get(array[k])+1);
         }
         hm.put(array[k],k);
      }
      Arrays.sort(sorta);
      int max = 0;
      for(int k = 0; k < n; k++){
         max = Math.max(max, hm.get(sorta[k])-k-freq.get(sorta[k]));
      }
      
      
      
      System.out.println(max+1);
      out.println(max+1);
      out.close();
   }
   

}