const axios = require('axios').default;

import { store } from '../main.js'

const apiRoot = "http://localhost:3000";

async function getRecipes() {
    return axios.get(`${apiRoot}/recipes`);
}

async function getRecipeById(id) {
    return axios.get(`${apiRoot}/recipes/${id}`);
}

async function postReview(id, review) {
    const cfg = {
        headers: {
           Authorization: `Bearer ${store.state.token}`
        }
     };
    return axios.post(`${apiRoot}/recipes/${id}/reviews`, review, cfg);
}

async function deleteReview(userId, recipeId) {
    const cfg = {
        headers: {
           Authorization: `Bearer ${store.state.token}`
        }
    };
    return axios.delete(`${apiRoot}/recipes/${recipeId}/reviews/${userId}`, cfg);
}

async function getIngredients() {
    return axios.get(`${apiRoot}/ingredients`);
}

export { getRecipes, getIngredients, getRecipeById, postReview, deleteReview }