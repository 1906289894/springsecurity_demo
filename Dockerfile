FROM registry.zylliondata.local/centos:7-java-11

WORKDIR /opt/app
ENV HOME /opt/app
ADD . /opt/app

RUN mvn -B clean package && mv target/app.jar app.jar \
    && mvn -B clean && rm -rf ~/.m2/ src/ /bin/mvn /opt/apache-maven* \
    && yum autoremove -y \
    && yum clean all && rm -rf /tmp/* && rm -rf /usr/share/doc/ && rm -rf /usr/share/man

USER 1001
EXPOSE 5273

ENTRYPOINT ["java", "-jar", "/opt/app/app.jar"]