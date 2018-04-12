#!/usr/bin/env bash
cd docker
docker-compose down
export BOT_API_KEY=$1
docker-compose up -d
