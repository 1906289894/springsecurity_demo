FROM registry.zylliondata.local/centos:7-java-11

WORKDIR /opt/app
ENV HOME /opt/app
ADD . /opt/app

RUN curl http://nexus.zylliondata.local/repository/res/boot-maven.sh | bash

USER 1001
EXPOSE 5273

ENTRYPOINT ["bash", "/opt/app/run.sh"]