#include <bits/stdc++.h>

using namespace std;

struct Trie{
   Trie* children[2];
};

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int n;
   cin >> n;
   int P = 30;
   
   vector<int> array(n);
   for(int k = 0; k < n; k++){
      cin >> array[k];
   }
   
   vector<int> pxor(n+1,0);
   for(int k = 0; k < n; k++){
      pxor[k+1] = pxor[k] ^ array[k];
   }
   
   Trie* head = new Trie();
   
   for(int k = 0; k <= n; k++){
      Trie* cur = head;
      for(int p = P; p >= 0; p--){
         int i = (pxor[k] >> p)&1;
         if(cur->children[i] == nullptr){
            cur->children[i] = new Trie();
         }
         cur = cur->children[i];
      }
   }
   
   int answer = 0;
   for(int k = 1; k <= n; k++){
      int curanswer = 0;
      Trie* cur = head;
      for(int p = P; p >= 0; p--){
         int i = (pxor[k] >> p)&1;
         if(cur->children[1-i] != nullptr){
            curanswer ^= (1 << p);
            cur = cur->children[1-i];
         } else {
            cur = cur->children[i];
         }
      }
      
      answer = max(answer,curanswer);
   }
   
   cout << answer << "\n";
   
   
      
   
   
   return 0;
}