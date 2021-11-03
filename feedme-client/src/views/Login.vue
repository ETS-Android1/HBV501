<template>
    <v-card
        class="mx-auto"
        width="400"
        style="margin-top: 1rem;"
    >
    <v-card-title>FeedMe Login</v-card-title>
    <v-card-text>
        <v-form>
            <v-text-field
            v-model="username"
            label="Username"
            prepend-inner-icon="mdi-account"
            ></v-text-field>
            <v-text-field
            v-model="password"
            label="Password"
            prepend-inner-icon="mdi-lock"
            type="password"
            ></v-text-field>
            <v-btn
            color="orange darken-1"
            class="mr-4"
            width="100%"
            >
            Login
            </v-btn>          
        </v-form>
    </v-card-text>
    <v-card-actions>
        <p class="mx-auto"><router-link to="/register">Don't have an account? Register here for free!</router-link></p>
    </v-card-actions>
    </v-card>
</template>

<script>
export default ({
    name: 'Login',
    data() {
        return {
            username: "",
            password: ""
        }
    },
    async mounted() {

    }
})
</script>
