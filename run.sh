#!/usr/bin/env bash
 java \
    -javaagent:/opt/app/apm-agent.jar \
    -Delastic.apm.service_name=discovery \
    -Delastic.apm.server_urls=http://storage-1.zylliondata.local:8200,http://storage-2.zylliondata.local:8200,http://storage-3.zylliondata.local:8200 \
    -Delastic.apm.application_packages=com.zylliondata.d4i.discovery \
    -jar /opt/app/app.jar