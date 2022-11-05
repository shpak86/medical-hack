type httpMethod = "POST" | "GET";

interface IRequestConfig {
  method: httpMethod;
  credentials?: "include";
  body?: any;
}

export const fetchService = async (
  url: string,
  method: httpMethod = "GET",
  body?: any
): Promise<any> => {
  const requestConfig: IRequestConfig = {
    method,
    body,
    credentials: "include",
  };

  const request = await fetch(url, requestConfig)
    .then((res) => {
      if (!res.ok) {
        if (res.status === 401) {
          throw Error("Неавторизован");
        } else if (res.status === 403) {
          throw Error("Недостаточно прав");
        } else if (res.status === 404) {
          throw Error("Нет данных");
        } else if (
          (res.status > 400 && res.status < 404) ||
          (res.status > 405 && res.status < 500)
        ) {
          throw Error(`Что-то пошло не так, код ответа: ${res.status}`);
        } else if (res.status > 499) {
          throw Error("Ошибка сервера!");
        }
      }
      return res.json();
    })
    .catch((err) => {
      return Promise.reject(err);
    });

  return request;
};
