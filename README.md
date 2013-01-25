![Apple Passbook](http://images.apple.com/ios/whats-new/images/title_passbook.png)

# jersey-passbook

jersey-passbook is an example implementation of the [Apple Passbook Specification](https://developer.apple.com/library/prerelease/ios/#documentation/PassKit/Reference/PassKit_WebService/WebService.html "Apple Passbook Specification") running on [Jersey](http://jersey.java.net/ "Jersey") and based on the very excellent [Dropwizard](http://dropwizard.codahale.com/ "Dropwizard") framework. This isn't your grand dad's barnacle-encrusted Java code of bygone eras. It's succinct and has all the lovely performance advantages of the JVM.

## Requirements

- Java
- Maven
- Postgres 9.1 (I like Postgres and I make use of hstore – if you need to replace it with something else, it will require some amount of extra work)

## Getting started

To get cracking, check out the project and build a nice fat jar (A single jar file which contains all of the class files required to run the service):

```bash
$ mvn package
```

After that it's off to running the project:

```bash
$ java -jar target/jersey-passbook-1.0.jar server service-configuration.yml
```

There's also a Procfile for [foreman](https://github.com/ddollar/foreman "foreman"), if you prefer that. You can just run:

```bash
$ foreman start
```

For more info on getting up and running with dropwizard projects, check out the comprehensive [Dropwizard documentation](http://dropwizard.codahale.com/getting-started/ "Dropwizard documentation").

## Examples

For details regarding expected response codes please refer to the [Apple Passbook Specification](https://developer.apple.com/library/prerelease/ios/#documentation/PassKit/Reference/PassKit_WebService/WebService.html "Apple Passbook Specification").

For testing http calls, I can highly recommend [httpie](https://github.com/jkbr/httpie "httpie").

### Registering a Device to Receive Push Notifications for a Pass

```bash
$ http POST http://0.0.0.0:8080/v1/devices/deviceLibraryIdentifier/registrations/passTypeIdentifier/serialNumber pushToken=pushTokenValue HTTP_AUTHORIZATION:"ApplePass authenticationToken"
```

So here we're registering a device with deviceLibraryIdentifier, passTypeIdentifier and serialNumber. The payload is a JSON dictionary containing:

```json
{
  "pushToken": "pushTokenValue"
}
```

And finally, there's the HTTP_AUTHORIZATION header which always is set to ApplePass followed by a space followed by the pass’s authorization token as specified in the pass.

### Getting the Serial Numbers for Passes Associated with a Device

```bash
$ http GET http://0.0.0.0:8080/v1/devices/deviceLibraryIdentifier/registrations/passTypeIdentifier?passesUpdatedSince=tag
```

If there are matching passes, we'll receive a JSON dictionary with:

```json
{
  "lastUpdated": "timestamp",
  "serialNumbers": [
    "serial1",
    "serial2"
  ]
}
```

If nothing's found, the service returns status code 204.

There's the optional passesUpdatedSince URL parameter, jersey-passbook would expect a timestamp and filter the result on that timestamp.

### Getting the Latest Version of a Pass

```bash
$ http GET http://0.0.0.0:8080/v1/passes/passTypeIdentifier/serialNumber HTTP_AUTHORIZATION:"ApplePass authenticationToken"
```

This call returns the latest version of the requested passs. The response is a JSON dictionary with the pass data. The pass data is stored as hstore so it can basically be anything we want it to be:

```json
{
  "foo": "bar",
  "baz": "bat",
  "xyz": 123
}
```

The server will also set the proper lastModified value in the response header.

### Unregistering a Device

```bash
$ http DELETE http://0.0.0.0:8080/v1/deviceLibraryIdentifier/registrations/passTypeIdentifier/serialNumber HTTP_AUTHORIZATION:"ApplePass authenticationToken"
```

Just returns 200 OK or any other HTTP status code that is appropriate.

### Logging Errors

```bash
$ http POST http://0.0.0.0:8080/v1/log/ logs:=['error1', 'error2']
```

Just returns 200 OK. As Apple puts it:

> This endpoint is intended to help you debug your web service implementation. Log messages contain a description of the error in a human-readable format.

## Licence

jersey-passbook is available under the MIT licence. Please see LICENCE for further information.


