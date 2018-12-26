#! /bin/bash
sudo service twitter-analytics stop
rm -rf /home/ec2-user/twitter-analytics.jar
cp /home/ec2-user/tas-build/twitter-analytics.jar /home/ec2-user
rm -rf twitter-analytics.jar
sudo service twitter-analytics start