# FINT Polling Audit Plugin

[![Build Status](https://travis-ci.org/FINTlabs/fint-audit-polling-plugin.svg?branch=master)](https://travis-ci.org/FINTlabs/fint-audit-polling-plugin)
[![Coverage Status](https://coveralls.io/repos/github/FINTLabs/fint-audit-polling-plugin/badge.svg?branch=master)](https://coveralls.io/github/FINTLabs/fint-audit-polling-plugin?branch=master)

Implementation of fint-audit-api using mongodb.

## Installation

build.gradle

```
repositories {
    maven {
        url  "http://dl.bintray.com/fint/maven"
    }
}

compile('no.fint:fint-audit-polling-plugin:1.6.1')
```

## Usage

- Set `@EnableFintAudit` on your application class
- `@Autowire` in the FintAuditService interface and call `audit(Event event)`. This will automatically clear the event data
- Use `audit(Event event, Status... statuses)` will set the status on the event and audit it. Multiple statuses will cause multiple audit log statements
- If you need control of when to clear the event data, use `audit(Event event, boolean clearData)`

## Configuration

| Key | Default value | Comment |
|-----|---------------|---------|
