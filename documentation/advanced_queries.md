---
title: "Advanced queries"
last_updated: July 21, 2016
sidebar: documentation_sidebar
toc: true
---
This page is under construction, please check back soon!

{% comment %}
## Introduction

The GNS database can be queried using a general syntax which is very similar to the syntax supported by [MongoDB](https://www.mongodb.com/) with some small differences.

## Performing queries

Queries are performed by using the `selectQuery(String query)` method of `GNSClientCommands`. The results of the query are returned as a JSON formatted array containing GUIDs that match the criteria. If you are unfamiliar with JSON see the [wikipedia page]() and the [official site](http://www.json.org) for details on JSON.

Here's an example of how you would perform a query:

```java
GNSClientCommands client = new GNSClientCommands();

...

String query = "~hometown":"whoville"
JSONArray result = client.selectQuery(query);
``` 

## Query syntax

The syntax is similar to that used for the criteria part of the [find method in Mongodb](https://docs.mongodb.com/manual/reference/method/db.collection.find/). See also the [Query Operators](https://docs.mongodb.com/manual/reference/operator/query/) part of the MongoDB reference manual.

The differences are:

* You omit the outer set of brackets.
* Field names must be prefaced with a ~ (tilde) (Note: this restriction might go away if we decide to write a more complex parser)


There are additional exceptions to the query syntax made necessary by the use of the URI's for the HTTP client.

* You must use parentheses instead of brackets.

## Query operators

For the Java client here are some examples of query strings one could use. There are many more operators and examples in the [Query Operators] part of the manual.
Find all the records in the database whose field contains some value

Return records where the hometown field is the value whoville.

```java
String query = "~hometown":"whoville"
```

Note that in the above example the field could also have as a value an array whose first element is the string "whoville", but the mongodb query mechanism matches elements in arrays too when used this way.

### Comparison Operators

Return records where the money field has a value greater than zero (0).

```java
String query = "~money":{$gt:0}
```

### The AND operator

Return records where the money field has a value greater than zero (0) and less than 30.

```java
String query = "~money":($gt:0,$lt:30)
```

Note that there is an explicit $and operator, but usually just using a comma to separate query clauses will suffice.

### The OR operator

Return records where the hometown field is the value whoville OR the money field has a value greater than zero (0).

```java
String query = $or:[("~hometown":"whoville"),("~money":($gt:0))]
```

### The NOT operator

Return records whose money field is not less than 20 and importantly also records that don't have a money field.

```java
String query = "~money":($not:($lt:20))
```

Return all the records in the database:

```java
String query = "";
```

Note: There are limits to how many records can be returned, but these haven't been verified yet.

## GeoJSON Query Example

Given records in the GNS that contains the geoLocationCurrent field as a GeoJSON point like the one below:

```javascript
{
  "occupation": "hospital administrator",
  "age": 52,
  "name": "Ethel",
  "geoLocationHome": {
    "type": "Point",
    "coordinates": [
      -96.849359,
      32.755505
    ]
  },
  "geoLocationWork": {
    "type": "Point",
    "coordinates": [
      -96.83845,
      32.80924
    ]
  },
  "geoLocationCurrent": {
    "type": "Point",
    "coordinates": [
      -96.836627,
      32.810625
    ]
  },
}

{
  "occupation": "manager",
  "age": 65,
  "name": "Buford",
  "geoLocationHome": {
    "type": "Point",
    "coordinates": [
      -97.074197,
      32.87618
    ]
  },
  "geoLocationWork": {
    "type": "Point",
    "coordinates": [
      -97.194743,
      32.723211
    ]
  },
  "geoLocationCurrent": {
    "type": "Point",
    "coordinates": [
      -97.200472,
      32.725124
    ]
  },
}
```

You can execute a query like the one below to find records contained in the polygon and that match other attributes.

```java
$and: 
    [{~geoLocationCurrent:
        {$geoWithin:
            {$geometry:
                {"type":"Polygon",
                "coordinates":[[[-97.29004669189453,32.7139892578125],
                  [-97.28470611572266,32.7139892578125],
                  [-97.28470611572266,32.71849060058594],
                  [-97.28470611572266,32.72298812866211],
                  [-97.29004669189453,32.72298812866211],
                  [-97.29004669189453,32.71849060058594],
                  [-97.29004669189453,32.7139892578125]]]}}}},
    {~age:{$gt:60, $lt:999}}]
```
{% endcomment %}
