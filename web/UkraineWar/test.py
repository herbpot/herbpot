import time 
import datetime
import requests
from bs4 import BeautifulSoup as bs

url = "https://namu.wiki/w/2022%EB%85%84%20%EB%9F%AC%EC%8B%9C%EC%95%84%EC%9D%98%20%EC%9A%B0%ED%81%AC%EB%9D%BC%EC%9D%B4%EB%82%98%20%EC%B9%A8%EA%B3%B5"
targeturl = 'http://localhost:8000/addinfo/herbseed'
header = {"user-agent":"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/98.0.4758.102 Safari/537.36"}

def DataUpdate() :
    get = requests.get(url=url,headers=header)
    soup = bs(get.text,'html.parser')
    date = soup.select_one(r'#taKEcJzeY > div.\39 81597e1 > div > div > div > div > div.UdZA-86X > div:nth-child(4) > div:nth-child(6) > div > div > div > div > div > div > div > div > div > div:nth-child(8) > div:nth-child(1) > div > div.qGjF\+Czc._8g6BRxxu > table > tbody > tr:nth-child(6) > td > div > strong')
    dath = soup.select_one(r'#taKEcJzeY > div.\39 81597e1 > div > div > div > div > div.UdZA-86X > div:nth-child(4) > div:nth-child(6) > div > div > div > div > div > div > div > div > div > div:nth-child(8) > div:nth-child(1) > div > div.qGjF\+Czc._8g6BRxxu > table > tbody > tr:nth-child(20) > td:nth-child(1) > div:nth-child(1) > strong:nth-child(6)')
    print(soup.find())
    print(date.text,dath.text)
    requests.get(url=targeturl+f"?day={'D'+date.text.replace('일','')}&dath={dath.text.replace('~','')}")

def main():
    lastdate = 0
    print('crolor is online')
    while True:
        nowdate = datetime.date.day
        print(nowdate)
        if nowdate != lastdate :
            DataUpdate()
        time.sleep(10)
        print(lastdate,nowdate)
        lastdate = nowdate

main()