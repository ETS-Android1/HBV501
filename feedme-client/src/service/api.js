const axios = require('axios').default;

const apiRoot = "http://localhost:3000";

async function getRecipes() {
    return axios.get(`${apiRoot}/recipes`);
}

async function getRecipeById(id) {
    return axios.get(`${apiRoot}/recipes/${id}`);
}

async function getIngredients() {
    return axios.get(`${apiRoot}/ingredients`);
}

async function postRegister(info) {
    return axios.post(`${apiRoot}/users/register`, info);
}

module.exports = {
    getRecipes,
    getIngredients,
    getRecipeById,
    postRegister
}