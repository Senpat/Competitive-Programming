#include <bits/stdc++.h>

using namespace std;

string S = "ThoreHusfeldt";

struct Trie{
   char ch;
   Trie* children[52];
};

int getci(char ch){
   if(ch >= 'a') return ch-'a';
   return ch-'A'+26;
}

Trie* createTrie(){
   Trie* ret = new Trie();
   ret->ch = '@';
   for(int k = 0; k < 52; k++){
      ret->children[k] = NULL;
   }
   return ret;
}

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n;
   cin >> n;
   
   vector<string> array(n);
   int i = -1;
   for(int k = 0; k < n; k++){
      cin >> array[k];
      if(array[k] == S){
         i = k;
      }
         
   }
   
   if(i == 0){
      cout << "Thore is awesome\n";
      return 0;
   }
   
   Trie* head = createTrie();
   
   for(int k = 0; k < i; k++){
      Trie* curtrie = head;
      for(int j = 0; j < array[k].length(); j++){
      
         int ci = getci(array[k][j]);
         
         if(curtrie->children[ci] == NULL){
            Trie* temp = createTrie();
            temp->ch = array[k][j];
            curtrie->children[ci] = temp;
         }
         
         curtrie = curtrie->children[ci];
      }
   }
   
   int answer = 1;
   Trie* curtrie = head; 
   for(int k = 0; k < S.length(); k++){
      int ci = getci(S[k]);
      if(curtrie->children[ci] == NULL){
         break;
      }
      
      answer++;
      curtrie = curtrie->children[ci];
   }
   
   if(answer > 12){
      cout << "Thore sucks\n";
   } else {
      cout << S.substr(0,answer) << "\n";
   }
   
   
   
   
   
   return 0;
}