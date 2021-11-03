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
      <template v-for="(recipe,index) in historyList">
        <v-col :key="recipe.id">
          <v-container
            class="pa-2"
          >
          <v-card
        class="mx-auto"
        width="22rem"
        height="27rem"
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
                <div class="text-center mt-12">
                <v-rating
                  v-model="recipe.rating"
                  color="yellow darken-3"
                  background-color="grey darken-1"
                  empty-icon="$ratingFull"
                  half-increments
                  readonly
                ></v-rating>
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
        v-model="page" 
        class="my-4"
        circle
        :length="pages"
        @input="updatePage"
    ></v-pagination>
  </v-container>
  </div>
</template>

<script>
import { getIngredients, getRecipes } from "../service/api";
  export default {
    name: 'Home',
    data() {
      return {
        page: 1,
        pageSize: 6,
        list: [],
        listCount: 0,
        historyList: [],
        ex4: ['t1', 't2'],
        types: []
      }
    },
    async created() {
      const ingredients = (await getIngredients()).data;
      ingredients.forEach(ing => {
        this.types.push({ text: ing.name, value: ing.id , selected : false  })
      });     

      this.list = (await getRecipes()).data.recipes;
		let _this = this;
		_this.initPage();
		_this.updatePage(_this.page);
	},
    methods: {
      initPage: function() {
			let _this = this;
			_this.listCount = _this.list.length;
			if (_this.listCount < _this.pageSize) {
				_this.historyList = _this.list;
			} else {
				_this.historyList = _this.list.slice(0, _this.pageSize);
			}
		},
		updatePage: function(pageIndex) {
			let _this = this;
			let _start = (pageIndex - 1) * _this.pageSize;
			let _end = pageIndex * _this.pageSize;
			_this.historyList = _this.list.slice(_start, _end);
			_this.page = pageIndex;
		}
    },
    async mounted() {

    },
    computed: {
      pages() {
			let _this = this;
			if (_this.pageSize == null || _this.listCount == null) return 0;
			return Math.ceil(_this.listCount / _this.pageSize);
		}
    }
  }
</script>
