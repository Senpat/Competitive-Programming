#include <bits/stdc++.h>
//still tle, doesn't account for end
using namespace std;

class Trie{
   public:
   vector<Trie*> children;
   int freq;
   int all;
   int numdiff;
   Trie() : freq(0), all(0), numdiff(0){
      children = vector<Trie*>(26,nullptr);
   }
};

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int t;
   cin >> t;
   for(int q = 0; q < t; q++){
      int n,m;
      cin >> n >> m;
      
      vector<string> array(n);
      Trie* head = new Trie();
      
      for(int k = 0; k < n; k++){
         cin >> array[k];
         
         Trie* cur = head;
         //cur->freq++;
         for(int j = 0; j < array[k].size(); j++){
            int ci = array[k][j] - 'a';
            cur->all++;
            if(cur->children[ci] == nullptr){
               cur->children[ci] = new Trie();
               cur->numdiff++;
            }
            cur = cur->children[ci];
            
            cur->freq++;
         }
      }
      
      vector<char> answer;
      Trie* cur = head;
      
      while(true){
         //cout << cur->freq << " " << cur->numdiff << endl;
         if(cur->all <= 1) break;
         if(cur->numdiff >= m) break;
         
         int tot = cur->numdiff;
         for(int k = 0; k < 26; k++){
            if(cur->children[k] == nullptr) continue;
            
            if(tot + cur->children[k]->freq-1 >= m){
               char c = (char)(k + 'a');
               answer.push_back(c);
               
               cur = cur->children[k];
               m -= tot-1;
               break;
            }
            
            tot += cur->children[k]->freq - 1;
         }
         
         
      }
      if(answer.size() == 0){
         cout << "EMPTY\n";
      } else {
         for(int k = 0; k < answer.size(); k++){
            cout << answer[k];
         }
         cout << "\n";
      }
      
   }
   
   
   return 0;
}