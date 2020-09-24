//make sure to make new file!
import java.io.*;
import java.util.*;

public class A{
   
   public static void main(String[] args)throws IOException{
      BufferedReader f = new BufferedReader(new InputStreamReader(System.in));
      PrintWriter out = new PrintWriter(System.out);
      
      StringTokenizer st = new StringTokenizer(f.readLine());
      
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());
      
      ArrayList<ArrayList<String>> prefs = new ArrayList<ArrayList<String>>(n);
      for(int k = 0; k < n; k++) prefs.add(new ArrayList<String>());
      
      for(int k = 0; k < n; k++){
         st = new StringTokenizer(f.readLine());
         int i = Integer.parseInt(st.nextToken());
         
         for(int j = 0; j < i; j++){
            prefs.get(k).add(st.nextToken());
         }
      }
      
      //number of players
      int np = Integer.parseInt(f.readLine());
      
      //previous year ranking
      String[] prank = new String[np];
      for(int k = 0; k < np; k++){
         prank[k] = f.readLine();
      }
      
      int[] pointers = new int[n];
      int prankp = 0;
      
      ArrayList<StringJoiner> answer = new ArrayList<StringJoiner>(n);
      for(int k = 0; k < n; k++) answer.add(new StringJoiner(" "));
      
      HashSet<String> taken = new HashSet<String>();
      
      for(int k = 0; k < n*m; k++){
         int team = k%n;
         
         while(pointers[team] < prefs.get(team).size() && taken.contains(prefs.get(team).get(pointers[team]))){
            pointers[team]++;
         }
         
         if(pointers[team] < prefs.get(team).size()){
            taken.add(prefs.get(team).get(pointers[team]));
            answer.get(team).add(prefs.get(team).get(pointers[team]));
         } else {
            while(taken.contains(prank[prankp])){
               prankp++;
            }
            taken.add(prank[prankp]);
            answer.get(team).add(prank[prankp]);
         }
      }
      
      StringJoiner sj = new StringJoiner("\n");
      for(int k = 0; k < n; k++){
         sj.add(answer.get(k).toString());
      }
      out.println(sj);
         
         
         
      
      
      
      
      
      
      
      out.close();
   }
   
      
}