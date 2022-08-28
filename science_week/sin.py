import matplotlib.pyplot as plt
import numpy as np

def f(t):
    return np.exp(-t) * np.cos(2*np.pi*t)

def g(t):
    return np.exp(-t) * np.cos(2*np.pi*t)

def p(t):
    return np.exp(-t) * np.cos(2*np.pi*t)
    
def k(a,b):
    c = []
    for i in range(len(a)) :
        try:
            c.append((float(a[i])+float(b[i]))/2)
        except:
            try:
                c.append(a[i])
            except:
                c.append(b[i])
    return c



t1 = np.arange(5.0, 140.0, 0.2)
t2 = np.arange(1.0, 10.0, 0.1)
print(type(f(t2)))
print(f(t2))
print(len(f(t2)))
print(type(g(t1)))
print(g(t1))
print(len(g(t1)))

t3 = k(f(t2),g(t1))
t3 = np.array(t3, dtype=np.float32)
print(t3)
print(len(t3))

plt.figure()
plt.subplot(211)
plt.plot( t2, f(t2), 'b', t1, g(t1), 'ko', t3, 'r')#, t3, 'k'

plt.show()