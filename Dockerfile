FROM alpine:3.6
RUN apk add --no-cache mongodb-tools
COPY ./wait-for-mongo.sh .
COPY data/* /usr/src/app/
RUN chmod 700 ./wait-for-mongo.sh

ENTRYPOINT ["/bin/sh", "./wait-for-mongo.sh"]