# Problem: https://open.kattis.com/problems/bits

testCases = int(input())
for tc in range(testCases):

    highestNumberOf1s = 0
    fullNumber = input()
    length = len(fullNumber)

    for i in range(1, length + 1):

        currentNumber = int(fullNumber[:i])
        binaryVersion = str(bin(currentNumber))

        ones = binaryVersion.count('1')
        if ones > highestNumberOf1s:
            highestNumberOf1s = ones

    print(highestNumberOf1s)
