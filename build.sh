#! /bin/bash

build() {
  echo "build -----"
  mvn clean package -DskipTests

  docker build -t zeebe-script-worker:${version} .
}

push() {
  echo "push image -----"
  docker tag zeebe-script-worker:${version}   ccr.ccs.tencentyun.com/sre_pcg/zeebe-script-worker:${version}
  docker push ccr.ccs.tencentyun.com/sre_pcg/zeebe-script-worker:${version}
}

version=1.1.0

build


if [ $# -gt 0 ] && [ $1=='push' ]; then

  push

fi