![Apple Passbook](http://images.apple.com/ios/whats-new/images/title_passbook.png)

# jersey-passbook

jersey-passbook is an example implementation of the [Apple Passbook Specification](https://developer.apple.com/library/prerelease/ios/#documentation/PassKit/Reference/PassKit_WebService/WebService.html "Apple Passbook Specification") running on [Jersey](http://jersey.java.net/ "Jersey") and based on the very excellent [Dropwizard](http://dropwizard.codahale.com/ "Dropwizard") framework. This isn't your grand dad's barnacle-encrusted Java code of bygone eras. It's succinct and has all the lovely performance advantages of the JVM.

## Requirements

- Java
- Maven
- Postgres 9.1 (I like Postgres and I make use of hstore – if you need to replace it with something else, it will require some amount of extra work)

## Getting started

To get cracking, check out the project and build a nice fat jar (A single jar file which contains all of the class files required to run the service):

```
mvn package
```

After that it's off to running the project:

```
java -jar target/jersey-passbook-1.0.jar server service-configuration.yml
```

There's also a Procfile for [foreman](https://github.com/ddollar/foreman "foreman"), if you prefer that. You can just run:

```
foreman start
```

For more info on getting up and running with dropwizard projects, check out the comprehensive [Dropwizard documentation](http://dropwizard.codahale.com/getting-started/ "Dropwizard documentation").

## Example

TODO

## Licence

jersey-passbook is available under the MIT licence. Please see LICENCE for further information.


