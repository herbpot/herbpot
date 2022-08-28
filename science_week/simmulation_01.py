# import simpy
import random

while True :
    s1 = random.randint(0,10)#'파원1'
    s2 = random.randint(0,10)#'파원2'
    p = random.randint(0,10)#'특정 점'

    ww = lambda a : a if a > 0 else -a

    ccla = ww(s1*p + s2*p)%2

    if ccla == 0 :
        print('보강간섭')
    elif ccla == 1 :
        print('상쇄간섭')
    print(ccla)
