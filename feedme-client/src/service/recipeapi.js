const axios = require('axios').default;

import { store } from '../main.js'

const apiRoot = "http://localhost:3000";

async function getAllRecipes() {
    return axios.get(`${apiRoot}/recipes`);
}

async function getRecipes(ingredients) {
    let ingValues = ingredients.map(e => e.value).join(",");
    return axios.get(`${apiRoot}/recipes?ingredients=${ingValues}`)
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

export { getRecipes, getAllRecipes, getIngredients, getRecipeById, postReview, deleteReview }