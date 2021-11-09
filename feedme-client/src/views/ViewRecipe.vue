<template>
  <div>
    <v-dialog
      v-model="dialog"
      fullscreen
      hide-overlay
      transition="dialog-top-transition"
    >
      <v-card>
        <v-toolbar color="orange darken-1">
          <v-btn icon dark v-on:click="dialog = false">
            <v-icon>mdi-close</v-icon>
          </v-btn>
          <v-toolbar-title style="color: white"
            >Write a review for <b>{{ recipe.name }}</b></v-toolbar-title
          >
          <v-spacer></v-spacer>
          <v-toolbar-items>
            <v-btn
              dark
              text
              v-on:click="
                dialog = false;
                addReview();
              "
            >
              Post
            </v-btn>
          </v-toolbar-items>
        </v-toolbar>
        <v-container>
          <v-row>
            <v-col cols="12">
              <v-text-field
                label="Title"
                :value="selectedItem.title"
                v-model="selectedItem.title"
                required
              ></v-text-field>
            </v-col>
            <v-col cols="12">
              <v-textarea
                counter
                label="Text"
                :value="selectedItem.subtitle"
                v-model="selectedItem.subtitle"
              ></v-textarea>
            </v-col>
            <v-col cols="12">
              Rate the recipe
              <v-rating
                v-model="selectedItem.rating"
                color="yellow darken-3"
                background-color="grey darken-1"
                empty-icon="$ratingFull"
                half-increments
                hover
                large
              ></v-rating>
            </v-col>
          </v-row>
        </v-container>
      </v-card>
    </v-dialog>
    <v-container fluid>
      <v-btn fab light small @click="$router.go(-1)">
        <v-icon>mdi-arrow-left</v-icon>
      </v-btn>
      <v-card style="margin-top: 1rem">
        <v-toolbar class="mb-2" color="orange darken-3" dark flat>
          <v-toolbar-title>
            {{ recipe.name }}
          </v-toolbar-title>
        </v-toolbar>
        <v-container fluid>
          <v-rating
            v-model="recipe.rating"
            color="yellow darken-3"
            background-color="grey darken-1"
            empty-icon="$ratingFull"
            half-increments
            readonly
          ></v-rating>
          <v-row>
            <v-col cols="6">
              <v-card flat>
                <v-card-title>
                  Ingredients
                  <v-chip class="ma-2" color="green" text-color="white">
                    {{ recipe.ingredients.length }}
                  </v-chip>
                </v-card-title>
                <v-card-subtitle
                  >List of the ingredients in the recipe</v-card-subtitle
                >
                <v-card-text>
                  <v-simple-table fixed-header height="300px">
                    <template v-slot:default>
                      <thead>
                        <tr>
                          <th class="text-left">Name</th>
                          <th class="text-left">Amount</th>
                        </tr>
                      </thead>
                      <tbody>
                        <tr
                          v-for="item in recipe.ingredients"
                          :key="item.ingredient.id"
                        >
                          <td>{{ item.ingredient.name }}</td>
                          <td>{{ item.quantity }} {{ item.unit }}</td>
                        </tr>
                      </tbody>
                    </template>
                  </v-simple-table>
                </v-card-text>
              </v-card>
            </v-col>
            <v-col cols="6">
              <v-card class="mx-auto" flat>
                <v-card-title>Nutritional value</v-card-title>
                <v-card-subtitle
                  >The total amount of nutritions in the recipe</v-card-subtitle
                >
                <v-list>
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
              </v-card>
            </v-col>
          </v-row>
          <v-card flat width="80%">
            <v-card-title>Instructions</v-card-title>
            <v-card-subtitle
              >A guide from the author to ensure your success</v-card-subtitle
            >
            <v-card-text>
              <p class="text--primary">{{ recipe.description }}</p>
            </v-card-text>
          </v-card>
          <v-card flat>
            <v-card-title>Reviews</v-card-title>
            <v-card-subtitle>Feedback from our users</v-card-subtitle>
            <v-card-text>
              <v-list v-if="reviewList.length > 0" three-line>
                <template v-for="(item, index) in pageInfo.historyList">
                  <v-divider :key="index"></v-divider>
                  <v-list-item :key="item.title">
                    <v-list-item-avatar>
                      <v-img
                        :src="'https://icons.iconarchive.com/icons/papirus-team/papirus-status/128/avatar-default-icon.png'"
                      ></v-img>
                    </v-list-item-avatar>

                    <v-list-item-content>
                      <v-list-item-title>{{ item.title }}</v-list-item-title>
                      <v-list-item-subtitle
                        ><span class="text--primary"
                          >Posted by: {{ item.username }}</span
                        ><br />{{ item.subtitle }}</v-list-item-subtitle
                      >
                      <v-list-item-action>
                        <v-rating
                          v-model="item.rating"
                          color="yellow darken-3"
                          background-color="grey darken-1"
                          empty-icon="$ratingFull"
                          readonly
                          small
                          dense
                        ></v-rating>
                      </v-list-item-action>
                      <v-list-item-action-text
                        >{{ new Date(item.date).toDateString()
                        }}<v-spacer></v-spacer>
                      </v-list-item-action-text>
                      <v-btn
                        v-if="madeReviewOrAdmin(item.username)"
                        class="mx-2"
                        max-width="40"
                        max-height="40"
                        fab
                        dark
                        small
                        color="pink"
                        @click="deleteReview(item.user_id)"
                      >
                        <v-icon dark> mdi-delete </v-icon>
                      </v-btn>
                      <v-btn
                        v-if="madeReviewOrAdmin(item.username)"
                        class="mx-2"
                        max-width="40"
                        max-height="40"
                        fab
                        dark
                        small
                        color="indigo lighten-1"
                        @click="editReview()"
                      >
                        <v-icon dark> mdi-lead-pencil </v-icon>
                      </v-btn>
                    </v-list-item-content>
                  </v-list-item>
                </template>
              </v-list>
              <p v-else class="text--primary">
                This recipe has no reviews so far.
              </p>
            </v-card-text>
            <v-card-actions>
              <v-pagination
                v-model="pageInfo.page"
                class="my-4"
                circle
                :length="pages"
                @input="updatePage"
              ></v-pagination>
            </v-card-actions>
            <v-btn
              v-if="this.$store.state.authenticated"
              color="orange darken-1"
              class="ma-2 white--text"
              @click="dialog = true"
            >
              Write review
              <v-icon right dark> mdi-message-draw </v-icon>
            </v-btn>
          </v-card>
        </v-container>
      </v-card>
    </v-container>
  </div>
</template>

<script>
import { getRecipeById, postReview, deleteReview } from "../service/recipeapi";
import { initPage, updatePage, pages } from "../misc/pagination";
import { getUserInfo } from "../service/userapi";
export default {
  name: "ViewRecipe",
  data() {
    return {
      recipe: { ingredients: [] },
      dialog: false,
      selectedItem: { title: "", subtitle: "", rating: 0.0 },
      pageInfo: {
        historyList: [],
        page: 1,
        pageSize: 2,
        listCount: 0,
      },
      reviewList: [],
    };
  },
  async created() {
    await this.getRecipe();
    this.initPage();
    this.updatePage(this.pageInfo.page);
  },
  methods: {
    initPage: function () {
      this.pageInfo = initPage(this.reviewList, this.pageInfo);
    },
    updatePage: function (pageIndex) {
      this.pageInfo = updatePage(pageIndex, this.reviewList, this.pageInfo);
    },
    async getRecipe() {
      const recipeid = parseInt(this.$route.query.id);
      this.recipe = (await getRecipeById(recipeid)).data;
      this.recipe.ingredients.sort((a, b) =>
        a.ingredient.name > b.ingredient.name ? 1 : -1
      );
      this.reviewList = this.recipe.reviews;
      console.log(this.reviewList);
      console.log(this.$store.state.user);
    },
    async addReview() {
      postReview(this.recipe.id, this.selectedItem)
        .then(async (response) => {
          if (response.status === 201) {
            await this.getRecipe();
            this.initPage();
          }
        })
        .catch((err) => {
          console.log("err", err);
          //User already has a review
        });
    },
    madeReviewOrAdmin(username) {
      return (
        this.$store.state.user.username === username ||
        this.$store.state.user.admin
      );
    },
    async deleteReview(userId = null) {
      try {
        const getUser = await getUserInfo();
        const user = getUser.data.user;
        if (user) {
          if (!user.admin) userId = user.id;
          if (confirm("Do you really want to delete the review?")) {
            deleteReview(userId, this.recipe.id)
              .then(async (response) => {
                if (response.status === 200) {
                  //successful delete
                  await this.getRecipe();
                  this.initPage();
                }
              })
              .catch((err) => {
                console.log("Error while deleting review:", err);
              });
          }
        }
      } catch {
        console.log("err");
      }
    },
  },
  computed: {
    pages() {
      return pages(this.pageInfo);
    },
  },
};
</script>
