// Problem: https://open.kattis.com/problems/queens

#include <iostream>
using namespace std;

int main() {
    
    int n;
    cin >> n;
    int coordinates[n * 2];
    
    for (int i = 0; i < n * 2; i++) {
        int x, y;
        cin >> x >> y;
        coordinates[i] = x;
        coordinates[++i] = y;
        
        for (int j = i - 2; j >= 0; j--) {
            int a = abs(coordinates[i] - coordinates[j]);
            int b = abs(coordinates[i - 1] - coordinates[--j]);
            
            if (a == b || a == 0 || b == 0) {
                cout << "INCORRECT";
                return 0;
            }
        }
    }
    
    cout << "CORRECT";
    return 0;
}
