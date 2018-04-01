#!/usr/bin/env bash
docker-compose down
export BOT_API_KEY=$1
docker-compose up
