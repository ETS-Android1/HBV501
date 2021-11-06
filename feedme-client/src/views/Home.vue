<template>
  <div>   
  <v-container>
    <v-expansion-panels>
    <v-expansion-panel
    >
      <v-expansion-panel-header>
        Filter / sort
      </v-expansion-panel-header>
      <v-expansion-panel-content>
      <v-card flat>
        <v-card-title>Ingredients</v-card-title>
        <v-card-subtitle>Search based on your favorite ingredients!</v-card-subtitle>
        <v-card-text>
        <v-layout wrap>
                <v-flex v-for="(category,index) in types" :key="types[index].text" xs6 sm2>
                  <v-checkbox light :label="category.text" v-model="category.selected">
                  </v-checkbox>
                </v-flex>
        </v-layout>
        </v-card-text>
      </v-card>
      <v-card flat>
          <v-card-title>Nutritional values</v-card-title>
          <v-card-subtitle>If you need to be careful with your calories intake!</v-card-subtitle>
          <v-card-text>
            <form
            width="300"
            >
              <v-range-slider
                hint="Calories"
                max="0"
                min="100"
              ></v-range-slider>
            </form>
          </v-card-text>
      </v-card>
      </v-expansion-panel-content>
    </v-expansion-panel>
    </v-expansion-panels>
    <v-row no-gutters>
      <template v-for="(recipe,index) in pageInfo.historyList">
        <v-col :key="recipe.id">
          <v-container
            class="pa-2"
          >
          <v-card
        class="mx-auto"
        width="22rem"
        height="30rem"
        style="margin-top:3rem"
        >
        <v-card-title>{{recipe.name}}</v-card-title>
        <v-card-subtitle>{{setSubtitle(recipe.description)}}</v-card-subtitle>
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
                <div class="text-center mt-12">
                <v-rating
                  v-model="recipe.rating"
                  color="yellow darken-3"
                  background-color="grey darken-1"
                  empty-icon="$ratingFull"
                  half-increments
                  readonly
                ></v-rating>
                <p v-if="recipe.rating < 0">not rated yet</p>
                <p v-else><br /></p>
                </div>
        </v-card-text>
        <v-divider></v-divider>
         <v-card-actions>
           <v-btn
                color="orange"
                text
                :to="{path:'/viewrecipe',query:{id: recipe.id}}"
            >
                View
            </v-btn>
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
          v-if="index === 2"
          :key="`width-${index}`"
          width="100%"
        ></v-responsive>
      </template>
    </v-row>
    <v-pagination
        v-model="pageInfo.page" 
        class="my-4"
        circle
        :length="pages"
        @input="updatePage"
    ></v-pagination>
  </v-container>
  </div>
</template>

<script>
import { getIngredients, getRecipes } from "../service/recipeapi";
import { initPage, updatePage, pages} from "../misc/pagination";
  export default {
    name: 'Home',
    data() {
      return {
        pageInfo: {
				historyList: [],
				page: 1,
				pageSize: 6,
				listCount: 0
			},
        list: [],
        types: []
      }
    },
    async created() {
      const ingredients = (await getIngredients()).data;
      ingredients.forEach(ing => {
        this.types.push({ text: ing.name, value: ing.id , selected : false  })
      });     

      this.list = (await getRecipes()).data.recipes;
		this.initPage();
		this.updatePage(this.pageInfo.page);
	},
    methods: {
      initPage: function() {
        this.pageInfo = initPage(this.list, this.pageInfo);
		},
		updatePage: function(pageIndex) {
			this.pageInfo = updatePage(pageIndex, this.list, this.pageInfo);
		},
      setSubtitle: function(description) {
        if(description.length <= 49) {
          let spaceToAdd = (49 - description.length)*3;
          let buffer = "​ ".repeat(spaceToAdd);
          description = description + buffer;
        }
        else if(description.length >= 98) {
          description = description.substring(0, 98);
          description += '...'
        }
        return description;
      }
    },
    async mounted() {

    },
    computed: {
      pages() {
			return pages(this.pageInfo);
		}
    }
  }
</script>
