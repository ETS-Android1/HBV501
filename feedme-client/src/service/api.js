const axios = require('axios').default;

const apiRoot = "http://localhost:3000";

async function getRecipes() {
    return axios.get(`${apiRoot}/recipes`);
}

async function getIngredients() {
    return axios.get(`${apiRoot}/ingredients`);
}

module.exports = {
    getRecipes,
    getIngredients
}