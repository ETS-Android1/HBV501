<template>
  <v-app>
    <v-app-bar
    color="orange darken-1"
    fixed
    app
    dark
    >
    <v-toolbar-title class="ma-8">FeedMe!</v-toolbar-title>
      <v-toolbar-items>
        <v-btn text to="/">
          <span class="mr-2">Home</span>
        </v-btn>
        <v-btn text to="/viewrecipe">
          <span class="mr-2">ViewRecipe</span>
        </v-btn>
        <v-btn text to="/account">
          <span class="mr-2">Account</span>
        </v-btn>
      </v-toolbar-items>
      <v-spacer></v-spacer>
      <v-btn
        class="ma-2"
        plain
        color="white"
        to="/login"
      >
        Login
        <v-icon
          dark
          right
        >
          mdi-login-variant
        </v-icon>
      </v-btn>
    </v-app-bar>
    <v-main>
      <v-container fluid>
        <transition name="fade" mode="out-in">
          <router-view />
        </transition>
      </v-container>
    </v-main>
  </v-app>
</template>

<script>

export default {
  name: 'App',
  data: () => ({
    //
  }),
  methods: {
    isPath(path) {
      return this.$route.path === path;
    }
  }
};
</script>
