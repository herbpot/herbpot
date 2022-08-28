import json
import requests

url = "https://kapi.kakao.com//v2/user/scopes" #친구 목록 가져오기
header = {"Authorization": 'KakaoAK ' + "a31456ce5795ac75440aff15745cb37f"}

response = requests.post(url, headers=header)
print(response)