#!/usr/bin/env bash

REPOSITORY=/home/ec2-user/app/deploy

echo "> 현재 구동 중인 애플리케이션 pid 확인"
CURRENT_PID=$(pgrep -fl jipsayo | grep jar | awk '{print $1}')
echo "> 현재 구동 중인 애플리케이션 pid: $CURRENT_PID"

if [ -z "$CURRENT_PID" ]; then
    echo "> 현재 구동 중인 애플리케이션이 없으므로 종료하지 않습니다."
else
    echo "> kill -15 $CURRENT_PID"
    # shellcheck disable=SC2086
    kill -15 $CURRENT_PID
    sleep 5
fi

echo "> 새 애플리케이션 배포"
# shellcheck disable=SC2010
JAR_NAME=$(ls $REPOSITORY/ |grep 'jipsayo' | tail -n 1)
echo "> JAR Name: $JAR_NAME"

echo "> $JAR_NAME 에 실행권한 추가"
# shellcheck disable=SC2086
chmod +x $JAR_NAME

echo "> $JAR_NAME 실행"

nohup java -jar \
    -Dspring.config.location=classpath:/home/ec2-user/app/jipsayo_rds.yml \
    -Dspring.profiles.active=dev \
    $REPOSITORY/nohup.out 2>&1 &