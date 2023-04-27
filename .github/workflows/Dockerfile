FROM 16601154299/centos7-jdk8-tomcat
MAINTAINER "wangxt@chuangkit"<wangxt@ckt.cn>

EXPOSE 8081
ENV PROJECT_NAME interface-server
ENV PROJECT_ROOT /usr/local/springboot-${PROJECT_NAME}
RUN mkdir -p ${PROJECT_ROOT}
COPY ./target/classes/start.sh ${PROJECT_ROOT}/
COPY ./target/${PROJECT_NAME}.jar ${PROJECT_ROOT}/app.jar
RUN chmod 777 ${PROJECT_ROOT}/start.sh \
     && ln -s "$PROJECT_ROOT/start.sh" /usr/local/bin/start
CMD  ["/bin/bash", "-c", "start"]