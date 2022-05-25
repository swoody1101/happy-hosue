import { apiInstance, houseInstance } from "./index.js";

const api = apiInstance();
const house = houseInstance();

function sidoList(success, fail) {
  api.get(`/apt/sidoCode`).then(success).catch(fail);
}

function gugunList(params, success, fail) {
  api.get(`/apt/gugunCode`, { params: params }).then(success).catch(fail);
}

function dongList(params, success, fail) {
  api.get(`/apt/dongCode`, { params: params }).then(success).catch(fail);
}

function dealList(params, success, fail) {
  api.get(`/apt/dealInfo`, { params: params }).then(success).catch(fail);
}

function houseList(params, success, fail) {
  house.get(``, { params: params }).then(success).catch(fail);
}

export { sidoList, gugunList, dongList, dealList, houseList };
