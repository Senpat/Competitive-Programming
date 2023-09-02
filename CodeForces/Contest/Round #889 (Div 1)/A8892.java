//make sure to make new file!
import java.io.*;
import java.util.*;
//solves full subtasks hopefully
public class A8892{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){

         int n = Integer.parseInt(f.readLine());
      
         StringTokenizer st = new StringTokenizer(f.readLine());
         int[] array = new int[n];
         int maxabsi = 0;
         int pos = 0;
         int neg = 0;
         int zero = 0;
         
         int maxposi = 0;
         int maxnegi = 0;
         for(int k = 0; k < n; k++){
            array[k] = Integer.parseInt(st.nextToken());
            if(Math.abs(array[k]) > Math.abs(array[maxabsi])){
               maxabsi = k;
            }
            
            if(array[k] > 0){
               pos++;
               if(array[maxposi] < array[k]) maxposi = k;
            } else if(array[k] < 0){
               neg++;
               if(array[maxnegi] > array[k]) maxnegi = k;
            } else {
               zero++;
            }
               
         }
         
         if(array[maxabsi] == 0){
            out.println(0);
            continue;
         }
         
         ArrayList<Op> answer = new ArrayList<Op>();
         int REP = 5;
         
         if(array[maxabsi] > 0){
            //max abs is positive
            if(neg <= 12){
               for(int k = 0; k < n; k++){
                  if(array[k] < 0){
                     answer.add(new Op(k,maxabsi));
                  }
               }
               dopos(n,answer);
            } else {
               //too many negatives
               for(int k = 0; k < REP; k++){
                  answer.add(new Op(maxnegi,maxnegi));
               }
               
               for(int k = 0; k < n; k++){
                  if(array[k] > 0){
                     answer.add(new Op(k,maxnegi));
                  }
               }
               doneg(n,answer);
            }
         } else {
            //max abs is negative
            if(pos <= 12){
               for(int k = 0; k < n; k++){
                  if(array[k] > 0){
                     answer.add(new Op(k,maxabsi));
                  }
               }
               doneg(n,answer);
            } else {
               //too many positives
               for(int k = 0; k < REP; k++){
                  answer.add(new Op(maxposi,maxposi));
               }
               for(int k = 0; k < n; k++){
                  if(array[k] < 0){
                     answer.add(new Op(k,maxposi));
                  }
               }
               dopos(n,answer);
               
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
   
   public static void dopos(int n,ArrayList<Op> answer){
      for(int k = 0; k < n-1; k++){
         answer.add(new Op(k+1,k));
      }
   }
   
   public static void doneg(int n, ArrayList<Op> answer){
      for(int k = n-1; k >= 1; k--){
         answer.add(new Op(k-1,k));
      }
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