#!/usr/bin/env bash

mvn clean package

echo 'Copying files...'

HOST_INSTANCE_ADDRESS=$1

scp -i ~/.ssh/Catcher.pem \
    target/catcher-1.0-SNAPSHOT.jar \
    ec2-user@"$HOST_INSTANCE_ADDRESS":~/Catcher/

echo 'Restarting server...'

ssh -tt -i ~/.ssh/Catcher.pem ec2-user@"$HOST_INSTANCE_ADDRESS" << EOF

sudo mv ~/Catcher/* /var/www/catcher/Catcher/
sudo systemctl restart catcher
logout
EOF

echo 'Deployment has been completed.'