#include <bits/stdc++.h>
//solve using trie using of hashing

using namespace std;

class Trie{
   public:
      Trie* children[26];
      int freq;
};

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
      
   int n;
   cin >> n;
   vector<string> array(n);
   for(int k = 0; k < n; k++){
      cin >> array[k];
   }
   
   //sort by length
   
   sort(array.begin(),array.end(),[]
      (const string& left, const string& right){
         if(left.length() == right.length()){
            return left > right;
         } else {
            return left.length() > right.length();
         }
      });
   
   Trie* head = new Trie();
   
   int answer = 0;
   for(int k = 0; k < n; k++){
      Trie* curtrie = head;
      //check if you should add it to the trie
      bool fail = true;
      for(int j = array[k].length()-1; j >= 0; j--){
         int ci = array[k][j]-'a';
         if(curtrie->children[ci] == NULL){
            fail = false;
            break;
         }
         curtrie = curtrie->children[ci];
      }
      
      if(fail) continue;
      
      curtrie = head;
      int curdepth = 0;
      for(int j = array[k].length()-1; j >= 0; j--){
         int ci = array[k][j]-'a';
         if(curtrie->children[ci] == NULL){
            curtrie->children[ci] = new Trie();
         } else {
            curdepth++;
         }
         
         curtrie = curtrie->children[ci];
      }
      
      answer = max(answer,curdepth);
   }
   
   cout << answer << endl;
      
   
   return 0;
}