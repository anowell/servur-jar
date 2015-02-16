FROM anowell/servur

RUN apt-get update && \
    apt-get install -y software-properties-common && \
    add-apt-repository -y ppa:webupd8team/java && apt-get -y update && \
    echo debconf shared/accepted-oracle-license-v1-1 select true | /usr/bin/debconf-set-selections && \
    echo debconf shared/accepted-oracle-license-v1-1 seen true | /usr/bin/debconf-set-selections && \
    apt-get -y install oracle-java8-installer oracle-java8-set-default scala && \
    rm -rf /var/cache/oracle-jdk8-installer && \
    rm -rf /var/lib/apt/lists/*

COPY target/scala-2.10/servur-jar_2.10-0.1-SNAPSHOT.jar /opt/servur-jar.jar

WORKDIR /home/arunner
USER arunner
CMD ["scala", "/opt/servur-jar.jar"]
