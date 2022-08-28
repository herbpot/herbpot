import requests
import json
url = "https://kapi.kakao.com/v1/api/talk/friends" #친구 목록 가져오기
header = {"Authorization": 'Bearer ' + "w_XELl4Waaq1AEg3-pD3drdig0gw_7UTLlQ-pAo9cxcAAAF9Hptkzw"}

response = requests.get(url, headers=header)
result = json.loads(response.text)
friends_list = result.get("elements")

print(friends_list[0].get('uuid'))
