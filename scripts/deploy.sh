#!/bin/bash

REPOSITORY=/home/ubuntu/app/deploy

echo "> Build 파일 복사"
cp $REPOSITORY/zip/*.jar $REPOSITORY/

echo "> 현재 구동 중인 애플리케이션 pid 확인"
CURRENT_PID=$(pgrep -fl jipsayo | grep java | awk '{print $1}')
echo "> 현재 구동 중인 애플리케이션 pid: $CURRENT_PID"

if [ -z "$CURRENT_PID" ]; then
    echo "> 현재 구동 중인 애플리케이션이 없으므로 종료하지 않습니다."
else
    echo "> kill -15 $CURRENT_PID"
    kill -15 $CURRENT_PID
    sleep 5
fi

echo "> 새 애플리케이션 배포"
JAR_NAME=$(ls $REPOSITORY/ |grep 'jipsayo' | tail -n 1)
echo "> JAR Name: $JAR_NAME"

# deploy dir 이동
cd $REPOSITORY

echo "> $JAR_NAME 에 실행권한 추가"
chmod +x $JAR_NAME

echo "> $JAR_NAME 실행"
# 환경변수를 추가가 아닌 대체하는것!
nohup java -jar $JAR_NAME \
    -Dspring.config.location=classpath:/application.yml,classpath:/application-dev.yml,classpath:/application-rds.yml \
    -Dspring.profiles.active=dev \
    $JAR_NAME > $REPOSITORY/nohup.out 2>&1 &