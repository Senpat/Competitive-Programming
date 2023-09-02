#include <bits/stdc++.h>

using namespace std;

//hashing
long long BASE = 31LL;
long long MOD = 998244353LL;
vector<long long> p;

//aho-corasick
struct Trie{
   Trie* children[26];
   int i;
   Trie* parent;
   int pchi;
   
   int reached;
   
   Trie* suffix_link;
   Trie* exit_link;           //longest suffix which is a pattern
   Trie* jump[26];
   
   Trie() : i(-1), parent(nullptr), pchi(-1), reached(0), suffix_link(nullptr), exit_link(nullptr) {
      for(int k = 0; k < 26; k++){
         children[k] = nullptr;
         jump[k] = nullptr;
      }
   }
};

Trie* get_suffix_link(Trie*);

Trie* add_ch(Trie* trie, int chi){
   if(trie->jump[chi] == nullptr){
      if(trie->children[chi] != nullptr){
         trie->jump[chi] = trie->children[chi];
      } else {
         if(trie->pchi == -1){
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
   
   //todo: do in compile time
   int N = 100005;
   p = vector<long long>(N);
   p[0] = 1LL;
   for(int k = 1; k < N; k++){
      p[k] = (p[k-1] * BASE + MOD)%MOD;
   }
   
   string s;
   cin >> s;
   int n = s.length();
   
   int q;
   cin >> q;
   vector<string> patterns(q);
   for(int t = 0; t < q; t++){
      cin >> patterns[t];
   }
   
   Trie* head = new Trie();
   
   unordered_map<long long,int> hmap;
   hmap.reserve(1024);
   hmap.max_load_factor(0.25);
   vector<int> rep(q);
   
   for(int t = 0; t < q; t++){
      Trie* cur = head;
      long long hash = 0LL;
      for(int k = 0; k < patterns[t].size(); k++){
         int chi = patterns[t][k] - 'a';
         hash = (hash + (long long)(chi+1) * p[k] + MOD)%MOD;
         
         if(cur->children[chi] == nullptr){
            cur->children[chi] = new Trie();
            cur->children[chi]->parent = cur;
            cur->children[chi]->pchi = chi;
         }
         
         cur = cur->children[chi];
      }
      if(hmap.find(hash) == hmap.end()){
         hmap[hash] = t;
         cur->i = t;
         rep[t] = t;
      } else {
         rep[t] = hmap[hash];
      }
   }
   //initial suffix links
   head->suffix_link = head;
   for(int k = 0; k < 26; k++){
      if(head->children[k] != nullptr){
         head->children[k]->suffix_link = head;
      }
   }
   //bfs construction
   vector<Trie*> bfsorder;       //store order for later
   queue<Trie*> qu;
   qu.push(head);
   
   while(!qu.empty()){
      Trie* cur = qu.front();
      qu.pop();
      bfsorder.push_back(cur);
      
      get_suffix_link(cur);
      
      //update exit links
      if(cur->suffix_link->i != -1){
         cur->exit_link = cur->suffix_link;
      } else {
         cur->exit_link = cur->suffix_link->exit_link;
      }
      
      for(int k = 0; k < 26; k++){
         if(cur->children[k] != nullptr){
            qu.push(cur->children[k]);
         }
      }
   }
   vector<int> answer(q,0);
   
   Trie* cur = head;
   for(int k = 0; k < n; k++){
      int chi = s[k]-'a';
      cur = add_ch(cur,chi);
      
      cur->reached++;
   }
   
   for(int k = bfsorder.size()-1; k >= 0; k--){
      bfsorder[k]->suffix_link->reached += bfsorder[k]->reached;
      if(bfsorder[k]->i != -1){
         answer[bfsorder[k]->i] = bfsorder[k]->reached;
      }
   }
   
   for(int t = 0; t < q; t++){
      cout << answer[rep[t]] << endl;
   }
   
   return 0;
}