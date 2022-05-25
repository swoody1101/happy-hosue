import { apiInstance } from "./index.js";

const api = apiInstance();

function listArticle(page, keyword, success, fail) {
  api.get(`/board/list/${page}/${keyword}`).then(success).catch(fail);
}

function listNotice(success, fail) {
  api.get(`/board/notice`).then(success).catch(fail);
}

function writeArticle(article, success, fail) {
  api.post(`/board/write`, JSON.stringify(article)).then(success).catch(fail);
}

function getArticle(articleno, success, fail) {
  api.get(`/board/detail/${articleno}`).then(success).catch(fail);
}

function modifyArticle(article, success, fail) {
  api.put(`/board/modify`, JSON.stringify(article)).then(success).catch(fail);
}

function readCountUpArticle(articleno, success, fail) {
  api.put(`/board/readcount/${articleno}`).then(success).catch(fail);
}

function deleteArticle(articleno, success, fail) {
  api.delete(`/board/delete/${articleno}`).then(success).catch(fail);
}

export {
  listArticle,
  listNotice,
  writeArticle,
  getArticle,
  modifyArticle,
  readCountUpArticle,
  deleteArticle,
};
