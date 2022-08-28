import discord
import json

import commands as cmm

with open('./discord_bot/prefix,token.json','r') as F :
    jsons = json.load(F)

client = discord.Client()


token = jsons['token']
prefix = jsons['prefix']

@client.event
async def on_ready():

    print(client.user.name)
    print('봇이 dlfgksmswnd.')
    game = discord.Game('nalc에서 일하는 중.')
    await client.change_presence(status=discord.Status.online, activity=game)

@client.event
async def on_message(message) :
    print('message') #테스트용 지워도 무방
    if message.content[0] != prefix: return
    msg = message.content
    msg = msg[1:] #prefix 제거
    for i in cmm.__dict__ :
        if not i[0]+i[1] == '__' :
            exec(f'a = cmm.{i}')
            await a(message,msg)
        
    
client.run(token)
