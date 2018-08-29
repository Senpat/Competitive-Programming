import java.io.*;
import java.util.*;

class snowboots{
   public static int discarded=0;
   public static void main(String[] args) throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("snowboots.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("snowboots.out")));
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      
      int[] array = new int[n];
      int[] prefix = new int[n];
      
      StringTokenizer st1 = new StringTokenizer(f.readLine());
      array[0] = Integer.parseInt(st1.nextToken());
      //prefix[0] = 0;
      for(int k = 1; k < n; k++){
         array[k] = Integer.parseInt(st1.nextToken());
         //prefix[k]+=array[k];
      }
      //int discarded = 0;
      int cur = 0;
      Boot curboot = null;
      for(int k = 0; k < b; k++){
         StringTokenizer st2 = new StringTokenizer(f.readLine());
         curboot = new Boot(Integer.parseInt(st2.nextToken()),Integer.parseInt(st2.nextToken()));
         if(cur>1 && curboot.maxd < array[cur-1]){
            System.out.println("ey" + curboot.maxd + " " + array[cur-1]);
            discarded++;
         } 
         else {
            cur = canstep(array,cur,curboot);
            System.out.println("I: " +cur);
            if(cur >= array.length){
               System.out.println(" " + discarded);
               out.println(discarded);
               out.close();
               System.exit(0);
            } 
            /*else {
               cur = i;
            }*/
         }
      }
      
      System.out.println(discarded);
      out.println(discarded);
      out.close();
   }
         
      
      
      
      
   
   
   static class Boot{
      int maxd,maxs;
      public Boot(int a, int b){
         maxd = a;
         maxs = b;
      }
   }
   public static int canstep(int[] array, int i, Boot b){
      System.out.println(" ");
      
      if(i + b.maxs >= array.length)
         return array.length;
      for(int k = i+b.maxs; k > i; k--){
         System.out.println(array[k] + " " + b.maxd);
         if(array[k] <= b.maxd){
            return k-i+canstep(array,k,b);
         }
      }
      System.out.println("d");
      discarded++;
      return i;
   }
}
