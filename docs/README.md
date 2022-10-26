# Документация проекта

## API

### Загрузка DICOM файла

*Метод*: `POST`

*Путь*: `/dicom`

| Parameter | Description |
| :----------- | :--------------- |
| file | DICOM файл в формате Multipart файл |

Ответ:


```json
{
    "dicom-id": "String"
}
```

### Данные DICOM

Запрос возвращает поля из DICOM файла с указанным идентификатором. Медиа не возвращается. Вместо медиа возвращаются идентификаторы, используя которые можно получить данные отдельными запросами.

*Метод*: `GET`

*Путь*: `/dicom/{dicom-id}`

Параметры:

| Parameter | Description |
| :----------- | :--------------- |
| dicom-id | Идентификатор файла|

Ответ:

```json
{
    "dicom-id": "String",
    "images": ["Integer"],
}
```

### Изображение

Запрос возвращает из DICOM файла с идентификатором {dicom-id} изображение с идентификатором {image-id}

*Метод*: `GET`

*Путь*: `/dicom/{dicom-id}/image/{image-id}`

| Parameter | Description |
| :----------- | :--------------- |
| dicom-id | Идентификатор файла |
| image-id | Идентификатор изображения |

Ответ: изображение

### Получение разметки для изображения

Запрос возвращает набор примитивов разметки для изображения {image-id} DICOM файла {dicom-id}

*Метод*: `GET`

*Путь*:  `/dicom/{dicom-id}/image/{image-id}/markup`

| Parameter | Description |
| :----------- | :--------------- |
| dicom-id | инедтификатор файла |
| image-id | Идентификатор изображения |

Ответ:

```json
{
    "dicom-id": "String",
    "image-id": "Integer",
    "tags": ["String"],
    "markup": [
        {
            "type": "String",
            "geometry": [["Float", "Float"]],
        }
    ]
}
```

### Сохранение разметки для изображения

Запрос возвращает набор примитивов разметки для изображения из DICOM файла 

*Метод*: `POST`

*Путь*:  `/dicom/{dicom-id}/image/{image-id}/markup`

| Parameter | Description |
| :----------- | :--------------- |
| dicom-id | инедтификатор файла |
| image-id | Идентификатор изображения |
| tags | Список тегов |
| type | Тип примитива |
| geometry | Список точек примитива |

Тело запроса:

```json
{
    "tags": ["String"],
    "markup": [
        {
            "type": "String",
            "geometry": [["Float", "Float"]],
        }
    ]
}
```

Ответ:

```json
{
    "dicom-id": "String",
    "image-id": "Integer",
    "tags": ["String"],
    "markup": [
        {
            "type": "String",
            "geometry": [["Float", "Float"]],
        }
    ]
}
```
