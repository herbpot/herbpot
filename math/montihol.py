import asyncio
from random import randint as r
import time as t

async def main_change(count,doorcount=3):
    # print(count+1)
    global sus,fail
    l = list(range(1,doorcount+1,1))
    answer = l[r(0,2)] # 정답 선정
    ans = l[r(0,2)] # 랜덤 선텍
    l_ = l.copy()
    l_.remove(answer) #정답이 없는 리스트
    try:
        l_.remove(ans) #선택지 제외
    except:
        pass
    l.remove(l_[r(0,len(l_)-1)]) #정답이 아닌 것 하나 제외
    l.remove(ans)
    ans = l[r(0,len(l)-1)]
    if ans == answer:
        sus += 1
        # print('suscess!')
    else :
        fail += 1
    return

async def main_unchange(count,doorcount=3):
    # print(count+1)
    global sus,fail
    l = list(range(1,doorcount+1,1))
    answer = l[r(0,2)] # 정답 선정
    ans = l[r(0,2)] # 랜덤 선텍
    l_ = l.copy()
    l_.remove(answer) #정답이 없는 리스트
    try:
        l_.remove(ans) #선택지 제외
    except:
        pass
    l.remove(l_[r(0,len(l_)-1)]) #정답이 아닌 것 하나 제외
    l.remove(ans)
    if ans == answer:
        sus += 1
    else :
        fail += 1
        # print('suscess!')
    return

total = int(input("반복횟수 >>>"))
doorcount_ = int(input("문 개수 >>>"))
sus,fail = 0,0
for i in range(total):
    asyncio.run(main_change(i,doorcount_))
print(f"문이 {doorcount_}개이고 선택을 바꿀 때 확률 (성공,실패)\n",sus/total,"/", fail/total)
sus,fail = 0,0
for i in range(total):
    asyncio.run(main_unchange(i,doorcount_))
print(f"문이 {doorcount_}개이고 선택을 바꿀 때 확률 (성공,실패)\n",sus/total,"/", fail/total)
