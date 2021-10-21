<template>
  <div>   
  <v-container>
    <v-expansion-panels>
    <v-expansion-panel
    >
      <v-expansion-panel-header>
        Filtering
      </v-expansion-panel-header>
      <v-expansion-panel-content>
        <v-layout wrap>
                <v-flex v-for="(category,index) in types" :key="types[index].text" xs6 sm2>
                  <v-checkbox light :label="category.text" v-model="category.selected">
                  </v-checkbox>
                </v-flex>
        </v-layout>
      </v-expansion-panel-content>
    </v-expansion-panel>
    </v-expansion-panels>
    <v-row no-gutters>
      <template v-for="n in 6">
        <v-col :key="n">
          <v-container
            class="pa-2"

          >
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
            </v-card-actions>
          </v-card>
          </v-container>
        </v-col>
        <v-responsive
          v-if="n === 3"
          :key="`width-${n}`"
          width="100%"
        ></v-responsive>
      </template>
    </v-row>
  </v-container>
  </div>
</template>

<script>
import { getIngredients } from "../service/api";
  export default {
    name: 'Home',
    data() {
      return {
        recipes: [],
        ex4: ['t1', 't2'],
        types: [],
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
      getIngr() {
        getIngredients();
      }
    },
    async mounted() {
      const ingredients = (await getIngredients()).data;
      ingredients.forEach(ing => {
        this.types.push({ text: ing.name, value: ing.id , selected : false  })
      });
    }
  }
</script>
