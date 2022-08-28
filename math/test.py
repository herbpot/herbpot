list = []
for i in range(10):
    try:
        list.append(list[list.__len__()-1] + (i+1)**4)
    except IndexError:
        list.append(1)
    print(list.__len__())

list_ = []
for i in range(10):
    re_one = 0
    for j in range(i):
        re_one +=(j+1)**4
    list_.append(re_one)
    print(list_.__len__())

print(list == list_)
print(list)
print(list_)