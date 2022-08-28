import keyboard
import time

obj = keyboard
i = 0
while i<=1:
    a = obj.record(until='`')
    i = len(a)
print(a)
with open('pad.txt','w') as f :
    for l in a :
        f.write(str(l)+'\n')