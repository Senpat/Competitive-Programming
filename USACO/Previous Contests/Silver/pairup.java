import java.io.*;
import java.util.*;

class pairup{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("pairup.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("pairup.out")));
      
      int n = Integer.parseInt(f.readLine());
      
      Cow[] array = new Cow[n];
      
      for(int k = 0; k < n; k++){
         StringTokenizer st = new StringTokenizer(f.readLine());
         array[k] = new Cow(Integer.parseInt(st.nextToken()),Integer.parseInt(st.nextToken()));
      }
      
      Arrays.sort(array);
      
      int[] cum = new int[n];
      cum[0] = 0;
      for(int k = 1; k < cum.length; k++){
         cum[k] = cum[k-1] + array[k-1].num;
      }
      
      int total = cum[n-1] + array[n-1].num;
      
      int max = array[0].amt + array[n-1].amt;
      
      int cur = n-1;
      
      for(int k = 1; k < cur; k++){
         int rank = total-1-cum[k];
         
         while(cur >= 0 && cum[cur] > rank) cur--;
         
         max = Math.max(array[k].amt + array[cur].amt,max);
      }
      
      System.out.println(max);
      out.println(max);
      out.close();
      
   }
   
   
   static class Cow implements Comparable<Cow>{
      int num;
      int amt;
      
      public Cow(int a, int b){
         num = a;
         amt = b;
      }
      
      public int compareTo(Cow c){
         return amt-c.amt;
      }
   }
}