<template>
    <div>
        <v-dialog v-model="dialogDelete" max-width="600px">
          <v-card>
            <v-card-title class="text-h5">Are you sure you want to unfavorite this recipe?</v-card-title>
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn color="orange darken-1" text @click="closeDelete">Cancel</v-btn>
              <v-btn color="orange darken-1" text @click="deleteItemConfirm">OK</v-btn>
              <v-spacer></v-spacer>
            </v-card-actions>
          </v-card>
        </v-dialog>
        <v-card flat>
    <v-toolbar
      flat
      light
    >
      <v-toolbar-title>User Profile</v-toolbar-title>
    </v-toolbar>
    <v-tabs v-model="tab" vertical color="orange lighten-1">
      <v-tab href="#edit">
        <v-icon left>
          mdi-account
        </v-icon>
        Account
      </v-tab>
      <v-tab href="#favorites">
        <v-icon left>
          mdi-heart
        </v-icon>
        Favorites
      </v-tab>
      <v-tab-item value="edit">  
        <v-card
        flat
        class="mx-auto"
        width="600"
        height="600"
        >
        <v-card-title>
          Edit your account information
        </v-card-title>  
        <v-card-text>
          <v-card>
            <v-card-text>
              <v-text-field
              v-model="user.username"
              label="Username (*cant be changed.)"
              prepend-inner-icon="mdi-account"
              readonly
            ></v-text-field>
            <v-text-field
              v-model="email"
              label="Email"
              prepend-inner-icon="mdi-email"
            ></v-text-field>
            <v-text-field
              v-model="password"
              label="Current password"
              prepend-inner-icon="mdi-lock"
              type="password"
            ></v-text-field>
            <v-text-field
              v-model="newPassword"
              label="New password"
              prepend-inner-icon="mdi-lock"
              type="password"
            ></v-text-field>
            </v-card-text>
            <v-card-actions>
              <v-btn color="orange darken-1" class="mr-4" width="100%">
              Update
              </v-btn>
            </v-card-actions>
          </v-card>
        </v-card-text>
        </v-card>  
      </v-tab-item>
      <v-tab-item value="favorites">
        <v-card
        flat
        class="mx-auto"
        width="1000"
        height="800"
        >
        <v-card-title>
            Your favorite recipes!
        </v-card-title>
        <v-card-text>
        <v-data-table
        v-if="recipes.length > 0"
        :headers="headers"
        :items="recipes"
        item-key="name"
        class="elevation-1"
        :search="search"
        >
        <template v-slot:top>
            <v-text-field
            v-model="search"
            label="Search"
            class="mx-4"
            ></v-text-field>
        </template>
        <template #item.name="{ item }">
        <router-link :to="{path:'/viewrecipe',query:{id: item.id}}">
          {{ item.name }}
        </router-link>
      </template>
        <template v-slot:body.append>
            <tr>
            <td></td>
            <td>
                <v-text-field
                v-model="calories"
                type="number"
                label="Less than"
                ></v-text-field>
            </td>
            <td colspan="4"></td>
            </tr>
        </template>
        <template v-slot:item.actions="{ item }">
      <v-icon
        color="cyan darken-3"
        @click="deleteItem(item)"
      >
        mdi-heart-broken
      </v-icon>
    </template>
        </v-data-table>
        <p v-else class="text--primary">You don't have any recipes in your favorites.</p>
        </v-card-text>
        </v-card>     
      </v-tab-item>
    </v-tabs>
  </v-card>      
    </div>
</template>

<script>
import { getUserInfo, postUnfavorite } from '../service/userapi'
export default ({
    name: 'Account',
    data() {
        return {
            dialogDelete: false,
            user: { username: '' },
            email: '',
            password: '',
            newPassword: '',
            recipes: [],
            search: '',
            calories: '',
            editedIndex: -1,
            editedItem: {
                name: '',
                calories: 0,
                fats: 0,
                carbs: 0,
                proteins: 0,
            },
            defaultItem: {
                name: '',
                calories: 0,
                fats: 0,
                carbs: 0,
                proteins: 0,
            },
        }
    },
    methods: {
        closeDelete () {
        this.dialogDelete = false
        this.$nextTick(() => {
          this.editedItem = Object.assign({}, this.defaultItem)
          this.editedIndex = -1
        })
        },
        deleteItemConfirm () {
        this.recipes.splice(this.editedIndex, 1)
        this.setUnfavorite(this.editedItem.id);
        this.closeDelete()
      },
      deleteItem (item) {
        this.editedIndex = this.recipes.indexOf(item)
        this.editedItem = Object.assign({}, item)
        this.dialogDelete = true
      },
        setUnfavorite: async function(id) {
        await postUnfavorite(id).then(async (response) => {
            if(response.status === 204) {
            const recipes = (await getUserInfo()).data.recipes;
            this.$store.commit("setSavedRecipes", recipes);
            this.recipes = recipes;
            } 
        })
        .catch((err) => {
            console.log('error occured with favorites:', err);
        })
        },
    },
    async created() {
        const dat = (await getUserInfo()).data;
        this.user = dat.user;
        this.email = this.user.email;
        this.recipes = dat.recipes;
    },
    async mounted() {
        
    },
    computed: {
        headers () {
        return [
          {
            text: 'Recipe',
            align: 'start',
            sortable: true,
            value: 'name',
          },
          {
            text: 'Calories',
            value: 'calories',
            filter: value => {
              if (!this.calories) return true

              return value < parseInt(this.calories)
            },
          },
          { text: 'Fat (g)', value: 'fats' },
          { text: 'Carbs (g)', value: 'carbs' },
          { text: 'Protein (g)', value: 'proteins' },
          { text: 'Status', value: 'actions', sortable: false }
        ]
      },
      tab: {
      set (tab) {
        this.$router.replace({ query: { ...this.$route.query, tab } })
      },
      get () {
        return this.$route.query.tab
      }
    }
    }
})
</script>
