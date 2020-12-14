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



curl --location --request GET 'http://localhost:8080/account/v1/669-7788'

response would be:

{
    "accountNumber": "669-7788",
    "owner": "Kerem Karaca",
    "balance": 950.0,
    "createDate": "2020-03-26T06:15:50.550+0000",
    "transactions": [
        {
            "date": "2020-03-26T06:16:03.563+0000",
            "amount": 1000.0,
            "type": "DepositTransaction",
            "approvalCode": "67f1aada-637d-4469-a650-3fb6352527ba"
        },
        {
            "date": "2020-03-26T06:16:35.047+0000",
            "amount": 50.0,
            "type": "WithdrawalTransaction",
            "approvalCode": "a66cce54-335b-4e46-9b49-05017c4b38dd"
        }
    ]
}

