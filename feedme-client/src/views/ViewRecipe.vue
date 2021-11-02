<template>
    <v-container fluid>
        <v-icon
        large
        color="black"
        >
        mdi-arrow-left
        </v-icon>
        Back
        <v-card
        style="margin-top:1rem"
        >          
        <v-toolbar
          class="mb-2"
          color="orange darken-3"
          dark
          flat
        >
          <v-toolbar-title>{{recipe.name}}</v-toolbar-title>
        </v-toolbar>
            <v-card
            class="mx-auto"
            flat
            >
            <v-card-title>Nutritional value</v-card-title>
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
            <v-card
            class="mx-auto"
            flat
            >
                <v-card-title>Reviews</v-card-title>
                 <v-list three-line>
                <template v-for="(item, index) in items">
                    <v-divider
                    v-if="item.divider"
                    :key="index"
                    ></v-divider>

                    <v-list-item
                    v-else
                    :key="item.title"
                    >
                    <v-list-item-avatar>
                        <v-img :src="item.avatar"></v-img>
                    </v-list-item-avatar>

                    <v-list-item-content>
                        <v-list-item-title>{{item.title}}</v-list-item-title>
                        <v-list-item-subtitle><span class="text--primary">{{item.name}}</span><br>{{item.subtitle}}</v-list-item-subtitle>
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
                        <v-list-item-action-text>{{item.date}}</v-list-item-action-text>
                    </v-list-item-content>
                    </v-list-item>
                </template>
                </v-list>
            </v-card>
        </v-card>
    </v-container>
</template>

<script>
import { getRecipeById } from "../service/api";

export default ({
    name: 'ViewRecipe',
    data() {
        return {
           recipe: {},
           items: [
        {
          avatar: 'https://cdn.vuetifyjs.com/images/lists/1.jpg',
          title: 'Amazing recipe!',
          name: 'John Johnsson',
          subtitle: `Amazing recipe, I tried it and it worked great. Tasted like heaven!`,
          date: '02/11/2021',
          rating: 5
        },
        { divider: true, inset: false },
        {
          avatar: 'https://cdn.vuetifyjs.com/images/lists/2.jpg',
          title: 'Nomnom',
          name: 'Peter Portland',
          subtitle: `Very good, only burned it twice.`,
          date: '02/11/2021',
          rating: 4
        },
        { divider: true, inset: false },
        {
          avatar: 'https://cdn.vuetifyjs.com/images/lists/3.jpg',
          title: '#Ad',
          name: 'Grace Walker',
          subtitle: `Best chicken recipe ever, only thanks to FeedMe the greatest company in the world, would watch more ads.`,
          date: '02/11/2021',
          rating: 5
        },
         { divider: true, inset: false },
        {
          avatar: 'https://cdn.vuetifyjs.com/images/lists/5.jpg',
          title: 'Worst chicken ever!',
          name: 'Karen Fletcher',
          subtitle: `Very tasteless and bland!`,
          date: '02/11/2021',
          rating: 1
        }
      ]
        }
    },
    methods: {

    },
    async mounted() {
        const recipeid = parseInt(this.$route.query.id);
        this.recipe = (await getRecipeById(recipeid)).data;
    }
})
</script>
