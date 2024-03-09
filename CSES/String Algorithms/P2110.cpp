#include <bits/stdc++.h>

using namespace std;

class State{
   public:
   int len;
   int suffix_link;
   int transition[26];
   
   State(int l) : len(l), suffix_link(-1){
      for(int k = 0; k < 26; k++){
         transition[k] = -1;
      }
   }
   State() : State(0){}
};

const int MAXN = 200005;
State sa[MAXN];
int curpos;

//extend suffix automaton at last with character ci
//returns new last
int sa_extend(int last, int ci){
   int cur = curpos++;
   sa[cur] = State(sa[last].len+1);
   
   int p = last;
   while(p != -1 && sa[p].transition[ci] == -1){
      sa[p].transition[ci] = cur;
      p = sa[p].suffix_link;
   }
   
   if(p == -1){
      //at head
      sa[cur].suffix_link = 0;
   } else {
      int q = sa[p].transition[ci];
      if(sa[p].len+1 == sa[q].len){
         sa[cur].suffix_link = q;
      } else {
         //clone
         int clone = curpos++;
         sa[clone] = State(sa[p].len+1);
         
         sa[clone].suffix_link = sa[q].suffix_link;
         sa[q].suffix_link = clone;
         for(int k = 0; k < 26; k++){
            sa[clone].transition[k] = sa[q].transition[k];
         }
         
         sa[cur].suffix_link = clone;
         
         //walk back from p, redirect any transitions to q to clone
         while(p != -1 && sa[p].transition[ci] == q){
            sa[p].transition[ci] = clone;
            p = sa[p].suffix_link;
         }
      }
   }
   
   return cur;
}



int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   string s;
   cin >> s;
   int n = s.length();
   
   sa[0] = State(0);
   curpos = 1;
   int last = 0;
   
   for(int k = 0; k < n; k++){
      last = sa_extend(last,s[k]-'a');
   }
   
   vector<int> dist(n+2,0);
   for(int k = 1; k < curpos; k++){
      //for every node, new substring in [len(suffix_link)+1,len]
      dist[sa[sa[k].suffix_link].len+1]++;
      dist[sa[k].len+1]--;
   }
   
   int cur = 0;
   for(int k = 1; k <= n; k++){
      cur += dist[k];
      cout << cur << " ";
   }
      
   
   
   return 0;
}