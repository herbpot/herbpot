import json
import requests
import os
url = "https://kapi.kakao.com/v1/api/talk/friends" #친구 목록 가져오기
header = {"Authorization": 'Bearer ' + "w_XELl4Waaq1AEg3-pD3drdig0gw_7UTLlQ-pAo9cxcAAAF9Hptkzw"}

response = requests.get(url, headers=header)
result = json.loads(response.text)
friends_list = result.get("elements")

print(friends_list)

uuids = []
uuids.append(friends_list[0].get('uuid'))

print(uuids)

kacaoUrl = 'https://kapi.kakao.com/ '
url = 'v1/api/talk/friends/message/default/send'

URL = kacaoUrl + url

headers = {
    "Authorization": "Bearer " + 'ObvPKu646EXku2gQn70HGcKIkpZviphhPnnA3gorDKgAAAF9HpA5qw'
}

data = {
    'receiver_uuids': '["_8n5yP_G_8bx3e7a6N7v1-PW-sz6zvbO_HY"]',
    "template_object" : json.dumps({
        "object_type": "text",
        "text": "앙",
        "link": {
            "web_url" : "https://www.google.com"
        }
        })
}

response = requests.post(URL,headers=headers,data=data)
print(response.status_code)
if response.json().get('result_code') == 0 :
    print('send')
else:
    print(str(response.json()))