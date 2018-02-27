FROM java:8-jdk

# Gradle
ENV GRADLE_VERSION 4.5.1
#ENV GRADLE_SHA 371cb9fbebbe9880d147f59bab36d61eee122854ef8c9ee1ecf12b82368bcf10

#RUN cd /usr/lib \
# && curl -fl https://downloads.gradle.org/distributions/gradle-${GRADLE_VERSION}-bin.zip -o gradle-bin.zip \
# && echo "$GRADLE_SHA gradle-bin.zip" | sha256sum -c - \
# && unzip "gradle-bin.zip" \
# && ln -s "/usr/lib/gradle-${GRADLE_VERSION}/bin/gradle" /usr/bin/gradle \
# && rm "gradle-bin.zip"

#RUN wget https://services.gradle.org/distributions/gradle-${GRADLE_VERSION}-all.zip && \
#    unzip gradle-${GRADLE_VERSION}-all.zip && \
#    ln -s gradle-${GRADLE_VERSION} gradle && \
#    rm gradle-${GRADLE_VERSION}-all.zip


RUN wget -q https://services.gradle.org/distributions/gradle-${GRADLE_VERSION}-bin.zip \
    && unzip gradle-${GRADLE_VERSION}-bin.zip -d /opt \
    && rm gradle-${GRADLE_VERSION}-bin.zip

ENV GRADLE_HOME /opt/gradle-${GRADLE_VERSION}
ENV PATH $PATH:/opt/gradle-${GRADLE_VERSION}/bin

# Set Appropriate Environmental Variables
#ENV GRADLE_HOME /usr/lib/gradle
#ENV PATH $PATH:$GRADLE_HOME/bin

EXPOSE  8080

WORKDIR /var/www/
CMD /bin/bash
