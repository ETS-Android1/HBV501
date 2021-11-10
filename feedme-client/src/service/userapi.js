import { store } from '../main.js'

const axios = require('axios').default;

const apiRoot = "http://localhost:3000/users";

async function postRegister(info) {
    return axios.post(`${apiRoot}/register`, info);
}

async function postLogin(info) {
    return axios.post(`${apiRoot}/login`, info);
}

async function postFavorite(recipeId) {
    const cfg = {
        headers: {
           Authorization: `Bearer ${store.state.token}`
        }
     };
    return axios.post(`${apiRoot}/me/recipes/${recipeId}`, recipeId, cfg)
}

async function postUnfavorite(recipeId) {
    const cfg = {
        headers: {
           Authorization: `Bearer ${store.state.token}`
        }
     };
    return axios.delete(`${apiRoot}/me/recipes/${recipeId}`, cfg)
}

async function getUserInfo() {
    const cfg = {
        headers: {
           Authorization: `Bearer ${store.state.token}`
        }
     };
    return axios.get(`${apiRoot}/me`, cfg);
}

export { getUserInfo, postRegister, postLogin, postFavorite, postUnfavorite }