# Learning Akka Streams & Http 2.4.x

This project is connected to the [How to read a web page, as a stream of lines, using akka-http 2.4.6](http://stackoverflow.com/questions/37416663/how-to-read-a-web-page-as-a-stream-of-lines-using-akka-http-2-4-6) entry in StackOverflow.

## Task

How to read a web resource, and expose it as a stream of `String`s?

## Getting started

You'll need:
- `sbt`
- `npm`

We use `npm serve` to serve the `data/a.txt` file as `http://localhost/a.txt`:

```
$ npm install serve
$ node_modules/.bin/serve data
```

Then:

```
$ sbt test
```


## Background

The role of this repo is to serve as a sample to get the code running. Once it is, it can serve as a sample to others.

AKa240516

