import { apiInstance } from "./index.js";

const api = apiInstance();

function listComment(bno, page, success, fail) {
  api.get(`/comment/list/${bno}/${page}`).then(success).catch(fail);
}

function writeComment(cno, comment, success, fail) {
  api
    .post(`/comment/write/${cno}`, JSON.stringify(comment))
    .then(success)
    .catch(fail);
}

function modifyArticle(comment, success, fail) {
  api.put(`/comment/modify`, JSON.stringify(comment)).then(success).catch(fail);
}

function deleteComment(cno, success, fail) {
  api.delete(`/comment/delete/${cno}`).then(success).catch(fail);
}

export { listComment, writeComment, modifyArticle, deleteComment };
