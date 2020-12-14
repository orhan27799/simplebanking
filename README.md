# simplebanking spring boot and maven 

curl --location --request POST 'http://localhost:8080/account/v1/credit/669-7788' \
--header 'Content-Type: application/json' \
--header 'Accept: application/json' \
--data-raw '    {
        "amount": 1000.0
    }'

response would be (200):
{
    "status": "OK",
    "approvalCode": "67f1aada-637d-4469-a650-3fb6352527ba"
}


curl --location --request POST 'http://localhost:8080/account/v1/debit/669-7788' \
--header 'Content-Type: application/json' \
--header 'Accept: application/json' \
--data-raw '    {
        "amount": 50.0
    }'

response would be (200):
{
    "status": "OK",
    "approvalCode": "a66cce54-335b-4e46-9b49-05017c4b38dd"
}


