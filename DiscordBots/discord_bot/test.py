# import commands as cmm

# # print(cmm.__dict__)
# for i in cmm.__dict__ :
#     if not i[0]+i[1] == '__' :
#         exec(f'a = cmm.{i}')
#         a()

# import json

# with open('./discord_bot/prefix,token.json','r') as F :
#     jsons = json.load(F)

# print(jsons.values)
# a , b = jsons
# a,b = jsons[a], jsons[b]
# print(a,b)


# def get_sum(start, end) :
#     sum = 0
#     for i in range(start,end+1):
#         sum += i
#     return sum

# a = get_sum(1,213)
# print(a)

# def get_max(x, y, z):
#     a = [x,y,z]
#     a.sort()
#     a.reverse()
#     return a[0],a[1],a[2]
# print(get_max(10, 20,30))

# def get_max(x, y, z):
#     a = {}
#     if x>y :
#         if x>z :
#             a[1] = x
#             if y>z:
#                 a[2] = y
#                 a[3] = z
#         else:
#             a[2] = x
#             a[1] = z
#             a[3] = y
#     else :
#         if y>z :
#             a[1] = y
#             if x>z :
#                 a[2] = x
#                 a[3] = y
#         else:
#             a[1] = z
#             a[2] = y
#             a[3] = x
#     return a
# print(get_max(10, 20,30))
import random

def get_password():
    al = 'qwertyuiopasdfghjklzxcvbnm1234567890'
    ps = ''
    for i in range(6):
        index = random.randint(len(al))
        ps = ps + al[index]

    return ps