#!/usr/bin/env bash

echo 'Copy files...'

scp -i ~/.ssh/id_rsa \
    target/AfishaParserService-0.1.0.jar \
    $REMOTEUSER@$REMOTEHOST:$REMOTEAPPDIR

echo 'Bye'