# PruebaTecnicaFonYou

## Configuracion del proyecto

### Base de datos: PostgreSQL

-- Database: ratings

-- DROP DATABASE IF EXISTS ratings;

CREATE DATABASE ratings
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Spanish_Colombia.1252'
    LC_CTYPE = 'Spanish_Colombia.1252'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;

en este ejemplo se uso el usuario postgres y password 0000

consta de 5 tablas: answer, exam, exam_student_report, question, student.
las cuales seran creadasn con sus respectivos campos al momento de ejecutar el proyecto.

![](https://s3.hedgedoc.org/demo/uploads/a09ba35a-84dd-433d-958b-7e7680588d76.png)


### Enpoints disponibles

**1**. se crearan estudiantes utilizando la siguiente API.

> localhost:8001/api/students (**POST**)
#### body:

> {
>     "name": "Pedro",
>     "age": 20,
>     "city": "Buenos Aires",
>     "utc": "UTC-3"
> }

![](https://s3.hedgedoc.org/demo/uploads/1f4c58ec-1b68-4c32-a110-8524c25b29c4.png)

la zona horaria debe ingresarse dependiendo del código UTC, para este ejemplo se usaron UTC-5 y UTC-3


**2** . se crearan examenes con el siguiente API.
> localhost:8001/api/exam (**POST**)
#### body:

se agrega el nombre del examen y una lista de preguntas con sus respectivas opciones: 

questionDtoList se ingresa el nombre de la pregunta (**questionName**) y dentro un arreglo de opciones (**answer**), cada answer tiene un campo **value** este es un campo tipo Boolean: se marca con true la opcion que sea correcta y false las incorrectas.

    {   
    "name": "Exam 1",
    "questionDtoList": [
        {
            "questionName": "Cuanto es 2+2",
            "answer": [
                {
                    "name": "3",
                    "value": false
                },
                {
                    "name": "1",
                    "value": false
                },
                {
                    "name": "5",
                    "value": false
                },
                {
                    "name": "4",
                    "value": true
                }
            }
        },{
            "questionName": "¿Qué es un bucle en programación?",
            "answer": [
                {
                    "name": "Una estructura condicional.",
                    "value": false
                },
                {
                    "name": "Una función que se ejecuta solo una vez.",
                    "value": false
                },
                {
                    "name": "Una estructura que permite ejecutar un bloque de código repetidamente.",
                    "value": true
                },
                {
                    "name": "Un tipo de dato numérico.",
                    "value": false
                }
            ]
        }
      ]
    }

![](https://s3.hedgedoc.org/demo/uploads/4fc596fd-10aa-497c-9f2b-3145dc6a5ca7.png)

Exam:
![](https://s3.hedgedoc.org/demo/uploads/eefc182a-c757-4c4f-b4b2-74ed27637e54.png)

Question:
![](https://s3.hedgedoc.org/demo/uploads/904b44a5-ce65-4b7f-9e51-cb4513b343da.png)

Answer:
![](https://s3.hedgedoc.org/demo/uploads/03fb6807-5fd5-4487-a7c5-e82a5e19cc51.png)

**3** . se asigna el examen a un estudiante con la siguiente API:

> localhost:8001/api/students/assign_exam_to_student (**POST**)

se asigna el examen con id 1 al estudiante con id 1 "Pedro". Tambien se le asignara al estudiante con id 2 "Marco" del mismo modo.
#### body:
> 
```
{
    "studentId": 1,
    "examId": 2,
    "presentationDate": "2023-08-20T10:56:00Z"
}
```
![](https://s3.hedgedoc.org/demo/uploads/2ce595b2-b003-4daa-9b2c-073181adb18f.png)

en la respuesta se ve:

```
{
    "id": 2, // id del registro creado.
    "studentId": 1,v// id del estudiante.
    "examId": 1, // id del examen
    "examJson": null, // campo que por ahora no sera usado.
    "presentationDate": "2023-08-20T12:56:00Z", // hora de presentacion del examen
    "result": 0.0, // resultado al momento de ser creado en 0
    "status": true // estado activo
}
```
Como se puede observar la fecha de presentacion del examen fue a las **10:56:00** Hora Bogota, pero debido a que el estudiante pertenece a la zona horaria UTC-3 de argentina, este se programa a su hora segun la zona correspondiente **12:56:00**

(**NOTA**): puedes listar los estudiantes creados con la siguiente API:
localhost:8001/api/students (**GET**)

![](https://s3.hedgedoc.org/demo/uploads/7747b39b-c6cb-41f6-9a39-290399508a02.png)

**4**. el estudiante presentara el examen y enviara las respuestas con la siguiente API:
> localhost:8001/api/exam/exam_student (**POST**)
#### body:
```
{
    "examId": 1,
    "studentId": 1,
    "questionDtoList": [
        {
            "id": 1,
            "questionName": "Cuanto es 2+2",
            "answer": [
                {
                    "id": 2,
                    "name": "1",
                    "value": true
                }
            ]
        },
        {
            "id": 2,
            "questionName": "¿Qué es un bucle en programación?",
            "answer": [
                 {
                    "id": 7,
                    "name": "Una estructura que permite ejecutar un bloque de código repetidamente.",
                    "value": true
                }
            ]
        }
    ]
}
```

El examen sera calificado y debido a que el total de las preguntas debe dar un 100%, en este examen el estudiante acerto una pregunta solamente en resultado seria un 50%.

![](https://s3.hedgedoc.org/demo/uploads/d13f1cd1-a745-45a2-bfaa-764f752718ed.png)
 
 en el momento en que se presenta el examen se registran las respuestas y el puntaje, el **status** pasa a ser **false** lo que quiere decir que no podra ser presentado de nuevo.
 
 ![](https://s3.hedgedoc.org/demo/uploads/885188b3-cd84-43bb-be8d-3dedeaa983ae.png)

 respuesta:
![](https://s3.hedgedoc.org/demo/uploads/6cadc824-de56-4adc-bd8c-ddd009ad3788.png)

**5**. Puedes recopilar las respuestas de un examen que ya presento el estudiante con la siguiente API:
> localhost:8001/api/exam/get_exam_student_answer/{**studentId**}/{**examId**} (**GET**)

![](https://s3.hedgedoc.org/demo/uploads/a7d926a0-756d-4505-be7e-202efeec08dc.png)

(**NOTA**) puedes ver la lista de examenes creados con la siguiente API:
> localhost:8001/api/exam (**GET**)

![](https://s3.hedgedoc.org/demo/uploads/14fe87c9-dfb5-4b8a-88d5-968bbb4605bb.png)

(**NOTA**) Puedes ver los examenes que tiene asignado un estudiante con la siguiente API recibe studentId como parametro:

![](https://s3.hedgedoc.org/demo/uploads/dd5d71e5-cdf2-4857-ae46-e07e64dc83a1.png)

> localhost:8001/api/exam/{**studentId**} (**GET**)

(**NOTA**) Puede ver el detalle de un examen asignado a un estudiante con la siguiente
API la cual recibe como parametro el studentId y el examId:

> localhost:8001/api/exam/{**studentId**}/{**examId**} (**GET**)

Mas informacion acerca de los endpoint (curl) estara disponible en el archivo [Fonyou.postman_collection]([https://](https://github.com/fabiansiza994/PruebaTecnicaFonYou/blob/master/Fonyou.postman_collection.json)https://github.com/fabiansiza994/PruebaTecnicaFonYou/blob/master/Fonyou.postman_collection.json)
