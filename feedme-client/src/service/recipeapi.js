const axios = require('axios').default;

import { store } from '../main.js'

const apiRoot = "http://localhost:3000";

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
    console.log('the param:',urlparam);
    return axios.get(`${apiRoot}/recipes?${urlparam}`)
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