const axios = require('axios').default;

import { store } from '../main.js'

const apiRoot = "http://localhost:3000";

function getConfig() {
    return {
        headers: {
            Authorization: `Bearer ${store.state.token}`
        }
    };
}

function andParam(param, addition) {
    if(param.endsWith('&') || param.length <= 0){
        param += addition;
    } else {
        param += "&"
        param += addition;
    }
    return param;
}

async function getAllRecipes() {
    return axios.get(`${apiRoot}/recipes`);
}

async function getRecipes(ingredients, minmaxlist, sortType) {
    let urlparam = '';
    let ingValues = ingredients.map(e => e.value).join(",");
    if(ingValues.length > 0)
        urlparam += `ingredients=${ingValues}`
    minmaxlist.forEach(e => {
        if(e.enabled) 
            urlparam = andParam(urlparam, `min${e.name}=${e.range[0]}&max${e.name}=${e.range[1]}`);
    });
    if(sortType !== 'nothing' || sortType == undefined) {
        urlparam = andParam(urlparam, `sort=${sortType}`);
    }
    return axios.get(`${apiRoot}/recipes?${urlparam}`)
}

async function getRecipeById(id) {
    return axios.get(`${apiRoot}/recipes/${id}`);
}

async function postReview(id, review) {
    return axios.post(`${apiRoot}/recipes/${id}/reviews`, review, getConfig());
}

async function patchReview(recipeId, userId, updated) {
    return axios.patch(`${apiRoot}/recipes/${recipeId}/reviews/${userId}`, updated, getConfig());
}

async function deleteReview(userId, recipeId) {
    return axios.delete(`${apiRoot}/recipes/${recipeId}/reviews/${userId}`, getConfig());
}

async function getIngredients() {
    return axios.get(`${apiRoot}/ingredients`);
}

export { getRecipes, getAllRecipes, getIngredients, getRecipeById, postReview, deleteReview, patchReview }