<template>
    <v-card
        class="mx-auto"
        max-width="20rem"
        style="margin-top:3rem"
    >
        <v-card-title>{{recipe.name}}</v-card-title>
        <v-card-subtitle>{{recipe.description}}</v-card-subtitle>
        <v-card-text>
            <v-list dense>
                <v-list-item>
                  <v-list-item-content>Calories:</v-list-item-content>
                  <v-list-item-content class="align-end">
                    {{ recipe.calories }}
                  </v-list-item-content>
                </v-list-item>
                <v-list-item>
                  <v-list-item-content>Fat:</v-list-item-content>
                  <v-list-item-content class="align-end">
                    {{ recipe.fats }}
                  </v-list-item-content>
                </v-list-item>

                <v-list-item>
                  <v-list-item-content>Carbs:</v-list-item-content>
                  <v-list-item-content class="align-end">
                    {{ recipe.carbs }}
                  </v-list-item-content>
                </v-list-item>

                <v-list-item>
                  <v-list-item-content>Protein:</v-list-item-content>
                  <v-list-item-content class="align-end">
                    {{ recipe.proteins }}
                  </v-list-item-content>
                </v-list-item>
            </v-list>
        </v-card-text>
         <v-card-actions>
            <v-btn
                color="orange"
                text
            >
                Favorite
            </v-btn>

            <v-btn
                color="orange"
                text
            >
                
            </v-btn>
        </v-card-actions>
    </v-card>
</template>

<script>
export default ({
    name: 'ViewRecipe',
    data() {
        return {
            recipe: {
                id: 1,
                name: "Chicken and Broccoly Stir-Fry",
                description: "Bla",
                instructions: "Do things",
                calories: 468.0,
                carbs: 17.2,
                proteins: 38.9,
                fats: 27.2,
                image: "Get image",
                ingredients: [
                    {
                        id: 1,
                        name: "chicken"
                    },
                    {
                        id: 2,
                        name: "beans"
                    },
                    {
                        id: 3,
                        name: "catnip"
                    },
                    {
                        id: 4, 
                        name: "milk"
                    }
                ],
                comments: [
                    {
                        id: 1,
                        body: "Dry"
                    }
                ]
            }
        }
    },
    methods: {

    }
})
</script>
