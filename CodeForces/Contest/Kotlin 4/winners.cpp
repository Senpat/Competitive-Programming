#include "testlib.h"
#include <iostream>

using namespace std;

int main(int argc, char *argv[]) {
    int seed = 284;
    int len = 1044;
    int nwinners = 50;
    rnd.setSeed(seed);
    
    set<int> winners;
    while (winners.size() < nwinners)
        winners.insert(50 + rnd.next(1, len));
    
    for (auto winner: winners)
        cout << winner << " ";
    cout << endl;
}