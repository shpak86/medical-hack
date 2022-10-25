# Документация проекта

## API

### Загрузка DICOM файла

Метод: POST
Путь:  /data
Параметры:
- file  -   Multipart файл

Ответ:

- id Идентификатор файла

### Данные DICOM

Метод: GET
Путь:  /data/{id}
Параметры:

- id Идентификатор файла

Ответ:

```json
{
    id: "String",
    images: ["Integer"],
}
```

### Изображение

Метод: GET
Путь:  /data/{id}/image/{image}
Параметры:

- id Идентификатор файла
- image Идентификатор изображения

Ответ: изображение

### Получение разметки

Метод: GET
Путь:  /data/{id}/markup
Параметры:

- id инедтификатор файла

Ответ:

```json
{
    id: "String",
    markup: [
        {
            id: "String",
            tags: "String",
            type: "String",
            geometry: [["Float", "Float"]],
        }
    ]
}
```