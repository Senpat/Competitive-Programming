#include <bits/stdc++.h>

using namespace std;

//Suffix Automaton
struct State{
   int len;
   State* suffix_link;
   State* transitions[26];
   long long subsize;
   
   State(int l, State* slink) : len(l), suffix_link(slink), subsize(0LL) {
      for(int k = 0; k < 26; k++){
         transitions[k] = nullptr;
      }
   }
   
   State() : State(0,nullptr){}
};

//Adds the character c to the Suffix Automaton, given than the last State is last
//Returns the new last of the Suffix Automaton
State* extend_sa(State* last, char c){
   int ci = c-'a';
   State* cur = new State(last->len+1,nullptr);
   
   State* state = last;
   while(state->len > 0 && state->transitions[ci] == nullptr){
      state->transitions[ci] = cur;
      state = state->suffix_link;
      //cout << "1: " << state->len << endl;
   }
   
   if(state->transitions[ci] == nullptr){
      //state->len must be 0, so state is the head
      cur->suffix_link = state;
      state->transitions[ci] = cur;
   } else {
      //2 cases
      State* p = state;
      State* q = state->transitions[ci];
      if(p->len+1 == q->len){
         cur->suffix_link = q;
      } else {
         //cout << "in clone" << endl;
         //clone
         State* clone = new State(p->len+1,q->suffix_link);
         for(int k = 0; k < 26; k++){
            clone->transitions[k] = q->transitions[k];
         }
         //cout << "done cloning" << endl;
         q->suffix_link = clone;
         cur->suffix_link = clone;
         while(state != nullptr && state->transitions[ci] == q){
            //cout << "2: " << state->len << endl;
            state->transitions[ci] = clone;
            state = state->suffix_link;
         }
         //cout << "done cloning case" << endl;
      }
   }
   
   return cur;
}

void dfs(State* v){
   //cout << "dfs: " << v->len << endl;
   for(int k = 0; k < 26; k++){
      if(v->transitions[k] == nullptr) continue;
      if(v->transitions[k]->subsize == 0LL) dfs(v->transitions[k]);
      v->subsize += v->transitions[k]->subsize;
   }
   v->subsize++;
   //cout << v->len << " " << v->subsize << endl;
}

string answer;
void getkth(State* state, long long x){
   if(x == 0LL) return;
   
   long long cur = 0LL;
   for(int k = 0; k < 26; k++){
      if(state->transitions[k] == nullptr) continue;
      
      if(cur+state->transitions[k]->subsize >= x){
         answer += 'a'+k;
         getkth(state->transitions[k],x-cur-1);
         break;
      }
      
      cur += state->transitions[k]->subsize;
   }
}


int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   string s;
   long long m;
   cin >> s >> m;
   int n = s.length();
   
   State* head = new State();
   State* last = head;
   
   for(int k = 0; k < n; k++){
      last = extend_sa(last,s[k]);
   }
   
   dfs(head);
   
   answer = "";
   getkth(head,m);
   
   cout << answer << endl;
   
   return 0;
}