# RedBag Routes
###
#### Base URL - `https://localhost:8080`

###
`/postBloodRequest` - POST

#### Params Body
```js
{
	"bloodGroup": "A-",
	"numberOfBags": 1,
	"phoneNumber": "1-664-328-8791",
	"name": "Foo Bar",
	"address": "Ap #464-8482 Vitae Street",
	"email": "nec.urna@perinceptos.org",
	"age": 61,
	"location":{ 
         "type": "Point",
         "coordinates": [-70.37, 43.77]
     }
}
```

##
`/getBloodRequestList` - POST

#### Query url
```js
"skip" = number
"sort" = "asc" or "desc"
"bloodGroup" = "<array of bloodgroup>" must be -> bloodGroup=A+&bloodGroup=O- in encoded format
"maxDistance" = distance to find receipent within //10km
"longitude" = -73.17234234
"latitude" =  40.77234234
```

== doc to be updated == 