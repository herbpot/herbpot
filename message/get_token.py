import requests

url = "https://kauth.kakao.com/oauth/token"

data = {
    "grant_type" : "authorization_code",
    "client_id" : "2ae8c33e11ccb13fa966976c83afe145",
    "redirect_url" : "https://localhost:3000",
    "code" : "NcCUGBAuwIe34rfFlDppiJu8oCIrSm0vkr3_55qCEB9h1bGOgg2FBjVY7WHAD7zkoNds4wopcJ8AAAF9Hpp9_g"
}
data_ = {
    "grant_type": "refresh_token",
    "client_id": "2ae8c33e11ccb13fa966976c83afe145",
    "refresh_token": "TgnFRuW8ykGC3AeYKKfRj9ojBVZx3cJxu_PtPwo9dRoAAAF9HprxyA"
}
response = requests.post(url, data=data_)
tokens = response.json()
print(tokens)