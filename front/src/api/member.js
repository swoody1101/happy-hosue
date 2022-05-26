import { apiInstance } from "./index.js";

const api = apiInstance();

async function login(member, success, fail) {
  await api
    .post(`/user/login`, JSON.stringify(member))
    .then(success)
    .catch(fail);
}

async function join(user, success, fail) {
  await api.post(`/user/join`, JSON.stringify(user)).then(success).catch(fail);
}

async function idCheck(id, success, fail) {
  await api.post(`/user/idCheck`, JSON.stringify(id)).then(success).catch(fail);
}

async function findById(memberid, success, fail) {
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  await api.get(`/user/info/${memberid}`).then(success).catch(fail);
}

async function sendEmailCode(memberEmail, emailAddress, success, fail) {
  await api
    .post(`/mail/send?id=${memberEmail}&address=${emailAddress}`)
    .then(success)
    .catch(fail);
}

async function secession(user, success, fail) {
  await api
    .delete(`/user/secession`, JSON.stringify(user))
    .then(success)
    .catch(fail);
}

export { login, findById, sendEmailCode, join, idCheck, secession };
