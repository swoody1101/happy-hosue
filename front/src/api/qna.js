import { apiInstance } from "./index.js";
const api = apiInstance();

function listQnA(page, success, fail) {
  await api.get(`/qna`, { params: page }).then(success).catch(fail);
}

function writeQnA(article, success, fail) {
await api.post(`/qna`, JSON.stringify(article)).then(success).catch(fail);
}

function getQnA(articleno, success, fail) {
  await api.get(`/qna/${articleno}`).then(success).catch(fail);
}

function modifyQnA(article, success, fail) {
  await api
    .put(`/qna/${article.articleno}`, JSON.stringify(article))
    .then(success)
    .catch(fail);
}

function deleteQnA(articleno, success, fail) {
  await api.delete(`/qna/${articleno}`).then(success).catch(fail);
}

export { listQnA, writeQnA, getQnA, modifyQnA, deleteQnA };
