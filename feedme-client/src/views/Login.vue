<template>
  <v-card class="mx-auto" width="400" style="margin-top: 1rem">
    <v-alert v-if="error.visible" dense dismissible type="error">
      {{ error.message }}
    </v-alert>
    <v-card-title>FeedMe Login</v-card-title>
    <v-card-text>
      <v-form>
        <v-text-field
          v-model="username"
          :error-messages="nameErrors"
          label="Username"
          required
          @input="$v.username.$touch()"
          @blur="$v.username.$touch()"
          prepend-inner-icon="mdi-account"
        ></v-text-field>
        <v-text-field
          v-model="password"
          :error-messages="passwordErrors"
          label="Password"
          prepend-inner-icon="mdi-lock"
          required
          @input="$v.password.$touch()"
          @blur="$v.password.$touch()"
          type="password"
        ></v-text-field>
        <v-btn color="orange darken-1" class="mr-4" width="100%" @click="login">
          Login
        </v-btn>
      </v-form>
    </v-card-text>
    <v-card-actions>
      <p class="mx-auto">
        <router-link to="/register"
          >Don't have an account? Register here for free!</router-link
        >
      </p>
    </v-card-actions>
  </v-card>
</template>

<script>
import { postLogin, getUserInfo } from "../service/userapi";
import { validationMixin } from "vuelidate";
import { required } from "vuelidate/lib/validators";
export default {
  name: "Login",
  mixins: [validationMixin],
  validations: {
    username: { required },
    password: { required },
  },
  data() {
    return {
      username: "",
      password: "",
      error: {
        visible: false,
        message: "",
      },
    };
  },
  computed: {
    nameErrors() {
      const errors = [];
      if (!this.$v.username.$dirty) return errors;
      !this.$v.username.required && errors.push("Username is required.");
      return errors;
    },
    passwordErrors() {
      const errors = [];
      if (!this.$v.password.$dirty) return errors;
      !this.$v.password.required && errors.push("Password is required");
      return errors;
    },
  },
  methods: {
    async login() {
      this.$v.$touch();
      this.error.visible = false;
      if (!this.$v.$anyError) {
        postLogin({ username: this.username, password: this.password })
          .then(async (response) => {
            if (response.status === 200) {
              this.$store.commit("setUser", response.data.user);
              this.$store.commit("setToken", response.data.token);
              this.$store.commit("setAuth", true);
              const recipes = (await getUserInfo()).data.recipes;
              this.$store.commit("setSavedRecipes", recipes);
              this.$router.push({ name: "Account" });
            }
          })
          .catch(() => {
            this.error.visible = true;
            this.error.message =
              "Incorrect username or password, please try again.";
          });
      }
    },
    clear() {
      this.$v.$reset();
      this.username = "";
      this.password = "";
    },
  },
  async mounted() {
    this.clear();
    this.username = this.$route.query.id;
  },
};
</script>
