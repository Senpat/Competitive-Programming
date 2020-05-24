import java.util.Scanner;

public class RecalledHardware {

	static Scanner s = new Scanner(System.in);
    
	public static void main(String[] args) {
		String serialNumber = s.next();      // An 11 character serial number.
		
		// code to solve the problem.  You can write and call other methods as well.
      
      System.out.println(check(serialNumber));
      
      
      
	}
   
   public static boolean check(String s){
      String uid = convert(s.substring(s.length()-4));
      //System.out.println(uid);
      if(palin(uid)) return false;
      
      String state = s.substring(0,2);
      int factory = Integer.parseInt(s.substring(2,3));
      int month = Integer.parseInt(s.substring(3,5));
      int year = Integer.parseInt(s.substring(5,7));
      
      
      if(s.substring(0,3).equals("PA6")) return true;
      
      if(state.equals("NJ") && ((year == 15 && month >= 11) || (year == 16 && month <= 2))) return true;
      
      if((state.equals("NY") || state.equals("AK")) && month == 10 && year == 16){
         if(Integer.parseInt(uid) % 9 == 0 && Integer.parseInt(uid) % 27 != 0) return false;
         return true;
      }
      
      
      
      return false;
      
      
   }
   
   public static String convert(String s){
      String a = "";
      
      for(int k = 0; k < s.length(); k++){
         if(s.charAt(k) >= '0' && s.charAt(k) <= '9'){
            a+=s.charAt(k);
         } else {
            a+= "" + ((int)s.charAt(k));
         }
      }
      
      return a;
   }
   
   public static boolean palin(String a){
      
      
      for(int k = 0; k < a.length()/2; k++){
         if(a.charAt(k) != a.charAt(a.length()-1-k)) return false;
      }
      return true;
   }
      
   
   
}
