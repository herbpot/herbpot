from posixpath import split
import requests
from bs4 import BeautifulSoup as bs
import serial
import time

url = 'http://nelpi.duckdns.org:3033'


py_serial = serial.Serial(
    
    # Window
    'COM7',
    
    # 보드 레이트 (통신 속도)
    9600,

    timeout=1
)

while True:
      
    commend = "send"
    
    py_serial.write(commend.encode())
    
    time.sleep(0.1)
    
    if py_serial.readable():
        
        # 들어온 값이 있으면 값을 한 줄 읽음 (BYTE 단위로 받은 상태)
        # BYTE 단위로 받은 response 모습 : b'\xec\x97\x86\xec\x9d\x8c\r\n'
        response = py_serial.readline()
        
        # 디코딩 후, 출력 (가장 끝의 \n을 없애주기위해 슬라이싱 사용)
        res: str = response[:len(response)-1].decode()
        res.split('/')
        print("res: ",res)
        if(res):
            for i in res :
                i.strip()
            if res[0] == "true":
                isOn = "false"
            else :
                isOn = "true"
            minu = res[1]
            print(res,isOn,minu)
            requests.get(url+f"?isOn={isOn},minu={minu}")
        
    commend = input('>>>')
        
