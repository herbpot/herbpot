const { Client, Intents } = require('discord.js');
const { joinVoiceChannel,createAudioResource,NoSubscriberBehavior,createAudioPlayer } = require('@discordjs/voice')
const express = require('express')
const ttsmodule = require('google-tts-api')

const app = express()
const { token } = require('./config.json')
const setCommand = require('./deploy-commands.js');

const client = new Client({ intents: [Intents.FLAGS.GUILDS,Intents.FLAGS.GUILD_VOICE_STATES] });

var connection;

client.once('ready', (msg) => {
    map = msg.guilds.cache;
    // console.log(typeof(map),map);
    const Guilds = client.guilds.cache.map(guild => guild.id);
    console.log(Guilds);
    for(var i=0; i<Guilds.length;i++){
        setCommand.update(Guilds[i])
    }
    // console.log(msg);
    
})

client.once('guildCreate',(guild) => {
    // console.log(guild.id);
    setCommand.update(guild.id)
})

client.on('interactionCreate', async interaction => {
	if (!interaction.isCommand()) return;

	const { commandName } = interaction;

	switch (commandName) {
        case "ping":
            await interaction.reply('Pong!');
            break;
        case "server":
            await interaction.reply(`Server name: ${interaction.guild.name}\nTotal members: ${interaction.guild.memberCount}`);
            break;
        case "user":
    		await interaction.reply(`Your tag: ${interaction.user.tag}\nYour id: ${interaction.user.id}`);
            break;
        case "join":
            try{
                connection = joinVoiceChannel(
                    {
                        channelId: interaction.member.voice.channel.id,
                        guildId: interaction.guild.id,
                        adapterCreator: interaction.guild.voiceAdapterCreator
                    }
                )
                // console.log(connection.subscribe());
                interaction.reply("Hi!")
            }catch{interaction.reply("저런.. 에러에요");}
            break;
        case "leave":
            try{
                connection.disconnect()
                interaction.reply("Bye!")
            }catch{interaction.reply("저런.. 에러에요 혹시 들어가지도 않았는데 나가라는건 아니죠?")}
            break;
        case "out":
            interaction.
            break
        default:
            break;
    }
});

client.on("voiceStateUpdate",(old,now) => {
    try{
        const guild = client.guilds.cache.get(old.guild.id)
        const members = old.channel.members.map(member => member.id)
        for(var i=0;i<members.length;i++){
            const memberId = members[i]
            const member = guild.members.cache.get(memberId)
            if(member.user.bot == true){
                member.voice.disconnect()
            }
        }
    }catch{}
})

client.login(token);

// express


app.get('/online/:url',(req,res) => {
    const Guilds = client.guilds.cache.map(guild => guild.id);
    for(var i = 0; i<Guilds.length;i++){
        const guild = client.guilds.cache.get(Guilds[i])
        guild.systemChannel.send(`MincreftServer (${req.params.url}) is alive!`)
    }
})

app.get('/offline/:url', (req,res) => {
    const Guilds = client.guilds.cache.map(guild => guild.id);
    for(var i = 0; i<Guilds.length;i++){
        const guild = client.guilds.cache.get(Guilds[i])
        guild.systemChannel.send(`MincreftServer (${req.params.url}) is stop!`)
    }
})

app.get('/tts/:str',(req,res) => {
    const str = req.params.str
    const url = ttsmodule.getAudioUrl(str,{
        lang: 'ko',
        slow: false,
        host: 'https://translate.google.com',
    })
    // console.log(connection);
    const player = createAudioPlayer({
        behaviors: {
            noSubscriber: NoSubscriberBehavior.Pause,
        },
    });
    const resource = createAudioResource(url);
    player.play(resource);
    // console.log(connection.subscribe);
    connection.subscribe(player);
})


app.listen(8000,()=>console.log('http://localhost:8000'))