<template>
  <div>
    <v-container>
      <v-expansion-panels multiple>
        <v-expansion-panel>
          <v-expansion-panel-header> Ingredients filtering </v-expansion-panel-header>
          <v-expansion-panel-content>
            <v-card flat>
              <v-card-title>Ingredients</v-card-title>
              <v-card-subtitle
                >Search based on your favorite ingredients!</v-card-subtitle
              >
              <v-card-text>
                <v-layout wrap>
                  <v-flex
                    v-for="(category, index) in types"
                    :key="types[index].text"
                    xs6
                    sm2
                  >
                    <v-checkbox
                      light
                      :label="category.text"
                      v-model="category.selected"
                    >
                    </v-checkbox>
                  </v-flex>
                </v-layout>
              </v-card-text>
            </v-card>
          </v-expansion-panel-content>
        </v-expansion-panel>
        <v-expansion-panel>
          <v-expansion-panel-header> Min/max nutrition values </v-expansion-panel-header>
          <v-expansion-panel-content>
            <v-row>
              <v-col cols="6">
                <v-card flat width="600">
                  <v-card-title>Filter by calories</v-card-title>
                  <v-card-subtitle
                    >Finds recipes within the calories boundaries that you
                    set.</v-card-subtitle
                  >
                  <v-card-text>
                    <v-checkbox
                      v-model="caloriesSlider.enabled"
                      :label="`Enable`"
                    ></v-checkbox>
                    <v-row>
                      <v-col class="px-4">
                        <v-range-slider
                          v-model="caloriesSlider.range"
                          :max="caloriesSlider.max"
                          :min="caloriesSlider.min"
                          :disabled="!caloriesSlider.enabled"
                          hide-details
                          class="align-center"
                        >
                          <template v-slot:prepend>
                            <v-text-field
                              :value="caloriesSlider.range[0]"
                              class="mt-0 pt-0"
                              hide-details
                              single-line
                              type="number"
                              style="width: 60px"
                              @change="$set(caloriesSlider.range, 0, $event)"
                            ></v-text-field>
                          </template>
                          <template v-slot:append>
                            <v-text-field
                              :value="caloriesSlider.range[1]"
                              class="mt-0 pt-0"
                              hide-details
                              single-line
                              type="number"
                              style="width: 60px"
                              @change="$set(caloriesSlider.range, 1, $event)"
                            ></v-text-field>
                          </template>
                        </v-range-slider>
                      </v-col>
                    </v-row>
                  </v-card-text>
                </v-card>
              </v-col>
              <v-col cols="6">
                <v-card flat width="600">
                  <v-card-title>Filter by carbs</v-card-title>
                  <v-card-subtitle
                    >Finds recipes within the carbs boundaries that you
                    set.</v-card-subtitle
                  >
                  <v-card-text>
                    <v-checkbox
                      v-model="carbsSlider.enabled"
                      :label="`Enable`"
                    ></v-checkbox>
                    <v-row>
                      <v-col class="px-4">
                        <v-range-slider
                          v-model="carbsSlider.range"
                          :max="carbsSlider.max"
                          :min="carbsSlider.min"
                          :disabled="!carbsSlider.enabled"
                          hide-details
                          class="align-center"
                        >
                          <template v-slot:prepend>
                            <v-text-field
                              :value="carbsSlider.range[0]"
                              class="mt-0 pt-0"
                              hide-details
                              single-line
                              type="number"
                              style="width: 60px"
                              @change="$set(carbsSlider.range, 0, $event)"
                            ></v-text-field>
                          </template>
                          <template v-slot:append>
                            <v-text-field
                              :value="carbsSlider.range[1]"
                              class="mt-0 pt-0"
                              hide-details
                              single-line
                              type="number"
                              style="width: 60px"
                              @change="$set(carbsSlider.range, 1, $event)"
                            ></v-text-field>
                          </template>
                        </v-range-slider>
                      </v-col>
                    </v-row>
                  </v-card-text>
                </v-card>
              </v-col>
            </v-row>

            <v-row>
              <v-col cols="6">
                <v-card flat width="600">
                  <v-card-title>Filter by protein</v-card-title>
                  <v-card-subtitle
                    >Finds recipes within the protein boundaries that you
                    set.</v-card-subtitle
                  >
                  <v-card-text>
                    <v-checkbox
                      v-model="proteinsSlider.enabled"
                      :label="`Enable`"
                    ></v-checkbox>
                    <v-row>
                      <v-col class="px-4">
                        <v-range-slider
                          v-model="proteinsSlider.range"
                          :max="proteinsSlider.max"
                          :min="proteinsSlider.min"
                          :disabled="!proteinsSlider.enabled"
                          hide-details
                          class="align-center"
                        >
                          <template v-slot:prepend>
                            <v-text-field
                              :value="proteinsSlider.range[0]"
                              class="mt-0 pt-0"
                              hide-details
                              single-line
                              type="number"
                              style="width: 60px"
                              @change="$set(proteinsSlider.range, 0, $event)"
                            ></v-text-field>
                          </template>
                          <template v-slot:append>
                            <v-text-field
                              :value="proteinsSlider.range[1]"
                              class="mt-0 pt-0"
                              hide-details
                              single-line
                              type="number"
                              style="width: 60px"
                              @change="$set(proteinsSlider.range, 1, $event)"
                            ></v-text-field>
                          </template>
                        </v-range-slider>
                      </v-col>
                    </v-row>
                  </v-card-text>
                </v-card>
              </v-col>
              <v-col cols="6">
                <v-card flat width="600">
                  <v-card-title>Filter by fat</v-card-title>
                  <v-card-subtitle
                    >Finds recipes within the fat boundaries that you
                    set.</v-card-subtitle
                  >
                  <v-card-text>
                    <v-checkbox
                      v-model="fatsSlider.enabled"
                      :label="`Enable`"
                    ></v-checkbox>
                    <v-row>
                      <v-col class="px-4">
                        <v-range-slider
                          v-model="fatsSlider.range"
                          :max="fatsSlider.max"
                          :min="fatsSlider.min"
                          :disabled="!fatsSlider.enabled"
                          hide-details
                          class="align-center"
                        >
                          <template v-slot:prepend>
                            <v-text-field
                              :value="fatsSlider.range[0]"
                              class="mt-0 pt-0"
                              hide-details
                              single-line
                              type="number"
                              style="width: 60px"
                              @change="$set(fatsSlider.range, 0, $event)"
                            ></v-text-field>
                          </template>
                          <template v-slot:append>
                            <v-text-field
                              :value="fatsSlider.range[1]"
                              class="mt-0 pt-0"
                              hide-details
                              single-line
                              type="number"
                              style="width: 60px"
                              @change="$set(fatsSlider.range, 1, $event)"
                            ></v-text-field>
                          </template>
                        </v-range-slider>
                      </v-col>
                    </v-row>
                  </v-card-text>
                </v-card>
              </v-col>
            </v-row>
          </v-expansion-panel-content>
        </v-expansion-panel>
        <v-expansion-panel>
          <v-expansion-panel-header> Sorting </v-expansion-panel-header>
          <v-expansion-panel-content>
            <v-card flat width="600">
              <v-card-title>Sort by...</v-card-title>
              <v-card-text>
                <v-select
                  v-model="sortedItem"
                  :items="sortItems"
                  label="Pick a category to sort by!"
                  :value="sortItems[0]"
                  dense
                ></v-select>
              </v-card-text>
            </v-card>
          </v-expansion-panel-content>
        </v-expansion-panel>
      </v-expansion-panels>
      <v-btn
        color="orange darken-1"
        style="margin-top: 1rem"
        @click="getRecipeList(forceStartPage = true)"
      >
        Search
      </v-btn>
      <v-btn
        color="orange darken-1"
        style="margin-top: 1rem; margin-left: 1rem"
        @click="getRecipeList"
      >
        Clear
      </v-btn>
      <v-switch
        v-if="isAuth()"
        v-model="favonly"
        :label="`Show only favorite`"
        @click="getRecipeList(forceStartPage = true)"
      ></v-switch>
      <v-row no-gutters v-if="this.list.length > 0">
        <template v-for="(recipe, index) in pageInfo.historyList">
          <v-col :key="recipe.id">
            <v-container class="pa-2">
              <v-card
                class="mx-auto"
                width="23rem"
                height="28rem"
                style="margin-top: 3rem"
              >
                <v-card-title>{{ recipe.name }}</v-card-title>
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
                    color="orange darken-1"
                    class="ma-2 white--text"
                    :to="{ path: '/viewrecipe', query: { id: recipe.id } }"
                  >
                    View
                    <v-icon right dark small> mdi-magnify </v-icon>
                  </v-btn>
                  <v-spacer></v-spacer>
                  <v-btn
                    v-if="isAuth() && !isFavorited(recipe.id)"
                    fab
                    dark
                    color="pink lighten-1"
                    small
                    @click="setFavorite(recipe.id)"
                  >
                    <v-icon>mdi-heart</v-icon>
                  </v-btn>
                  <v-btn
                    v-if="isAuth() && isFavorited(recipe.id)"
                    fab
                    dark
                    color="cyan darken-2"
                    small
                    @click="setUnfavorite(recipe.id)"
                  >
                    <v-icon>mdi-heart-broken</v-icon>
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
      <v-card v-else class="mx-auto" flat>
        <v-card-title
          >Could not find any recipe using these search
          parameters.</v-card-title
        >
        <v-card-text>
          <v-img
            src="https://cdn.discordapp.com/attachments/882918702826278942/900005348658798653/madChicken.png"
            max-height="500"
            max-width="500"
          ></v-img>
        </v-card-text>
      </v-card>
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
import {
  getIngredients,
  getAllRecipes,
  getRecipes,
} from "../service/recipeapi";
import { initPage, updatePage, pages } from "../misc/pagination";
import { postFavorite, postUnfavorite, getUserInfo } from "../service/userapi";
import { store } from "../main";
export default {
  name: "Home",
  data() {
    return {
      sortedItem: 'nothing',
      sortItems: ["nothing", "rating", "calories", "carbs", "fats", "proteins"],
      caloriesSlider: {
        name: 'calories',
        min: 0,
        max: 1000,
        range: [0, 1000],
        enabled: false,
      },
      carbsSlider: {
        name: 'carbs',
        min: 0,
        max: 100,
        range: [0, 100],
        enabled: false,
      },
      proteinsSlider: {
        name: 'proteins',
        min: 0,
        max: 100,
        range: [0, 100],
        enabled: false,
      },
      fatsSlider: {
        name: 'fats',
        min: 0,
        max: 100,
        range: [0, 100],
        enabled: false,
      },
      favonly: false,
      pageInfo: {
        historyList: [],
        page: 1,
        pageSize: 6,
        listCount: 0,
      },
      list: [],
      types: [],
    };
  },
  async created() {
    const ingredients = (await getIngredients()).data;
    ingredients.forEach((ing) => {
      this.types.push({ text: ing.name, value: ing.id, selected: false });
    });

    this.list = (await getAllRecipes()).data.recipes;
    this.initPage();
    this.updatePage(this.pageInfo.page);
  },
  methods: {
    initPage: function () {
      this.pageInfo = initPage(this.list, this.pageInfo);
    },
    updatePage: function (pageIndex) {
      this.pageInfo = updatePage(pageIndex, this.list, this.pageInfo);
    },
    getRecipeList: async function (forceStartPage = false) {
      let getFilteredTypes = this.types.filter((elem) => {
        return elem.selected;
      });
      let sliderValues = [this.caloriesSlider, this.carbsSlider, this.proteinsSlider, this.fatsSlider];
      this.list = (await getRecipes(getFilteredTypes, sliderValues, this.sortedItem)).data.recipes;
      if (this.favonly) {
        const favorites = (await getUserInfo()).data.recipes;
        this.list = this.list.filter((e) =>
          favorites.some((m) => m.id === e.id)
        );
      }
      this.initPage();
      if(forceStartPage) 
        this.updatePage(1);   
      else
        this.updatePage(this.pageInfo.page);
    },
    isFavorited: function (recipeId) {
      return store.state.savedRecipes.some((e) => e.id === recipeId);
    },
    setUnfavorite: async function (id) {
      await postUnfavorite(id)
        .then(async (response) => {
          if (response.status === 204) {
            const recipes = (await getUserInfo()).data.recipes;
            this.$store.commit("setSavedRecipes", recipes);
            this.getRecipeList();
          }
        })
        .catch((err) => {
          console.log("error occured with favorites:", err);
        });
    },
    setFavorite: async function (id) {
      await postFavorite(id)
        .then(async (response) => {
          if (response.status === 201) {
            const recipes = (await getUserInfo()).data.recipes;
            this.$store.commit("setSavedRecipes", recipes);
            this.getRecipeList();
          }
        })
        .catch((err) => {
          console.log("error occured with favorites:", err);
        });
    },
    isAuth: function () {
      return store.state.authenticated;
    }
  },
  async mounted() {},
  computed: {
    pages() {
      return pages(this.pageInfo);
    },
  },
};
</script>
