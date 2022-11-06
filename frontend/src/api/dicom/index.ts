import { fetchService } from "../../helpers/fetchService";

/**
 * Загрузка Dicom на бэк
 */
//TODO multipart dicom
export const sendDicom = async (
  file: string | Blob
): Promise<{ dicomId: number }> => {
  const url = `/dicom/`;
  const formData = new FormData();
  formData.append("file", file);
  const result = fetchService(url, "POST", formData);
  return result;
};

/** Получение данных **/

/**
 * Получение списка картинок dicom
 */
export const getDicom = async (
  dicomId: number
): Promise<{ dicomId: number; imagesNumber: number; modality: string }> => {
  const url = `/dicom/${dicomId}/fields`;
  const result = fetchService(url);

  return result;
};
/**
 * Получение картинки из dicom
 */
export const getDicomImage = async (dicomId: number, imageId: number) => {
  const url = `/dicom/${dicomId}/image/${imageId}`;
  const result = fetchService(url);
  return result;
};
interface Markup {
  type: string;
  geometry: number[][];
}
export interface DicomImageMarkup {
  dicomId: string;
  imageId: number;
  tags: string[];
  markup: Markup[];
}

/**
 * Получение картинки из dicom
 */
export const getDicomImageMarkup = async (
  dicomId: number,
  imageId: number
): Promise<{
  dicomId: string;
  imageId: number;
  tags: string[];
  markup: Markup[];
}> => {
  const url = `/dicom/${dicomId}/image/${imageId}/markup`;
  const result = fetchService(url);

  return result;
};

/** Загрузка данных **/

export interface SendMarkup {
  dicomId: string;
  imageId: number;
  tags: string[];
  markup: Markup[];
}

/**
 * Отправка разметки для картинки
 */
export const sendDicomImageMarkup = async (
  dicomId: string,
  imageId: number,
  body: SendMarkup
): Promise<{
  dicomId: string;
  imageId: number;
  tags: string[];
  markup: Markup[];
}> => {
  const url = `/dicom/${dicomId}/image/${imageId}/markup`;
  const result = fetchService(url, "POST", JSON.stringify(body), {
    'Accept': 'application/json',
    'Content-Type': 'application/json'
  },);
  return result;
};

//todo вынести интерфейсы
