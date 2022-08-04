# Server Monitoring Bot

Cross-platform bot for monitoring your server and sending reports to chats (currently supported only
telegram). 

## Overview

Current features:
* Fetching information about system
* Message templating
* CLI Args
* Sending messages to telegram
* Background jobs 

Planned features:
* More templates
* Discord/Slack/VK Messenger support
* Logging


Known issues:
* Logging via logback, lg4j, etc.. not working


## Environment Variables and Set Up

To run this project, you will need to add the following environment variables to your
operating system (before launch)

`TG_CHAT_ID` - Chat ID where bot send reports

`TG_URL` - URL For sending messages (https://api.telegram.org/botID:AccessToken/sendMessage)

Then:

```bash
java -jar TGMonitoringBot-<version>.jar --interval 30
```

By default **TimeUnit** set to MINUTES so Bot will send report every 30 minutes.
If you want to change unit, for example to seconds just do like this:

```bash
java -jar TGMonitoringBot-<version>.jar --interval 30 --unit SECONDS
```

Full list of units you can find by providing --help option:
```bash
java -jar TGMonitoringBot-<version>.jar --help
```

## Authors

- [@nominori-dev](https://www.github.com/nominori-dev)

