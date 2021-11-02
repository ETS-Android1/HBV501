<template>
    <h3>Hello</h3>
</template>

<script>
import { getRecipeById } from "../service/api";


export default ({
    name: 'ViewRecipe',
    data() {
        return {
           recipe: {}
        }
    },
    methods: {

    },
    async mounted() {
        const recipeid = parseInt(this.$route.params.id);
        console.log(recipeid);
        const isrecipe = (await getRecipeById(recipeid)).data;
        if(isrecipe)
            this.recipe = isrecipe;
    }
})
</script>
