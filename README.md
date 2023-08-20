# PruebaTecnicaFonYou

## creacion de examen
curl --location 'localhost:8001/api/exam' \
--header 'Content-Type: application/json' \
--data '{
    "name": "Exam 1",
    "questionDtoList": [
        {
            "questionName": "question 1",
            "answer": [
                {
                    "name": "opt_1",
                    "value": false
                },
                 {
                    "name": "opt_2",
                    "value": false
                },
                 {
                    "name": "opt_3",
                    "value": false
                },
                 {
                    "name": "opt_4",
                    "value": true
                }
            ]
        },
        {
            "questionName": "question 2",
            "answer": [
                {
                    "name": "opt_1",
                    "value": true
                },
                 {
                    "name": "opt_2",
                    "value": false
                },
                 {
                    "name": "opt_3",
                    "value": false
                },
                 {
                    "name": "opt_4",
                    "value": false
                }
            ]
        }
    ]
}'

## creacion de estudiante

curl --location 'localhost:8001/api/students' \
--header 'Content-Type: application/json' \
--data '{
    "name": "Marco",
    "age": 22,
    "city": "Neiva",
    "utc": "UTC-5"
}'
