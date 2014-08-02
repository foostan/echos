FROM ubuntu:14.04

MAINTAINER foostan ks@fstn.jp

RUN apt-get update
RUN apt-get install -y software-properties-common
RUN add-apt-repository -y ppa:webupd8team/java && apt-get update
RUN echo oracle-java8-installer shared/accepted-oracle-license-v1-1 select true | /usr/bin/debconf-set-selections
RUN apt-get install -y oracle-java8-installer

ADD target/universal/stage echos

EXPOSE 9000
CMD /echos/bin/echos
