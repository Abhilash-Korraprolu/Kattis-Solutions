// Problem: https://open.kattis.com/problems/averagespeed

#include <iostream>
#include <string>
#include <iomanip>
using namespace std;

int main() {
    
    cout << fixed << setprecision(2);
    double speed = 0.0, dist = 0.0, secs1 = 0.0;
    string s;
    
    while (getline(cin, s)) {
        double secs2 = (stod(s.substr(0, 2)) * 3600) + (stod(s.substr(3, 2)) * 60) + stod(s.substr(6, 2));
        dist += speed * ((secs2 - secs1) / 60.0 / 60.0);
        secs1 = secs2;
        
        if (s.length() > 8)
            speed = stod(s.substr(9));
        else
            cout << s + " " << dist << " km\n";
    }
    return 0;
}
