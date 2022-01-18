//make sure to make new file!
import java.io.*;
import java.util.*;

public class CDS2021{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         int[] array = new int[n];
         for(int k = 0; k < n; k++){
            array[k] = Integer.parseInt(f.readLine());
         }
         
         StringJoiner sj = new StringJoiner("\n");
         Stack<Entry> stack = new Stack<Entry>();
         
         //first element should be 1
         sj.add("1");
         stack.push(new Entry("1",1));
         
         for(int k = 1; k < n; k++){
            if(array[k] == 1){
               //add new depth
               String s = stack.peek().s + ".1";
               sj.add(s);
               stack.push(new Entry(s,1));
            } else {
               while(!stack.isEmpty() && stack.peek().last != array[k]-1){
                  stack.pop();
               }
               
               if(stack.isEmpty()) break;             //shouldn't happen
               
               String s = "";
               int index = stack.peek().s.lastIndexOf(".");
               if(index == -1) s = "" + array[k];
               else s = stack.peek().s.substring(0,index+1) + array[k];
               sj.add(s);
               stack.pop();
               stack.push(new Entry(s,array[k]));
            }
         }
         
         out.println(sj.toString());
      }
      
      
      
      
      out.close();
   }
   
   public static class Entry{
      String s;
      int last;
      public Entry(String a, int b){
         s = a;
         last = b;
      }
   }
      
}