# Ask
A Tinder-like platform to rent & sell items in cool and smart way.

## Business Model Canvas


![alt text](https://github.com/AlexisDrch/Ask/blob/master/CS8803-BMC-ASK.png)


### Front-end

#### Android 

#### IOS 

### Back-end

. To deploy changes on heroku

git add -A
git commit
git push heroku master

#### RESTfull API

#### DataBase 

. To deploy database schema on heroku

pg_dump --no-owner ask > ask-v0.dump
heroku pg:psql < ask-v0.dump

Note that you need to be in the folder where the .dump is

. Get heroku postgres database

heroku config:get DATABASE_URL -a ask-capa

