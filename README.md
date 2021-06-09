# Basic Usage

```shell
export BITBUCKET_AUTH=<token>
export BITBUCKET_OWNER=<owner>
export BITBUCKET_PROJECT_NAME=<project name>
export DB_HOST=<host>
export DB_USER=<user>
export DB_PASSWORD=<pass>
export DB_PORT=<port>
export JAVA_OPS=-Xms100m -Xmx1536m

java
```

# Docker

```shell
docker-compose up
```

# Build from source

```shell
git clone https://github.com/msoa/bitbucket-extractor.git
cd bitbucket-extractor
./gradlew build
```

# API

# How does it work?

This extractor gets data about repositories, pull requests, commits, comments and activities from the Bitbucket API. First get data from repositories by filtering by project name,
then, for each repository, it gets the pull requests, commits, comments, activities and stores them in the database as json data.

# License

[MIT](./LICENSE)