const axios = require('axios').default;

const apiRoot = "http://localhost:3000";

async function getRecipes() {
    return axios.get(`${apiRoot}/recipes`);
}

module.exports = {
    getRecipes
}