#include <bits/stdc++.h>

using namespace std;

//hashing
long long BASE = 31LL;
long long MOD = 998244353LL;
vector<long long> p;

//aho-corasick
struct Trie{
   Trie* children[26];
   //vector<int> i;          //indeces of pattern (empty means it isn't the end of a pattern)
   int i;
   bool reached;
   Trie* parent;
   int pchi;
   
   Trie* suffix_link;
   
   Trie* jump[26];         //what happens when you add this character
   
   Trie* exit_link;        //longest suffix that is also a pattern
   
   Trie(){
      for(int k = 0; k < 26; k++){
         children[k] = nullptr;
         jump[k] = nullptr;
      }
      i = -1;
      reached = false;
      
      parent = nullptr;
      pchi = -1;
      
      suffix_link = nullptr;
      exit_link = nullptr;
   }
   
};

vector<bool> answer;

Trie* get_suffix_link(Trie* trie);

Trie* add_ch(Trie* trie, int chi){
   //cout << "in add ch " << trie->pchi << " " << chi << endl;
   //for(int i : trie->i){
      //cout << i << endl;
   //}
   
   if(trie->jump[chi] == nullptr){
      if(trie->children[chi] != nullptr){
         trie->jump[chi] = trie->children[chi];
      } else {
         if(trie->pchi == -1){
            //at the head
            trie->jump[chi] = trie;
         } else {
            trie->jump[chi] = add_ch(get_suffix_link(trie), chi);
         }
      }  
   }
   return trie->jump[chi];
}

Trie* get_suffix_link(Trie* trie){
   //cout << "in suffix link " << trie->pchi << endl;
   if(trie->suffix_link == nullptr){
      trie->suffix_link = add_ch(get_suffix_link(trie->parent), trie->pchi);
   }
   //cout << "added suffix link " << trie->suffix_link->pchi << endl;
   return trie->suffix_link;
}

void dfs_suffix_link(Trie* trie){
   if(trie->suffix_link == nullptr){
      trie->suffix_link = get_suffix_link(trie);
   }
   
   for(int k = 0; k < 26; k++){
      if(trie->children[k] != nullptr){
         //cout << k << endl;
         dfs_suffix_link(trie->children[k]);
         //cout << "back" << endl;
      }
   }
}


int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
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
   for(int k = 0; k < q; k++){
      cin >> patterns[k];
   }
   
   //create trie
   Trie* head = new Trie();
   
   unordered_map<long long,int> hmap;
   hmap.reserve(1024);
   hmap.max_load_factor(0.25);
   vector<int> rep(q);
   
   for(int k = 0; k < q; k++){
      long long hash = 0LL;
      Trie* cur = head;
      for(int j = 0; j < patterns[k].size(); j++){
         int chi = patterns[k][j]-'a';
         hash = (hash + (long long)chi * p[j] + MOD)%MOD;
         if(cur->children[chi] == nullptr){
            cur->children[chi] = new Trie();
            cur->children[chi]->parent = cur;
            cur->children[chi]->pchi = chi;
         }
            
         cur = cur -> children[chi];
      }
      //cur->i.push_back(k);
      if(hmap.find(hash) == hmap.end()){
         hmap[hash] = k;
         cur->i = k;
         rep[k] = k;
      } else {
         rep[k] = hmap[hash];
      }
   }
   //create suffix links
   head->suffix_link = head;
   for(int k = 0; k < 26; k++){
      if(head->children[k] != nullptr){
         head->children[k]->suffix_link = head;
      }
   }
   
   //cout << "starting dfs" << endl;
   
   //dfs construction
   //no need to precompute
   //dfs_suffix_link(head);
   
   //cout << "finishing dfs" << endl;
   
   //bfs construction
   queue<Trie*> qu;
   qu.push(head);
   
   while(!qu.empty()){
      Trie* cur = qu.front();
      qu.pop();
      
      get_suffix_link(cur);
      //if(cur->suffix_link->i.size() > 0){
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
   
   answer = vector<bool>(q,false);
   
   Trie* cur = head;
   
   for(int k = 0; k < n; k++){
      cur = add_ch(cur, s[k]-'a');
      
      Trie* exit = cur->i == -1 ? cur->exit_link : cur;
      while(exit != nullptr){
         answer[exit->i] = true;
         exit = exit->exit_link;
      }
   }
   
   for(int k = 0; k < q; k++){
      if(answer[rep[k]]){
         cout << "YES\n";
      } else {
         cout << "NO\n";
      }
   }
   
   
   return 0;
}