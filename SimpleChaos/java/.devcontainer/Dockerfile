FROM ubuntu:20.04
RUN apt-get update && \
	rm /bin/sh && ln -s /bin/bash /bin/sh && \
	apt-get -qq -y install curl wget unzip zip && \
	curl -s "https://get.sdkman.io" | bash && \
	/bin/bash -c "source /root/.sdkman/bin/sdkman-init.sh; \
	sdk install java 17.0.1-tem; \
	sdk install maven"
