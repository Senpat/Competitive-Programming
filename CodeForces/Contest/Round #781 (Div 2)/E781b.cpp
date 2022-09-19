#include <bits/stdc++.h>
//help from danny
using namespace std;


struct Query {
   int l, r, idx;
   bool operator<(Query other) const
   {
      return l > other.l;                 //sort by decreasing l value
   }
};

struct Trie{
    Trie* left;
    Trie* right;
    int left1;
    int left2;

};

Trie* trie;
int N = 100005;

Trie* createtrie(){
   Trie* ret = new Trie();
   ret->left = NULL;
   ret->right = NULL;
   ret->left1 = N;
   ret->left2 = N;
   return ret;
}

void add(int x, int index){
   //cout << "add " << x << endl;
   int i = 1 << 30;
   
   Trie* curtrie = trie;
   
   while(i >= 0){
      if(index < curtrie->left1){
         curtrie->left2 = curtrie->left1;
         curtrie->left1 = index;
      } else if(index < curtrie->left2){
         curtrie->left2 = index;
      }
      
      
      if(i == 0) break;
      if((x & i) == 0){
         //put in left 
         //cout << i << " left" << endl;
         if(curtrie->left == NULL){
            curtrie->left = createtrie();
         }
         curtrie = curtrie->left;
      } else {
         //put in right
         //cout << i << " right" << endl;
         if(curtrie->right == NULL){
            curtrie->right = createtrie();
         }
         curtrie = curtrie->right;
      }     
      
      
      i >>= 1;
   }
}

int get_answer(int r){
   int i = 1 << 30;
   int answer = 0;
   
   vector<Trie*> curtries;
   curtries.push_back(trie);
   while(i > 0){
      int num0 = 0;
      for(Trie* tptr : curtries){
         if(tptr->left != NULL){
            if(tptr->left->left2 <= r){
               num0+=2;
            } else if(tptr->left->left1 <= r){
               num0++;
            }
         }
      }
      vector<Trie*> next;
      if(num0 < 2){
         answer |= i;
         for(Trie* tptr : curtries){
            if(tptr->left != NULL && tptr->left->left1 <= r){
               next.push_back(tptr->left);
            }
            if(tptr->right != NULL){
               next.push_back(tptr->right);
            }
         }
      } else {
         //bit is 0
         for(Trie* tptr : curtries){
            if(tptr->left != NULL && tptr->left->left1 <= r){
               next.push_back(tptr->left);
            }
         }
      }
      
      curtries = next;
   
      i >>= 1;
   }
   
   return answer;
   

}


int main(){
   ios::sync_with_stdio(false);
   cin.tie(0);
   
   int t;
   cin >> t;
   for(int qq = 1; qq <= t; qq++){
      int n;
      cin >> n;
      vector<int> array;
      for(int k = 0; k < n; k++){
         int i;
         cin >> i;
         array.push_back(i);
      }
      int q;
      cin >> q;
      vector<Query> queries;
      for(int k = 0; k < q; k++){
         //im bad at c++
         int l,r;
         cin >> l >> r;
         Query query;
         query.l = l-1;
         query.r = r-1;
         query.idx = k;
         queries.push_back(query);
      }
      
      vector<int> answer(q);
      sort(queries.begin(),queries.end());
      
      trie = createtrie();
      int i = n;
      for (Query q : queries) {
         while(i > q.l){
            add(array[i-1],i-1);
            i--;
         }
         answer[q.idx] = get_answer(q.r);
      }
      
      for(int k = 0; k < q; k++){
         cout << answer[k] << endl;
      }
   
   }
   
   
   
   
   return 0;
}