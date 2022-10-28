import { fetchService } from "../../helpers/fetchService";

/** 
 * Загрузка Dicom на бэк
 */
//TODO multipart dicom
export const sendDicom = async (): Promise<{ dicomId: string }> => {
    const url = `/dicom`;
    const result = fetchService(url, 'POST', {});

    return result;
};

/** Получение данных **/

/** 
 * Получение списка картинок dicom
 */
export const getDicom = async (dicomId: number): Promise<{ dicomId: string, images: number[] }> => {
    const url = `/dicom/${dicomId}`;
    const result = fetchService(url);

    return result;
};
/** 
 * Получение картинки из dicom
 */
export const getDicomImage = async (dicomId: string, imageId: number) => {
    const url = `/dicom/${dicomId}/image/${imageId}`;
    const result = fetchService(url);

    return result;
};
interface Markup {
    type: string;
    geometry: number[][]
}
/** 
 * Получение картинки из dicom
 */
export const getDicomImageMarkup = async (dicomId: string, imageId: number): Promise<{ dicomId: string, imageId: number, tags: string[], markup: Markup[] }> => {
    const url = `/dicom/${dicomId}/image/${imageId}/markup`;
    const result = fetchService(url);

    return result;
};

/** Загрузка данных **/

/** 
 * Отправка разметки для картинки
 */
export const sendDicomImageMarkup = async (dicomId: string, imageId: number, body: { dicomId: string, imageId: number, tags: string[], markup: Markup[] }): Promise<{ dicomId: string, imageId: number, tags: string[], markup: Markup[] }> => {
    const url = `/dicom/${dicomId}/image/${imageId}/markup`;
    //todo json stringify
    const result = fetchService(url, 'POST', JSON.stringify(body));

    return result;
};

//todo вынести интерфейсы