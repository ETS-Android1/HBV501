import { store } from '../main.js'

const axios = require('axios').default;

const apiRoot = "http://localhost:3000/users";

function getConfig() {
    return {
        headers: {
            Authorization: `Bearer ${store.state.token}`
        }
    };
}

async function postRegister(info) {
    return axios.post(`${apiRoot}/register`, info);
}

async function postLogin(info) {
    return axios.post(`${apiRoot}/login`, info);
}

async function patchUser(info) {
    return axios.patch(`${apiRoot}/me`, info, getConfig());
}

async function postFavorite(recipeId) {
    return axios.post(`${apiRoot}/me/recipes/${recipeId}`, recipeId, getConfig())
}

async function postUnfavorite(recipeId) {
    return axios.delete(`${apiRoot}/me/recipes/${recipeId}`, getConfig())
}

async function getUserInfo() {
    return axios.get(`${apiRoot}/me`, getConfig());
}

export { getUserInfo, postRegister, postLogin, postFavorite, postUnfavorite, patchUser}