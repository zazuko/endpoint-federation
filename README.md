# Endpoint federation

This project creates a federated endpoint using [FedX](https://rdf4j.org/documentation/programming/federation/).

That way you can get a single endpoint that is able to query multiple SPARQL endpoints.

## Quick start

Configure your endpoints in the [`app/config.ttl`](./app/config.ttl) file.

You can have some examples here: https://rdf4j.org/documentation/programming/federation/#member-configuration

Run the server using the following command:

```sh
./gradlew run
```

The endpoint will be available at: http://localhost:8080/sparql

## POST query

You will need to set the following headers:

- `Accept`: `application/sparql-results+json` or `application/sparql-results+xml`
- `Content-Type`: `x-www-form-urlencoded`

Example of query using `curl`:

```sh
curl -X POST \
  'http://localhost:8080/sparql' \
  -H 'Accept: application/sparql-results+json' \
  -H 'Content-Type: application/x-www-form-urlencoded' \
  --data 'query=SELECT DISTINCT * WHERE { ?s ?p ?o } LIMIT 100'
```

## GET query

You will need to set the following headers:

- `Accept`: `application/sparql-results+json` or `application/sparql-results+xml`
- `Content-Type`: `x-www-form-urlencoded`

The query needs to be urlencoded and passed to the `query` parameter.
For big queries, you should use the `POST` method.

Example of query using `curl`:

```sh
curl 'http://localhost:8080/sparql?query=SELECT%20*%20WHERE%20%7B%20%3Fs%20%3Fp%20%3Fo%20%7D%20LIMIT%2010' \
  -H 'Accept: application/sparql-results+json' \
  -H 'Content-Type: application/x-www-form-urlencoded'
```
