#include <iostream>
#include <algorithm>
using namespace std;

int main() {
    
    long noOfItems, X;
    cin >> noOfItems >> X;
    
    long itemPrices[noOfItems];
    for (long i = 0; i < noOfItems; i++)
        cin >> itemPrices[i];
    
    sort(itemPrices, itemPrices + noOfItems);
    for (long i = 1; i < noOfItems; i++) {
        if (itemPrices[i] + itemPrices[i - 1] > X) {
            cout << i;
            return 0;
        }
    }
    
    cout << noOfItems;
    return 0;
}
