import { apiInstance } from "./index.js";
const api = apiInstance();

async function selectOne(qna_no, success, fail) {
  await api.get(`/qna/detail/${qna_no}`).then(success).catch(fail);
}

async function selectGroup(group_no, success, fail) {
  await api.get(`/qna/detail/list/${group_no}`).then(success).catch(fail);
}

async function getGroupNo(qna_no, success, fail) {
  await api.get(`/qna/group/${qna_no}`).then(success).catch(fail);
}

export { selectOne, selectGroup, getGroupNo };
