# RedBag Routes
##
#### Base URL - `https://localhost:8080`

##
`/registerWithEmail` - POST

#### Params
```js
{
    "emailAddress":"a@a.com",  **REQUIRED**
    "password":"a"  **REQUIRED**
    "firstName":"John"  **REQUIRED**
    "lastName":"Doe"  **REQUIRED**
}
```

##
`/loginWithEmail` - POST

#### Params
```js
{
    "emailAddress":"a@a.com", **REQUIRED**
    "password":"a"  **REQUIRED**
}
```

##
`/loginWithFacebook` - POST

#### Params
```js
{
    "access_token":"<Token> as string format"
}
```


##
`/updateProfile` - POST

#### Params
Header = `Authorization`: __`your login token`__
```js
{
    "firstName":"John",
    "lastName":"Smith",
    "profilePictureUrl": "<link> as string format"
    ...
}
```


##
`/getMe` - POST

#### Params
Header = `Authorization`: __`your login token`__


##
`/offers/getOffers` - GET

#### Params
Header = `Authorization`: __`your login token`__


##
`/receipt/uploadReceipt` - POST

#### Params
Header = `Authorization`: __`your login token`__
```js
{
    "images":[<url of images>,<url of images>,...],
    "offers":[<list of offers id as string format>]
}
```

##
`/receipt/getReceipts` - GET

#### Params
Header = `Authorization`: __`your login token`__


##
`/forgot` - POST

#### Params
```js
{
    "emailAddress":"a@a.com"  **REQUIRED**
}

##
`/updateLocation` - POST

#### Params
```js
{
    "latitude":"40.843985",  **REQUIRED**
    "longitude":"30.843985"  **REQUIRED**
}
```









