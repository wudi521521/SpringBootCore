#! /bin/bash

getCurrentPath(){
        SOURCE="$0"
        while [ -h "$SOURCE"  ]; do # resolve $SOURCE until the file is no longer a symlink
            DIR="$( cd -P "$( dirname "$SOURCE"  )" && pwd  )"
            SOURCE="$(readlink "$SOURCE")"
            [[ $SOURCE != /*  ]] && SOURCE="$DIR/$SOURCE" # if $SOURCE was a relative symlink, we need to resolve it relative to the path where the symlink file was located
        done
        DIR="$( cd -P "$( dirname "$SOURCE"  )" && pwd  )"
        echo $DIR
}

scriptHome=`getCurrentPath`;
env="beta";
echo "launch script";
echo $scriptHome;
echo $env;

cd $scriptHome;
source /etc/profile

java -version
root=$scriptHome;
port=8081;
heapMem="4096m";
directMem="1024m";
jarName="app.jar";
mkdir -p $root/logs;

java \
-Dfile.encoding=UTF8 \
-Duser.timezone=GMT+08 \
-Dons.client.logRoot=/root/logs/ons \
-Dons.client.logLevel=WARN \
-Dons.client.logFileMaxIndex=1 \
-Xms$heapMem \
-Xmx$heapMem \
-XX:MaxDirectMemorySize=$directMem \
-jar $root/$jarName  \
--server.port=$port \
| tee -a $root/logs/$port.log
