# Problem: https://open.kattis.com/problems/guessinggame

highest_low = 0
lowest_high = 11

while True:
    num = int(input())
    if num == 0:
        break
    status = input()

    if (status == 'too low') & (num > highest_low):
        highest_low = num

    elif (status == 'too high') & (num < lowest_high):
        lowest_high = num

    elif status == 'right on':
        print('Stan may be honest') if (num > highest_low) & (num < lowest_high) else print('Stan is dishonest')
        
        highest_low = 0
        lowest_high = 11
