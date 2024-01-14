#!/bin/bash -xe

if [ -z "${M2_HOME}" ]; then
  export MVN_EXEC="mvn"
else
  export MVN_EXEC="${M2_HOME}/bin/mvn"
fi

$MVN_EXEC clean package -DskipTests

cp -R target/album-0.0.1-SNAPSHOT.jar docker
cd docker

docker compose -f docker-album.yml up --build -d
rm -rf album-0.0.1-SNAPSHOT.jar