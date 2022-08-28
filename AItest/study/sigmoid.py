import numpy as np

def sigmoid(x:int) :
    return 1/(1 +  np.e ** (-x))

print(sigmoid(1))