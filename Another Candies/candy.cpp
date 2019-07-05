#include <iostream>
using namespace std;

int main() {
    
    int TC;
    cin >> TC;
    
    while (TC-- > 0) {
        cin.ignore();
        long long noOfChildren, r = 0;
        cin >> noOfChildren;
        
        for (int i = 0; i < noOfChildren; i++) {
            long long noOfcandies;
            cin >> noOfcandies;
            r += noOfcandies % noOfChildren;
        }
        
        cout << (r % noOfChildren == 0 ? "YES" : "NO") << endl;
    }
    
    return 0;
}
