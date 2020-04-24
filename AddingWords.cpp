// Problem: https://open.kattis.com/problems/addingwords

#include <iostream>
#include <string>
#include <map>
#include <algorithm>
using namespace std;

void toArray(string command, string cmdArray[]) {
    
    int i = 0;
    while (command != "") {
        auto found = command.find(" ");
        cmdArray[i] = command.substr(0, found);
        command.erase(0, found + 1);
        i++;
    }
}

int main() {
    
    map<string, int> mp;
    string command;
    
    while (getline(cin, command)) {
        command += " ";
        auto size = count(command.begin(), command.end(), ' ');
        string cmdArray[size];
        toArray(command, cmdArray);
        
        if (cmdArray[0] == "def")
            mp[cmdArray[1]] = stoi(cmdArray[2]);
        
        else if (cmdArray[0] == "clear")
            mp.clear();
        
        else {
            for (int i = 1; i < size; i++)
                cout << cmdArray[i] << " ";
            
            int result = 0;
            for (int i = 0; i < size; i = i + 2) {
                if (mp.find(cmdArray[i + 1]) == mp.end() && i != size - 1) {
                    cout << "unknown" << endl;
                    break;
                }
                if (i == 0)
                    result = mp[cmdArray[1]];
                
                else if (cmdArray[i] == "+")
                    result += mp[cmdArray[i + 1]];
                
                else if (cmdArray[i] == "-")
                    result -= mp[cmdArray[i + 1]];
                
                else if (cmdArray[i] == "=") {
                    bool exists = false;
                    map<string, int>::iterator it = mp.begin();
                    while (it != mp.end()) {
                        if (it -> second == result) {
                            cout << it-> first << endl;
                            exists = true;
                        }
                        it++;
                    }
                    if (!exists)
                        cout << "unknown\n";
                }
            }
        }
    }
    return 0;
}
