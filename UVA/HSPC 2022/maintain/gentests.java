//make sure to make new file!
import java.io.*;
import java.util.*;
//ADD SAMPLES MANUALLY
public class gentests{
   
   public static void main(String[] args)throws IOException{
      //BufferedReader f = new BufferedReader(new FileReader("t.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("maintain.judge.in")));
      
      int Q = 199992;
      
      out.println(Q);
      
      ArrayList<Integer> alist = new ArrayList<Integer>();
      int q = 0;
      int size = -1;
      int i = 0;
      while(q < Q){
         if(q == 0){
            size = 50000;
         } else if(size == -1){
            size = (int)(Math.random()*10000)+3;
            i = 0;
         }
         
         if(size == i){
            int index = (int)(Math.random()*alist.size());
            out.println("- " + alist.get(index));
            alist.remove(index);
            if(alist.size() == 0){
               size = -1;
            }
              
         } else {
            if(Math.random() < 0.3 && alist.size() > 0){
               int index = (int)(Math.random()*alist.size());
               out.println("- " + alist.get(index));
               alist.remove(index);
            } else {
               int A = (int)(Math.random()*1000000000)+1;
               alist.add(A);
               out.println("+ " + A);
            }
            i++;
         }
         
         q++;
      }
      
      
      
      
      
      
      out.close();
   }
   
      
}