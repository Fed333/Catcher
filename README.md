# Catcher
Educational Web Application which helps people to learn new and consolidate current English words.

## Roles
* STUDENT
* TEACHER
* ADMIN

## Functionality
* create and edit account info
* view all words
* add words
* take words for learning
* view taken words
* take tests
* search words
* search students

## Hosting
The application is developed to be hosted on AWS EC2.
It leverages Nginx as a reverse proxy and systemctl for process management. Utilizing these tools ensures efficient handling of HTTP requests, automatic restarts, and streamlined process control for a reliable hosting configuration.
The key directories and files include:
- `/etc/systemd/system/catcher.service`: The Systemd service configuration file for managing the application process.
- `/var/www/catcher/`: The main directory containing the application files and assets.
- `/usr/local/bin/start-catcher.sh`: A script to start the application, executed by the Systemd service.

## Deploy
To deploy your application artefact.
Run the following command `bash ./scripts/deploy.sh $hosted-address`