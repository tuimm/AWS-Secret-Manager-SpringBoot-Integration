# springboot-aws-secret-manager-sample

### 1. Mongo setup

To run this project you need to start up a mongo server with the authentication enabled.

TO startup mongo we have used a docker container by running the next command:

- > docker run -d --name mongodb -p 27017:27017 -e MONGO_INITDB_ROOT_USERNAME=mongoadmin -e MONGO_INITDB_ROOT_PASSWORD=secret -v /var/mongodb/data:/data/db mongo

You can check the docker has started properly with the command:

- > docker ps -a

Then you need to create a user to for the database you will work with. To do that you can do it by using mongosh client.

- > mongosh "mongodb://localhost:27017" --username mongoadmin --authenticationDatabase admin -p
- > test> db.createUser({user:"myUser", pwd: passwordPrompt(), roles:[{role:"readWrite",db:"test"}]})

### 2. Secret Manager setup

Following the spring documentation: https://github.com/spring-attic/spring-cloud-aws/blob/main/docs/src/main/asciidoc/secrets-manager.adoc

We have to create the secret with the next name:

> /secret/springboot-aws-secret-manager-sample/local

Take into account that local is the active profile you have enabled in your app. Theoretically you should at least to create another secret:

> /secret/springboot-aws-secret-manager-sample

### 3. Application Configuration


In you don't want to create an extra secret you have to set the next configuration in the bootstrap:

````
aws:
    secretsmanager:
        enabled: true
        profile-separator: "/"
        name: springboot-aws-secret-manager-sample
        prefix: /secret
        default-context: springboot-aws-secret-manager-sample
        failFast: false
````

- By setting failFast you do not need to create the secret we mentioned above (" /secret/springboot-aws-secret-manager-sample"), your app will start properly, but it will print a warning mentioning that cannot find a secret
- By setting the default-context with the same value of the service name we avoid having to create more secrets as showed in the spring doc.


We need to add the next three libraries:

````
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-aws-secrets-manager-config</artifactId>
            <version>2.2.6.RELEASE</version>
        </dependency>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-bootstrap</artifactId>
        </dependency>
````

###  4. Examples

- How to refer a secret from the yml?
````
spring:
  data:
    mongodb:
      username: ${username}
      password: ${password}
````
- How to refer a secret from the code ?

````
  @Value(value = "${username}")
  private String username;
````
