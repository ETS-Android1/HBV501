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
			</v-container>
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
				<v-list v-if="items.length > 0" three-line>
					<template v-for="(item, index) in items">
						<v-divider v-if="item.divider" :key="index"></v-divider>

						<v-list-item v-else :key="item.title">
							<v-list-item-avatar>
								<v-img :src="item.avatar"></v-img>
							</v-list-item-avatar>

							<v-list-item-content>
								<v-list-item-title>{{ item.title }}</v-list-item-title>
								<v-list-item-subtitle
									><span class="text--primary">{{ item.name }}</span
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
								<v-list-item-action-text>{{ item.date }}</v-list-item-action-text>
							</v-list-item-content>
						</v-list-item>
					</template>
				</v-list>
        <p v-else class="text--primary">This recipe has no reviews so far.</p>
        </v-card-text>
			</v-card>
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
			desserts: [
				{
					name: "Frozen Yogurt",
					calories: 159
				},
				{
					name: "Ice cream sandwich",
					calories: 237
				},
				{
					name: "Eclair",
					calories: 262
				},
				{
					name: "Cupcake",
					calories: 305
				},
				{
					name: "Gingerbread",
					calories: 356
				},
				{
					name: "Jelly bean",
					calories: 375
				},
				{
					name: "Lollipop",
					calories: 392
				},
				{
					name: "Honeycomb",
					calories: 408
				},
				{
					name: "Donut",
					calories: 452
				},
				{
					name: "KitKat",
					calories: 518
				}
			],
			items: [
			]
		};
	},
	async mounted() {
		const recipeid = parseInt(this.$route.query.id);
		this.recipe = (await getRecipeById(recipeid)).data;
		console.log(this.recipe);
	}
};
</script>
