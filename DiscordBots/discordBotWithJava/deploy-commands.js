const { SlashCommandBuilder } = require('@discordjs/builders');
const { REST } = require('@discordjs/rest');
const { Routes } = require('discord-api-types/v9');
const { clientId, token } = require('./config.json');

const commands = [
	new SlashCommandBuilder().setName('ping').setDescription('Replies with pong!'),
	new SlashCommandBuilder().setName('server').setDescription('Replies with server info!'),
	new SlashCommandBuilder().setName('user').setDescription('Replies with user info!'),
    new SlashCommandBuilder().setName('join').setDescription('당신이 있는 음성체널로 들어와요'),
	new SlashCommandBuilder().setName('leave').setDescription('당신이 있는 음성체널에서 떠나요'),
]
	.map(command => command.toJSON());

const rest = new REST({ version: '9' }).setToken(token);

module.exports = {
    update : (guildId) => {
        rest.put(Routes.applicationGuildCommands(clientId, guildId), { body: commands })
    	    .then(() => console.log('Successfully registered application commands.'))
	        .catch(console.error);
        },

    }
