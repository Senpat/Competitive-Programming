//make sure to make new file!
import java.io.*;
import java.util.*;

public class A715{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      int t = Integer.parseInt(f.readLine());
      
      for(int q = 1; q <= t; q++){
      
         int n = Integer.parseInt(f.readLine());
      
         char[] ca = f.readLine().toCharArray();
         char[] cb = f.readLine().toCharArray();
         char[] cc = f.readLine().toCharArray();
         
         int[][] strings = new int[3][2*n];
         
         int[] num0s = new int[3];
         for(int k = 0; k < 2*n; k++){
            strings[0][k] = Character.getNumericValue(ca[k]);
            strings[1][k] = Character.getNumericValue(cb[k]);
            strings[2][k] = Character.getNumericValue(cc[k]);
            
            if(strings[0][k] == 0) num0s[0]++;
            if(strings[1][k] == 0) num0s[1]++;
            if(strings[2][k] == 0) num0s[2]++;
         }
         
         ArrayList<Integer> answer = new ArrayList<Integer>();
         
            //try 0s
         int min = -1;                    //2nd most 0s
         int max = 0;                     //most 0s;
         for(int k = 0; k < 3; k++){
            if(max == -1 || num0s[k] > num0s[max]) max = k;
         }
         for(int k = 0; k < 3; k++){
            if(k == max) 
               continue;
            if(min == -1 || num0s[k] > num0s[min]) min = k;
         }
            
         if(num0s[min] >= n){
            
            
            //move 0s from min to max
            int mini = 0;
            int maxi = 0;
            
            while(mini < 2*n || maxi < 2*n){
               if(mini >= 2*n){
                  answer.add(strings[max][maxi]);
                  maxi++;
               } else if(maxi >= 2*n){
                  answer.add(strings[min][mini]);
                  mini++;
               } else {
                  
                  if(strings[max][maxi] == strings[min][mini]){
                     answer.add(strings[max][maxi]);
                     mini++;
                     maxi++;
                  } else {
                     answer.add(1);
                     if(strings[max][maxi] == 1) maxi++;
                     else mini++;
                  }
               }
            }
         } else {
            min = -1;                    //2nd most 0s
            max = 0;                     //most 0s;
            for(int k = 0; k < 3; k++){
               if(max == -1 || 2*n-num0s[k] > 2*n-num0s[max]) max = k;
            }
            for(int k = 0; k < 3; k++){
               if(k == max) 
                  continue;
               if(min == -1 || 2*n-num0s[k] > 2*n-num0s[min]) min = k;
            }
            
            //move 0s from min to max
            int mini = 0;
            int maxi = 0;
            
            while(mini < 2*n || maxi < 2*n){
               if(mini >= 2*n){
                  answer.add(strings[max][maxi]);
                  maxi++;
               } else if(maxi >= 2*n){
                  answer.add(strings[min][mini]);
                  mini++;
               } else {
                  
                  if(strings[max][maxi] == strings[min][mini]){
                     answer.add(strings[max][maxi]);
                     mini++;
                     maxi++;
                  } else {
                     answer.add(0);
                     if(strings[max][maxi] == 0) maxi++;
                     else mini++;
                  }
               }
            }
         }
                        
                  
         StringJoiner sj = new StringJoiner("");
         for(int i : answer){
            sj.add("" + i);
         }
         out.println(sj.toString());
            
            
      
      }
      
      
      
      
      out.close();
   }
   
      
}