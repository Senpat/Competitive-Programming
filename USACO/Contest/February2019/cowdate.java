/*
TASK: cowdate
LANG: JAVA
*/
import java.io.*;
import java.util.*;

public class cowdate{

   public static double TEN6 = 1000000.0;
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new FileReader("cowdate.in"));
      PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cowdate.out")));
      
      int n = Integer.parseInt(f.readLine());
      
      int[] array = new int[n];
      double[] prob = new double[n];
      double[] iprob = new double[n];
      
      double max = 0.0;
      
      for(int k = 0; k < n; k++){
         array[k] = Integer.parseInt(f.readLine());
         prob[k] = array[k]/TEN6;
         iprob[k] = (1000000-array[k])/TEN6;
         
         max = Math.max(max,prob[k]);
      }
      
      
      
      
      
      double cursum = prob[0];
      double curprod = iprob[0];
      max = Math.max(max,cursum);
      
      int l = 0;
      int r = 0;
      
      double prevsum = cursum;
      boolean last = false;                                 //false is expand, true is contract
      
      while(r < n-1 || l < n-1){
         //calculate new cursum and curprod
         
         if(r == n-1){
            //always contract
            prevsum = cursum;
                  
            cursum = (cursum-(curprod*prob[l]/iprob[l]))/iprob[l];
            curprod = curprod/iprob[l];
                  
            last = true;
            max = Math.max(max,cursum);
                  
            l++;

         
         } else if(l==r){
            //always expand, calculate new cursum and curprod
            prevsum = cursum;
            r++;
            cursum = cursum*iprob[r] + curprod*prob[r];
            curprod *= iprob[r];
            max = Math.max(max,cursum);
            last = false;
            
            
         } else {
            if(last){
               //last was contract. if new cursum bigger, constract, else expand
               if(cursum >= prevsum){
                  //contract
                  prevsum = cursum;
                  
                  cursum = (cursum-(curprod*prob[l]/iprob[l]))/iprob[l];
                  curprod = curprod/iprob[l];
                  
                  last = true;
                  max = Math.max(max,cursum);
                  
                  l++;
               } else {
                  //expand
                  prevsum = cursum;
                  r++;
                  cursum = cursum*iprob[r] + curprod*prob[r];
                  curprod *= iprob[r];
                  max = Math.max(max,cursum);
                  last = false;
               }
            
            } else {
               //last was expand. if new curssum bigger, expand, else contract
               if(cursum >= prevsum){
                  prevsum = cursum;
                  r++;
                  cursum = cursum*iprob[r] + curprod*prob[r];
                  curprod *= iprob[r];
                  max = Math.max(max,cursum);
                  last = false;
               
               
               } else {
                  prevsum = cursum;
                  
                  cursum = (cursum-(curprod*prob[l]/iprob[l]))/iprob[l];
                  curprod = curprod/iprob[l];
                  
                  last = true;
                  max = Math.max(max,cursum);
                  
                  l++;
               
               
               }
               
               
               
            }
         
         }
      }
      
      
      
      long answer = (int)(max*1000000.0+0.11);
      System.out.println(max);
      System.out.println(answer);
      out.println(answer);

      
      
      
      
      out.close();
   }
   
      
}