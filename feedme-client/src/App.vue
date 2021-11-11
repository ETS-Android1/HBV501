<template>
  <v-app>
    <v-app-bar color="orange darken-1" fixed app dark>
      <v-toolbar-title class="ma-8">FeedMe!</v-toolbar-title>
      <v-toolbar-items>
        <v-btn text to="/">
          <span class="mr-2">Home</span>
        </v-btn>
        <v-btn v-if="this.$store.state.authenticated" text to="/account">
          <span class="mr-2">Account</span>
        </v-btn>
      </v-toolbar-items>
      <v-spacer></v-spacer>
      <v-btn
        v-if="!this.$store.state.authenticated"
        class="ma-2"
        plain
        color="white"
        to="/login"
      >
        Login
        <v-icon dark right> mdi-login-variant </v-icon>
      </v-btn>
      <v-btn
        v-if="this.$store.state.authenticated"
        class="ma-2"
        plain
        color="white"
        @click="logout"
      >
        Logout
        <v-icon dark right> mdi-login-variant </v-icon>
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
  name: "App",
  data() {
    return {};
  },
  methods: {
    isPath(path) {
      return this.$route.path === path;
    },
    logout() {
      this.$store.commit("setUser", null);
      this.$store.commit("setToken", null);
      this.$store.commit("setAuth", false);
      sessionStorage.clear();
      this.$router.push("/");
    },
  },
  mounted() {},
};
</script>
