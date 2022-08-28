import json
import requests
import os
kacaoUrl = 'https://kapi.kakao.com'
sendurl = kacaoUrl+'/oauth/authorize?client_id=2ae8c33e11ccb13fa966976c83afe145&redirect_uri=https://localhost:3000&response_type=code&scope=friends,'
print(sendurl)