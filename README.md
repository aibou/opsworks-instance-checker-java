## Build

    mvn package

## Deploy

- Upload opsworks-instance-checker-${VERSION}.jar to Amazon Lambda.
- Set handler `moe.aibou.aws.lambda.opsworks.instance_checker.App::handler`