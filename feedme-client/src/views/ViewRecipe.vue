<template>
	<v-container fluid>
		<v-btn fab light small @click="$router.go(-1)">
			<v-icon>mdi-arrow-left</v-icon>
		</v-btn>
		<v-card style="margin-top: 1rem">
			<v-toolbar class="mb-2" color="orange darken-3" dark flat>
				<v-toolbar-title>{{ recipe.name }}</v-toolbar-title>
			</v-toolbar>
			<v-container fluid>
				<v-row>
					<v-col cols="6">
						<v-card flat>
							<v-card-title>Ingredients</v-card-title>
							<v-card-subtitle>List of the ingredients in the recipe</v-card-subtitle>
							<v-card-text>
								<v-simple-table fixed-header height="300px">
									<template v-slot:default>
										<thead>
											<tr>
												<th class="text-left">
													Name
												</th>
												<th class="text-left">
													Amount
												</th>
											</tr>
										</thead>
										<tbody>
											<tr v-for="item in recipe.ingredients" :key="item.ingredient.id">
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
					<p class="text--primary">{{recipe.description}}</p>
				</v-card-text>
			</v-card>
			<v-card class="mx-auto" flat>
				<v-card-title>Reviews</v-card-title>
        <v-card-subtitle>Feedback from our users</v-card-subtitle>
        <v-card-text>
				<v-list v-if="reviewList.length > 0" three-line>
					<template v-for="(item, index) in reviewList">
						<v-divider v-if="item.divider" :key="index"></v-divider>

						<v-list-item v-else :key="item.title">
							<v-list-item-avatar>
								<v-img :src="'https://icons.iconarchive.com/icons/papirus-team/papirus-status/128/avatar-default-icon.png'"></v-img>
							</v-list-item-avatar>

							<v-list-item-content>
								<v-list-item-title>{{ item.title }}</v-list-item-title>
								<v-list-item-subtitle
									><span class="text--primary">{{ item.username }}</span
									><br />{{ item.subtitle }}</v-list-item-subtitle>
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
								<v-list-item-action-text>{{ item.date }}</v-list-item-action-text>
							</v-list-item-content>
						</v-list-item>
					</template>
				</v-list>
        <p v-else class="text--primary">This recipe has no reviews so far.</p>
        </v-card-text>
			</v-card>
      </v-container>
		</v-card>
	</v-container>
</template>

<script>
import { getRecipeById } from "../service/recipeapi";

export default {
	name: "ViewRecipe",
	data() {
		return {
			recipe: {},
			reviewList: [
			]
		};
	},
	async created() {
		const recipeid = parseInt(this.$route.query.id);
		this.recipe = (await getRecipeById(recipeid)).data;
    await this.setReviews(this.recipe.reviews);
		console.log(this.recipe.reviews);
	},
  methods: {
    async setReviews(reviews) {
      let list = [];
      for(let review in reviews) {
        list.push(review);
        list.push({divider: true, inset: true})
      }
      if(reviews.length <= 1)
        this.reviewList = reviews;
      else
        this.reviewList = list;
    }
  }
};
</script>
