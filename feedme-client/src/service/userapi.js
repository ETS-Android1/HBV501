const axios = require('axios').default;

const apiRoot = "http://localhost:3000/users";

async function postRegister(info) {
    return axios.post(`${apiRoot}/register`, info);
}

async function postLogin(info) {
    return axios.post(`${apiRoot}/login`, info);
}

async function getUserInfo(token) {
    const cfg = {
        headers: {
           Authorization: "Bearer " + token
        }
     }
    return axios.get(`${apiRoot}/me`, cfg);
}

module.exports = {
    postRegister,
    postLogin,
    getUserInfo
}