![alt text](https://travis-ci.org/kgribov/TelegramBot.svg?branch=master)

# TelegramBot

## Overview
This project is a simplest way to create a **Telegram** bot with Scala API: [TelegramBotScalaApi](https://github.com/kgribov/TelegramBotScalaApi).

It have all dependencies that you need, CLI parsing, logging, monitoring and packaging into docker. You will have you own bot platform.

Requirements for your environmet:

* SBT and Scala version 2.12 or higher
* Docker and Docker Compose version 2 or higher

## Getting started

To create your own bot just follow next steps:

1. Create your bot in Telegram and get bot API key, instructions: [Creating your bot in Telegram](https://github.com/kgribov/TelegramBot#creating-your-bot-in-telegram)
1. Clone this repository to your computer: `git clone https://github.com/kgribov/TelegramBot.git`
1. Go to directory with project: `cd TelegramBot`
1. Build it using SBT: `sbt docker`
1. Grand permissions for start script: `chmod +x startup.sh`
1. Start bot with script: `./startup.sh <Bot API key>`

That's all, your bot is running now!

You can type something to him and he will calculate your message size or type `/random` command to get a random number.

Open bot dashboard [Bot Dashboard](http://localhost:9999/d/bots/telegram-bot-dashboard?refresh=10s&orgId=1) to see metrics of your bot.

> If you don't see any data on dashboard, no panic! Give some time to system to fully start-up (like 1-2 minutes).

## Creating your bot in Telegram

To create your bot in Telegram follow official instructions from Telegram team: [Instructions](https://core.telegram.org/bots#6-botfather).

Don't forget to disable privacy rules for your bot, to read all messages in chat, BotFather could do it with command: `/setprivacy`, set status to **DISABLED**.

## Customize your bot

If you want to implement your own message processing, your own commands and dialogs with bot, open and modify class `CreateBotSchema`.
To discover all bot Scala API features, read documentation in Scala API repo: [API features](https://github.com/kgribov/TelegramBotScalaApi#api-features)

## Monitoring
