#!/usr/bin/env bash
cd docker
docker-compose down -v
export BOT_API_KEY=$1
docker-compose up
