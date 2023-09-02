#include <bits/stdc++.h>
//bugs:
//extra cout
//this case: abcd 2 abcd bc (pattern within another pattern)
//updating answer when pattern matches trie node without checking if it has been seen
using namespace std;

struct Trie{
   Trie* children[26];
   int i;
   Trie* parent;
   int pchi;
   
   Trie* jump[26];
   
   Trie* suffix_link;
   Trie* exit_link;
   int len;                   //length of prefix this trie node represents
   
   Trie(): i(-1), parent(nullptr), pchi(-1), suffix_link(nullptr), exit_link(nullptr), len(0){
      for(int k = 0; k < 26; k++){
         children[k] = nullptr;
         jump[k] = nullptr;
      }
   } 
};

Trie* get_suffix_link(Trie* trie);

Trie* add_ch(Trie* trie, int chi){
   if(trie->jump[chi] == nullptr){
      if(trie->children[chi] != nullptr){
         trie->jump[chi] = trie->children[chi];
      } else {
         if(trie->parent == nullptr){
            //head
            trie->jump[chi] = trie;
         } else {
            trie->jump[chi] = add_ch(get_suffix_link(trie),chi);
         }
      }
   }
   return trie->jump[chi];
}

Trie* get_suffix_link(Trie* trie){
   if(trie->suffix_link == nullptr){
      trie->suffix_link = add_ch(get_suffix_link(trie->parent),trie->pchi);
   }
   return trie->suffix_link;
}

int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   string s;
   cin >> s;
   int n = s.length();
   
   Trie* head = new Trie();
   head->len = 0;
   
   int q;
   cin >> q;
   unordered_map<long long,int> hmap;
   hmap.reserve(1024);
   hmap.max_load_factor(0.25);
   
   vector<int> rep(q);
   
   for(int t = 0; t < q; t++){
      string pattern;
      cin >> pattern;
      
      Trie* cur = head;
      for(int k = 0; k < pattern.length(); k++){
         int chi = pattern[k]-'a';
         
         if(cur->children[chi] == nullptr){
            cur->children[chi] = new Trie();
            cur->children[chi]->parent = cur;
            cur->children[chi]->pchi = chi;
            cur->children[chi]->len = cur->len+1;
         }
         
         cur = cur->children[chi];
      }
      
      if(cur->i == -1){
         cur->i = t;
         rep[t] = t;
      } else {
         rep[t] = cur->i;
      }
            
      
   }
   
   //initial
   head->suffix_link = head;
   for(int k = 0; k < 26; k++){
      if(head->children[k] != nullptr){
         head->children[k]->suffix_link = head;
      }
   }
   
   //bfs construction
   queue<Trie*> qu;
   qu.push(head);
   
   while(!qu.empty()){
      Trie* cur = qu.front();
      qu.pop();
      
      get_suffix_link(cur);
      
      if(cur->suffix_link->i != -1){
         cur->exit_link = cur->suffix_link;
         //cout << cur->i << " v1 " << cur->exit_link << endl;
      } else {
         cur->exit_link = cur->suffix_link->exit_link;
         //cout << cur->i << " v2 " << cur->exit_link << endl;
      }
      
      for(int k = 0; k < 26; k++){
         if(cur->children[k] != nullptr){
            qu.push(cur->children[k]);
         }
      }
   }
   vector<int> answer(q,-1);
   
   Trie* cur = head;
   for(int k = 0; k < n; k++){
      int chi = s[k]-'a';
      cur = add_ch(cur,chi);
      if(cur->i != -1 && answer[cur->i] == -1){
         answer[cur->i] = k - cur->len +1 +1;
      }
      Trie* exit = cur;
      while(exit->exit_link != nullptr && answer[exit->exit_link->i] == -1){
         //cout << exit->exit_link->i << endl;
         answer[exit->exit_link->i] = k - exit->exit_link->len + 1 + 1;
         exit = exit->exit_link;
      }
   
   }
   
   for(int k = 0; k < q; k++){
      cout << answer[rep[k]] << endl;
   }
   
   return 0;
}