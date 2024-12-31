#!/bin/bash
DIR="$( cd "$( dirname "$( realpath "${BASH_SOURCE[0]}" )" )" >/dev/null && pwd )"
java -jar "$DIR/DungeonCrawler1.1.jar" "$1"
