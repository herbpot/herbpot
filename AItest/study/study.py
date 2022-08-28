import numpy as np
import matplotlib as mp

epoche = 2000
a = 1
b = 1
r = 0.17

data = [[1,2],[4,3],[6,2]]

def sigmoid(x) :
    return 1/(1+ np.e ** (-x))

for i in range(epoche + 1) :
    for x,y in data :
        x_ = x*sigmoid(a*x+b)
        y_ = sigmoid(a*x+b)
        a -= r*x_
        b -= r*y_