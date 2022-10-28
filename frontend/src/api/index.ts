

import * as dicom from './dicom';


const api = {
  dicom,
};
export type IApi = typeof api;
export default api as IApi;
