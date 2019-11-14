#!/usr/bin/env bash




case $1 in
    k) port='10.90.165.139'
    ;;
    z) port='10.90.167.158'
    ;;
    m) port='10.90.187.185'
    ;;
esac
mysql -upay -h $port -p6a88edc00e6bf3
