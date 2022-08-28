import os
import sys


p = print
# def cal(x):
#     if( x%2 == 1 ):
#         x = 2*x
#     elif(x%2 == 0):
#         x = (x-1)/3
#     p(x)
#     # cal(x)

# cal(1)
# x = 1
# note = {}
# while x != 100 :
#     y = x
#     note[x] = []
#     while y != 1:
#         if( y%2 == 1 ):
#             y = 3*y + 1
#             note[x].append('e')
#         elif(y%2 == 0):
#             y = y/2
#             note[x].append('o')
#         p(y)
#     x += 1

# p('output : \n')
# string = ''
# for i in note:
#     o = 0
#     e = 0
#     for j in note[i]:
#         if j == 'o':
#             o += 1
#         else :
#             e += 1
#     if( o == e):
#         string += f'{i} | {o} | {o+e} \n'

# with open('./col.txt','x') as F:
#     F.write(string)

p = print
# def cal(x):
#     if( x%2 == 1 ):
#         x = 2*x
#     elif(x%2 == 0):
#         x = (x-1)/3
#     p(x)
#     # cal(x)

# cal(1)
x = 1
note = {}
while x != 10000 :
    y = x
    note[x] = []
    while y != 1:
        if( y%2 == 1 ):
            y = 3*y + 1
            note[x].append([y,'Odd_'])
        elif(y%2 == 0):
            y = y/2
            note[x].append([y,'Even'])
        # p(y)
    x += 1

string = ''
for i in note:
    o = 0
    e = 0
    string += f'{i} | '
    for j in note[i]:
        if j[0] == 'Odd_':
            o += 1
        else :
            e += 1
        string += f' {j[0]}'
    string += '\n'
    # string += f'{i} |짝수 : {o} | 홀수 : {e} | ' + str(note[i].__len__()) + '\n'
    # string += f'{note[i]} | {i} |짝수 : {e} | 홀수 : {o} | ' + str(note[i].__len__()) + '\n'

if os.path.isfile('./col.txt'):
    os.remove('./col.txt')

with open('./col.txt','x') as F:
    F.write(string)

from sklearn.preprocessing import PolynomialFeatures
from sklearn.linear_model import LinearRegression

poly = PolynomialFeatures(degree=5, include_bias=False)


print('모델 훈련중')
model = LinearRegression()
y = [[j[0] for j in note[i]] for i in note.keys()]
# p(y)
model.fit([[i+1] for i in range(9000)],y[:9000])
print("정확도 : ",model.score([i+1 for i in range(9000,10000)],y[9000:]))
