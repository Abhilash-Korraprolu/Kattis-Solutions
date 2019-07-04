#include <iostream>
#include <cmath>
#include <iomanip>
using namespace std;

int main() {
    
    double noOfSegments_Xs, noOfArcs_Ys, radius, ax, ay, bx, by;
    cin >> noOfSegments_Xs >> noOfArcs_Ys >> radius >> ax >> ay >> bx >> by;
    
    double PI = 3.141592653589;
    double segmentBitLength = radius / noOfArcs_Ys;
    double angle = 180.0 / noOfSegments_Xs;
    double arcRadius = (ay < by ? ay : by) * segmentBitLength;
    double arcBitLength = (angle / 360.0) * 2.0 * PI * arcRadius;
    
    double d1 = (fabs(ax - bx) * arcBitLength) + (fabs(ay - by) * segmentBitLength);
    double d2 = (ay + by) * segmentBitLength;
    cout << fixed << setprecision(7) << (d1 < d2 ? d1 : d2);
    
    return 0;
}
