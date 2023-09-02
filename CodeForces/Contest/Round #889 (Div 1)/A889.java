//make sure to make new file!
import java.io.*;
import java.util.*;

public class A889{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         StringTokenizer st = new StringTokenizer(f.readLine());
         int[] array = new int[n];
         int maxabsi = 0;
         for(int k = 0; k < n; k++){
            array[k] = Integer.parseInt(st.nextToken());
            if(Math.abs(array[k]) > Math.abs(array[maxabsi])){
               maxabsi = k;
            }
         }
         
         //add maxabsi to everything else
         ArrayList<Op> answer = new ArrayList<Op>();
         for(int k = 0; k < n; k++){
            if(k != maxabsi){
               answer.add(new Op(k,maxabsi));
            }
         }
         
         if(array[maxabsi] >= 0){
            for(int k = 0; k < n-1; k++){
               answer.add(new Op(k+1,k));
            }
         } else {
            for(int k = n-1; k >= 1; k--){
               answer.add(new Op(k-1,k));
            }
         }
         
         StringJoiner sj = new StringJoiner("\n");
         sj.add(""+answer.size());
         for(Op op : answer){
            sj.add(op.toString());
         }
         out.println(sj.toString());
      
      

      }
      
      
      
      
      out.close();
   }
   
   public static class Op{
      int x;
      int y;
      public Op(int a, int b){
         x = a+1;
         y = b+1;
      }
      public String toString(){
         return x + " " + y;
      }
   }
      
}