//make sure to make new file!
import java.io.*;
import java.util.*;

public class B854{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         StringTokenizer st = new StringTokenizer(f.readLine());
      
         
         HashSet<Integer> all = new HashSet<Integer>();
         boolean has1 = false;
         int[] array = new int[n];
         for(int k = 0; k < n; k++){
            array[k] = Integer.parseInt(st.nextToken());
            all.add(array[k]);
            if(array[k] == 1) has1 = true;
         }
         
         if(all.size() == 1){
            out.println(0);
            continue;
         }
         
         if(has1){
            out.println(-1);
            continue;
         }
         
         ArrayList<Op> answer = new ArrayList<Op>();
         while(true){
            //get maxi and mini
            
            int maxi = 0;
            int mini = 0;
            
            for(int k = 1; k < n; k++){
               if(array[k] > array[maxi]){
                  maxi = k;
               }
               if(array[k] < array[mini]){
                  mini = k;
               }
            }
            
            if(array[maxi] == array[mini]){
               //finished
               break;
            }
            
            array[maxi] = (array[maxi] + array[mini]-1) / array[mini];
            answer.add(new Op(maxi,mini));
            
            
            
         }
         
         StringJoiner sj = new StringJoiner("\n");
         sj.add("" + answer.size());
         for(Op op : answer){
            sj.add(op.toString());
         }
         out.println(sj.toString());

      }
      
      
      
      
      out.close();
   }
   
   public static class Op{
      int a;
      int b;
      public Op(int c, int d){
         a = c+1;
         b = d+1;
      }
      
      public String toString(){
         return a + " " + b;
      }
   }
      
}