async def hi(message,msg) :
    if msg == 'hi' :
        await message.channel.send('안녕하세요')

async def by(message,msg) :
    if msg == 'by' :
        await message.channel.send('안녕히가세요')
