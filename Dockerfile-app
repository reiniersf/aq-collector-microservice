FROM azul/zulu-openjdk-alpine:11.0.6 as builder
WORKDIR /opt
RUN mkdir maven build && wget -qO- "http://apache.mirrors.nublue.co.uk/maven/maven-3/3.6.3/binaries/apache-maven-3.6.3-bin.tar.gz" | tar xz -C maven --strip-components=1
ENV PATH=/opt/maven/bin:$PATH
RUN mvn --version
COPY . build
RUN cd build && mvn clean package
RUN ls build/target

FROM azul/zulu-openjdk-alpine:11.0.6
WORKDIR /opt
COPY --from=builder /opt/build/target/aq-collector-1.0-SNAPSHOT.jar .
EXPOSE 8091
ENTRYPOINT java -jar aq-collector-1.0-SNAPSHOT.jar
