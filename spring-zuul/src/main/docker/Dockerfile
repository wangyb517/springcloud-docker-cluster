FROM java:8

RUN ln -snf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && echo 'Asia/Shanghai' > /etc/timezone

#将本地文件夹挂载到当前容器
#VOLUME /tmp

ADD spring-zuul-0.0.1-SNAPSHOT.jar app.jar
#RUN ["/bin/bash","-c","touch /app.jar"]

#指定JAVA 环境变量
#ENV JAVA_HOME /jdk/jre
#ENV PATH $PATH:$JAVA_HOME/bin
#ENV CLASSPATH .:$JAVA_HOME/lib/rt.jar:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar
#开放8000端口
EXPOSE 8888
#配置容器启动后执行的命令
ENTRYPOINT ["java","-jar","/app.jar"]
